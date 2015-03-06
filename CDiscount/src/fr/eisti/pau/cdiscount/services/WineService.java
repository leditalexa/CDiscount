package fr.eisti.pau.cdiscount.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import fr.eisti.pau.cdiscount.domain.Recipe;
import fr.eisti.pau.cdiscount.domain.Wine;

public class WineService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public List<Wine> find(Wine wine){
		return find(wine.getName());
	}

	public List<Wine> find(String keyword){		
		JSONObject search, search1;
		List<Wine> res = new LinkedList<>();
		JSONObject data = getSearchJSON();
		
		if(data != null){
			// Page 0
			search = setParams(data, keyword, 0);
			String tmp = getCDiscountReponse(data, search);
			res.addAll(setListeVin(tmp));
			
			// Autres pages
			int nbPages = 0;
			try {
				nbPages = setStringToJSON(tmp).getInt("PageCount");
			} catch (JSONException e) {
				e.printStackTrace();
			}

			for(int i=1;i<nbPages;++i){
				JSONObject data1 = getSearchJSON();
				search1 = setParams(data1, keyword, i);
				res.addAll(setListeVin(getCDiscountReponse(data1, search1)));
			}
			return res;
		}
		return null;
	}
	
	
	private JSONObject setParams(JSONObject data, String keyword, int page){
		JSONObject search = null;
		try {
			search = data.getJSONObject("SearchRequest");
			search.put("Keyword", keyword);
			JSONObject tmp = search.optJSONObject("Pagination").put("PageNumber", page);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return search;
	}
	
	private String getCDiscountReponse(JSONObject data, JSONObject search){
		try{
			URL url = new URL("http://api.cdiscount.com/OpenApi/json/search");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(data.toString());
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String res = in.readLine();
			if(res != null){return res;}

			System.out.println("\nREST Service Invoked Successfully..");
			in.close();

		}catch(IOException e){
			System.out.println("Error with CDiscount REST service");
			e.printStackTrace();
		}		
		return null;
	}

	private List<Wine> setListeVin(String in){
		Gson gson = new Gson();
		JsonObject elt = gson.fromJson(in, JsonObject.class);
		JsonArray products = new JsonArray();
		if(elt.get("Products") instanceof JsonArray){
			products = elt.getAsJsonArray("Products");
		}
		
		List<Wine> wines = new LinkedList<Wine>();
		if(products != null){
			for (JsonElement jsonElement : products) {
				wines.add(gson.fromJson(jsonElement, Wine.class));
			}
		}
		return wines;
	}
	
	private static JSONObject setStringToJSON(String str){
		if(str != ""){
			try {
				return new JSONObject(str);
			} catch (JSONException e) {e.printStackTrace();}
		}
		return null;
	}
		

	private JSONObject getSearchJSON(){
		String string = "";
		InputStream is;

		try {
			is = getClass().getResourceAsStream("/SearchRequest.json");
			InputStreamReader reader = new InputStreamReader(is);

			BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				string += line + "\n";
			}
			br.close();
			reader.close();
			is.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject json;
		return setStringToJSON(string);
	}
	
	
	public List<Wine> findAssociated(Recipe recipe){
		List<Wine> res = new LinkedList<Wine>();
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service;
		String response = "";
		try {
			service = client.resource(
					"http://www.platsnetvins.com/api-xml/eisti-tcv.5vh4e7-accords-plat-xml.php?nomplat=" 
							+ URLEncoder.encode(recipe.getKeyword(), "UTF-8"));
			response = service.accept(MediaType.TEXT_PLAIN).get(String.class);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		res = fromPlatsNetVinXML(response);
		log.info("For recipe '"+recipe.getTitle()+"', "+res.size()+" wine found");
		return res;
	}

	private List<Wine> fromPlatsNetVinXML(String xml){
		SAXBuilder sxb = new SAXBuilder();
		List<Wine> res = new LinkedList<Wine>();
		try {
			Document document = sxb.build(new StringReader(xml));
			Element root = document.getRootElement();
			Element accords = root.getChild("accords");
			List<Element> xmlWines = accords.getChildren();
			for (Element wine : xmlWines) {
				List<Wine> test = find(wine.getChild("nom-vin").getValue()+" vin "+wine.getChild("type-vin").getValue());
				log.info(wine.getChild("nom-vin").getValue()+" -> found :"+test.size());
				res.addAll(test);
			}
			
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
			return null;
		}

		return res;
	}

}
