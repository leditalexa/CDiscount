package fr.eisti.pau.cdiscount.rest;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.eisti.pau.cdiscount.services.RecipeService;
import fr.eisti.pau.cdiscount.util.CDiscountResponse;

@Path("/recipe")
public class RecipeRestService {
	
	private final RecipeService recipeService = new RecipeService();
	
	@GET
	@Path("/find/{keyword}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRecipe(@PathParam("keyword") String keyword) throws UnsupportedEncodingException{
		return CDiscountResponse.build("recipes found", recipeService.find(keyword), 0);
	}
	
}
