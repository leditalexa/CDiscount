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
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

import fr.eisti.pau.cdiscount.domain.User;
import fr.eisti.pau.cdiscount.exception.UserAlreadyExistsException;
import fr.eisti.pau.cdiscount.exception.WrongPasswordException;
import fr.eisti.pau.cdiscount.exception.WrongUserException;
import fr.eisti.pau.cdiscount.services.UserService;
import fr.eisti.pau.cdiscount.util.ResponseEntity;

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
			return buildResponse("User "+usr.getIdentifiant()+ " created", usr, 0);
		}catch(WrongUserException e){
			return buildResponse("Worng request content", null, 1);
		}catch(UserAlreadyExistsException e){
			return buildResponse("User already exist", null, 1);
		}
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String json) {

		try{
			JSONObject obj = new JSONObject(json);
			String identifiant = obj.getString("identifiant");
			String password = obj.getString("passwd");
			
			User usr = userService.login(identifiant, password);
			return buildResponse("log in successful", usr, 0);
		}catch(JSONException e){
			return buildResponse("worng request content", null, 1);
		}catch(WrongUserException|WrongPasswordException e){
			return buildResponse("Wrong user or password",	null, 1);
		}
	}

	
	private Response buildResponse(String message, Object content, int code){
		ResponseBuilder build = new ResponseBuilderImpl();
		ResponseEntity.Builder entity = new ResponseEntity.Builder();

		return build.entity(
				entity
				.message(message)
				.content(content)
				.code(code)
				.build()
				).build();
	}
}
