package fr.eisti.pau.cdiscount.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
import org.mongodb.morphia.Datastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

import fr.eisti.pau.cdiscount.domain.User;
import fr.eisti.pau.cdiscount.util.CDiscountDatastore;
import fr.eisti.pau.cdiscount.util.ResponseEntity;

@Path("/user")
public class UserService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private final Datastore ds = CDiscountDatastore.getDatastore();

	@GET
	@Path("/{identifiant}")
	@Produces(MediaType.APPLICATION_JSON)
	public User get(@PathParam("identifiant") String identifiant){	
		return ds.get(User.class, identifiant);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response signUp(String json){
		Gson gson = new Gson();


		User usr = gson.fromJson(json, User.class);
		usr.setPasswd(generateHash(usr.getPasswd()));
		if(ds.get(User.class, usr.getIdentifiant()) == null){
			ds.save(usr);
			log.info("User "+usr.getIdentifiant()+ " created");
			return buildResponse(
					"User "+usr.getIdentifiant()+ " created",
					ds.get(User.class, usr.getIdentifiant()),
					0
					);
		}else{
			log.error("User "+usr.getIdentifiant()+ " already exist");
			return buildResponse(
					"User "+usr.getIdentifiant()+ " already exist",
					null,
					1
					);
		}
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(String json) {

		try{
			JSONObject obj = new JSONObject(json);
			String identifiant = obj.getString("identifiant");
			String password = obj.getString("passwd");

			User usr = ds.get(User.class, identifiant);

			if(usr != null){
				String hashedPassword = generateHash(password);
				String storedPasswordHash = usr.getPasswd();

				if(hashedPassword.equals(storedPasswordHash)){
					log.info("Login :"+identifiant);
					return buildResponse("log in successful", usr, 0);
				}else{
					return buildResponse("wrong password", null, 1);
				}
			}else{
				return buildResponse("wrong user", null, 1);
			}
		}catch(JSONException e){
			return buildResponse("worng request content", null, 1);
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

	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}
		return hash.toString();
	}

}
