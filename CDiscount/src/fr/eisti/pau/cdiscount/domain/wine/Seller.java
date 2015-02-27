package fr.eisti.pau.cdiscount.domain.wine;

public class Seller {
	private String Id;
	private String Name;
	
	
	public Seller() {
		super();
	}
	public Seller(String id, String name) {
		super();
		Id = id;
		Name = name;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	
}
