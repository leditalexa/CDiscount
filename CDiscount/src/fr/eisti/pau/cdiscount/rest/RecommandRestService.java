package fr.eisti.pau.cdiscount.rest;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Path("/recommend")
public class RecommandRestService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String recommand(String req){
		JSONObject t = new JSONObject();
		try{
			JSONObject reqJson = new JSONObject(req);
			String userId = reqJson.getString("userId");
			int num = reqJson.getInt("num");
			t.put("userId", userId);
			t.put("num", num);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return getCnxReponse(t, "POST");
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public String addSelectedWine(String req){
		JSONObject t = new JSONObject();
		try{
			JSONObject reqJson = new JSONObject(req);
			String userId = reqJson.getString("userId");
			JSONObject vin = reqJson.getJSONObject("wine");
			System.out.println(vin);
			t.put("userId", userId);
			t.put("wine", vin);
			t.put("rate", 2);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return getCnxReponse(t, "PUT");
	}


	private static String getCnxReponse(JSONObject o,String method){

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service;
		System.out.println("first layer:"+ o .toString());
		service = client.resource("http://localhost:8080/Recommender/recommendation");
		String response ="";
		switch (method) {
		case "PUT":
			response = service.accept(MediaType.APPLICATION_JSON).put(String.class, o.toString());
			break;
		case "POST":
			response = service.accept(MediaType.APPLICATION_JSON).post(String.class, o.toString());
			break;
		default:
			break;
		}
		return response;
	}

}
