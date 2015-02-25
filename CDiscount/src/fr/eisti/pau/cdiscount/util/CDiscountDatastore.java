package fr.eisti.pau.cdiscount.util;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class CDiscountDatastore {

	private static Datastore datastore;

	public static Datastore getDatastore(){
		
		if(datastore == null){
			String dbName = new String("cdiscount");
			MongoClient mongo;
			
			try {
				mongo = new MongoClient("localhost", 27017);
				Morphia morphia = new Morphia();
				CDiscountDatastore.datastore = morphia.createDatastore(mongo, dbName);
				
				morphia.mapPackage("fr.eisti.pau.cdiscount.domain");
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} 
			
		}
		return CDiscountDatastore.datastore;
	}

}
