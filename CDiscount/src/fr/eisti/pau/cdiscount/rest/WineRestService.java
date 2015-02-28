package fr.eisti.pau.cdiscount.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.eisti.pau.cdiscount.domain.Recipe;
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

	@GET
	@Path("/associated/{keyword}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAssociated(@PathParam("keyword") String keyword){
		Recipe test = new Recipe();
		test.setTitle(keyword);
		return  CDiscountResponse.build("ok", wineService.findAssociated(test), 0);//CDiscountResponse.build("", wineService.findAssociated(test), 0);
	}
}
