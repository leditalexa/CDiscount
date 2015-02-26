package fr.eisti.pau.cdiscount.domain;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class User {
	
	@Id
	private String identifiant;
	private String firstname;
	private String lastname;
	private String passwd;
	private int age;
	
	public User(){
		super();
	}
	
	public User(String firstname, String lastname, String identifiant,
			String passwd, int age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.identifiant = identifiant;
		this.passwd = passwd;
		this.age = age;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getIdentifiant() {
		return identifiant;
	}


	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}


	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
	
}
