package fr.eisti.pau.cdiscount.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import fr.eisti.pau.cdiscount.services.WineService;

public class RecommandRestService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getCnx(String userId, String itemId, float rate){
		return WineService.setStringToJSON(getCnxReponse(userId, itemId, rate));
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject get(String userId, String itemId, float rate){
		return WineService.setStringToJSON(getCnxReponse(userId, itemId, rate));
	}


	private static String getCnxReponse(String userId, String itemId, float rate){
		try{
			JSONObject t = new JSONObject();
			t.put("userId", userId);
			t.put("itemId", itemId);
			t.put("rate", rate);
			URL url = new URL("http://localhost:8080/Recommender/recommendation");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(t.toString());
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
			String res = in.readLine();
			if(res != null){return res;}

			System.out.println("\nREST Service Invoked Successfully..");
			in.close();

		}catch(IOException e){
			System.out.println("Error with CDiscount REST service");
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return null;
	}

}
