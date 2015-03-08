package fr.eisti.pau.cdiscount.dto;

import fr.eisti.pau.cdiscount.domain.Wine;


public class WineDto {
	private String title;
	private String url_icon;
	private String priceTop;
	private String price;
	private int rating;
	private String description;
	private String url;
	
	public WineDto() {
		super();
	}

	public WineDto(String title, String url_icon, String priceTop, String price, int rating, String description, String url) {
		super();
		this.title = title;
		this.url_icon = url_icon;
		this.priceTop = priceTop;
		this.price = price;
		this.rating = rating;
		this.description = description;
		this.url = url;
	}
	
	public WineDto(Builder b){
		this.title = b.title;
		this.url_icon = b.url_icon;
		this.priceTop = b.priceTop;
		this.price = b.price;
		this.rating = b.rating;
		this.description = b.description;
		this.url = b.url;		
	}
	
	public String getTitle() {return title;}

	public String getUrl_icon() {return url_icon;}

	public String getPriceTop() {return priceTop;}

	public String getPrice() {return price;}

	public int getRating() {return rating;}

	public String getDescription() {return description;}

	public String getUrl() {return url;}
	
	public static WineDto build(Wine w){
		Float tmp = Float.valueOf(w.getRating());
		
		return new WineDto.Builder()
				.title(w.getName())
				.url_icon(w.getMainImageUrl())
				.priceTop(w.getBestOffer().getPriceDetails().getReferencePrice())
				.price(w.getBestOffer().getSalePrice())
				.rating(tmp.intValue())
				.description(w.getDescription())
				.url(w.getBestOffer().getProductURL())
				.build();
	}
	
	// ************** Class Builder de transformation d'un Wine en WineDto  ************
	
	public static class Builder{
		private String title;
		private String url_icon;
		private String priceTop;
		private String price;
		private int rating;
		private String description;
		private String url;
		
		public Builder(){}
		
		public Builder title(String title){
			this.title = title;
			return this;
		}
		
		public Builder url_icon(String url_icon){
			this.url_icon = url_icon;
			return this;
			
		}
		public Builder priceTop(String priceTop){
			this.priceTop = priceTop;
			return this;
			
		}
		public Builder price(String price){
			this.price = price;
			return this;
			
		}
		public Builder rating(int rating){
			this.rating = rating;
			return this;
			
		}
		public Builder description(String description){
			this.description = description;
			return this;
			
		}
		public Builder url(String url){
			this.url = url;
			return this;
			
		}
		
		
		
		public WineDto build(){
			return new WineDto(this);
		}
	}
}
