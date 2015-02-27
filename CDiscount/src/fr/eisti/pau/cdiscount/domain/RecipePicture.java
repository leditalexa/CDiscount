package fr.eisti.pau.cdiscount.domain;

public class RecipePicture {

	private String name;
	private String url;
	
	public RecipePicture() {
		super();
	}

	public RecipePicture(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
