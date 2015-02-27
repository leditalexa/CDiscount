package fr.eisti.pau.cdiscount.services;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import fr.eisti.pau.cdiscount.domain.Recipe;

public class RecipeService {

	public List<Recipe> find(String keyword){

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getMarmitonURI(keyword)); 
		String response = service.accept(MediaType.TEXT_PLAIN).get(String.class);
		Gson gson = new Gson();
		JsonObject elt = gson.fromJson(response, JsonObject.class);
		JsonObject data = elt.getAsJsonObject("data");
		JsonArray items = data.getAsJsonArray("items");
		List<Recipe> recipes = new LinkedList<Recipe>();
		for (JsonElement jsonElement : items) {
			recipes.add(gson.fromJson(jsonElement, Recipe.class));
		}
		return recipes;
	}


	private URI getMarmitonURI(String keyword){
		try{
			keyword = URLEncoder.encode(keyword, "UTF-8");
			System.out.println(keyword);
			return UriBuilder.fromUri("http://m.marmiton.org/webservices/json.svc/GetRecipeSearch?SiteId=1&KeyWord="+keyword+"&SearchType=0&ItemsPerPage=30&StartIndex=1").build();
		}catch(UnsupportedEncodingException e){
			return null;
		}
	}

}
