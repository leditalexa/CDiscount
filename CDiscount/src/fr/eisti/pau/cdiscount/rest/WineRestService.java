package fr.eisti.pau.cdiscount.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.eisti.pau.cdiscount.services.WineService;
import fr.eisti.pau.cdiscount.util.CDiscountResponse;

@Path("/wine")
public class WineRestService {

	private final WineService wineService = new WineService(); 

	@GET
	@Path("/find/{keyword}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("keyword") String keyword){
		return CDiscountResponse.build("wines", wineService.find(keyword), 0);
	}

}
