package fr.eisti.pau.cdiscount.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fr.eisti.pau.cdiscount.domain.Wine;

public class WineService {
	
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
					JsonArray products = elt.getAsJsonArray("Products");
					List<Wine> wines = new LinkedList<Wine>();
					for (JsonElement jsonElement : products) {
						wines.add(gson.fromJson(jsonElement, Wine.class));
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
