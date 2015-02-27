package fr.eisti.pau.cdiscount.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;

import fr.eisti.pau.cdiscount.domain.User;
import fr.eisti.pau.cdiscount.exception.UserAlreadyExistsException;
import fr.eisti.pau.cdiscount.exception.WrongPasswordException;
import fr.eisti.pau.cdiscount.exception.WrongUserException;
import fr.eisti.pau.cdiscount.services.UserService;
import fr.eisti.pau.cdiscount.util.CDiscountResponse;

@Path("/user")
public class UserRestService {
	
	private final UserService userService = new UserService();

	@GET
	@Path("/{identifiant}")
	@Produces(MediaType.APPLICATION_JSON)
	public User get(@PathParam("identifiant") String identifiant){
		return userService.get(identifiant);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response signUp(String json){
		
		Gson gson = new Gson();
		User usr = gson.fromJson(json, User.class);
		
		try{
			usr = userService.signUp(usr);
			return CDiscountResponse.build("User "+usr.getIdentifiant()+ " created", usr, 0);
		}catch(WrongUserException e){
			return CDiscountResponse.build("Worng request content", null, 1);
		}catch(UserAlreadyExistsException e){
			return CDiscountResponse.build("User already exist", null, 1);
		}
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String json) {

		try{
			JSONObject obj = new JSONObject(json);
			String identifiant = obj.getString("identifiant");
			String password = obj.getString("password");
			
			User usr = userService.login(identifiant, password);
			return CDiscountResponse.build("log in successful", usr, 0);
		}catch(JSONException e){
			return CDiscountResponse.build("worng request content", null, 1);
		}catch(WrongUserException|WrongPasswordException e){
			return CDiscountResponse.build("Wrong user or password",	null, 1);
		}
	}
}
