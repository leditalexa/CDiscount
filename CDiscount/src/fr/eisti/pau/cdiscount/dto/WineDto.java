package fr.eisti.pau.cdiscount.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import fr.eisti.pau.cdiscount.domain.Wine;

public class WineDto {
	private String title;
	private String url_icon;
	private String priceTop;
	private String price;
	private String rating;
	private String description;
	
	
	public WineDto() {
		super();
	}

	public WineDto(String title, String url_icon, String priceTop, String price, String rating, String description) {
		super();
		this.title = title;
		this.url_icon = url_icon;
		this.priceTop = priceTop;
		this.price = price;
		this.rating = rating;
		this.description = description;
	}

	public WineDto(Wine vin) {
		super();
		this.title = vin.getName();
		this.url_icon = vin.getMainImageUrl();
		this.priceTop = vin.getBestOffer().getPriceDetails().getReferencePrice();
		this.price = vin.getBestOffer().getSalePrice();
		this.rating = vin.getRating();
		this.description = vin.getDescription();
	
	}

	public static List<WineDto> setTransport(List<Wine> in){
		List<WineDto> out = new LinkedList<>();
		for(Wine w : in){ out.add(new WineDto(w));}
		return out;
	}

	public static List<Wine> eraseDoublon(List<Wine> in){
		Set<Wine> tmp = new HashSet<>();
		tmp.addAll(in);
		List<Wine> out = new ArrayList<>(tmp);
		return out;
	}


	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}

	public String getUrl_icon() {return url_icon;}
	public void setUrl_icon(String url_icon) {this.url_icon = url_icon;}

	public String getPriceTop() {return priceTop;}
	public void setPriceTop(String priceTop) {this.priceTop = priceTop;}

	public String getPrice() {return price;}
	public void setPrice(String price) {this.price = price;}

	public String getRating() {return rating;}
	public void setRating(String rating) {this.rating = rating;}

	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
}
