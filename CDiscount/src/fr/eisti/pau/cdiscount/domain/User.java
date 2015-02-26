package fr.eisti.pau.cdiscount.domain;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class User {
	
	@Id
	private String identifiant;
	private String firstname;
	private String lastname;
	private String password;
	private int age;
	
	public User(){
		super();
	}
	
	public User(String firstname, String lastname, String identifiant,
			String password, int age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.identifiant = identifiant;
		this.password = password;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
	
	public boolean checkUser(){
		return this.firstname!=null &&
				this.identifiant!=null &&
				this.lastname!=null &&
				this.password!=null;
	}
	
}
