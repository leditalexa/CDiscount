package fr.eisti.pau.cdiscount.services;

import java.net.UnknownHostException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
import org.mongodb.morphia.Datastore;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import fr.eisti.pau.cdiscount.domain.User;
import fr.eisti.pau.cdiscount.util.CDiscountDatastore;

@Path("/user")
public class UserService {

	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)
	public User sayHello(){
		User usr = new User("Alexandre", "Ledi", "leditalexa", "Icosaedre20", 24);
		return usr;
	}
	
	@GET
	@Path("/get/{identifiant}")
	@Produces(MediaType.APPLICATION_JSON)
	public User get(@PathParam("identifiant") String identifiant){	
		User usr = new User("Alexandre", "Ledit", identifiant, "Icosaedre20", 24);
		return usr;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/saveDb")
	public String saveDB(User user){
		System.out.println(user);
		return "salut";
	}
	
	
}
