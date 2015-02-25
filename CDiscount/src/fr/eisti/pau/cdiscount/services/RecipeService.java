package fr.eisti.pau.cdiscount.services;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Path("/recipe")
public class RecipeService {
	
	@GET
	@Path("/find/{keyword}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRecipe(@PathParam("keyword") String keyword) throws UnsupportedEncodingException{
		
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		
		WebResource service = client.resource(getMarmitonURI(keyword)); 
		return service.accept(MediaType.TEXT_PLAIN).get(String.class).toString();
	}
	
	
	private URI getMarmitonURI(String keyword) throws UnsupportedEncodingException {
		keyword = URLEncoder.encode(keyword, "UTF-8");
		System.out.println(keyword);
	    return UriBuilder.fromUri("http://m.marmiton.org/webservices/json.svc/GetRecipeSearch?SiteId=1&KeyWord="+keyword+"&SearchType=0&ItemsPerPage=30&StartIndex=1").build();

	  }
	
}
