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
		JSONObject data = getSearchJSON();

		if(data!=null){
			try {
				JSONObject search = data.getJSONObject("SearchRequest");
				search.put("Keyword", keyword);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}

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

				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));

				String res = in.readLine();
				Gson gson = new Gson();
				if(res != null){
					JsonObject elt = gson.fromJson(res, JsonObject.class);
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

				System.out.println("\nREST Service Invoked Successfully..");
				in.close();

			}catch(IOException e){
				System.out.println("Error with CDiscount REST service");
				e.printStackTrace();
			}
		}
		return null;
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
							+ URLEncoder.encode(recipe.getTitle(), "UTF-8"));
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
		if(string != ""){
			try {
				json = new JSONObject(string);
				return json;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
