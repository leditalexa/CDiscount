package fr.eisti.pau.cdiscount.dao;

import org.mongodb.morphia.Datastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.eisti.pau.cdiscount.domain.User;
import fr.eisti.pau.cdiscount.exception.UserAlreadyExistsException;
import fr.eisti.pau.cdiscount.exception.WrongUserException;
import fr.eisti.pau.cdiscount.util.CDiscountDatastore;

public class UserDao{
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final Datastore ds = CDiscountDatastore.getDatastore();
	
	public User get(String identifiant){
		return ds.get(User.class, identifiant);
	}
	
	public User create(User user) throws UserAlreadyExistsException, WrongUserException{
		if(user.checkUser()){
			if(!exist(user.getIdentifiant())){
				ds.save(user);
				log.info("User "+user.getIdentifiant()+ " created");
				return user;	
			}else{
				log.error("User "+user.getIdentifiant()+ " already exist");
				throw new UserAlreadyExistsException();
			}
		}else{
			log.error("User "+user.getIdentifiant()+ " can't be created");
			throw new WrongUserException(); 
		}
	}
	
	public boolean exist(String identifiant){
		return findOne(identifiant)!=null;
	}
	
	public User findOne(String identifiant){
		return ds.get(User.class, identifiant);
	}

}
