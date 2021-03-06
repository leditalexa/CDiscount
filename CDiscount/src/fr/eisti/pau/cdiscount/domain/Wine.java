package fr.eisti.pau.cdiscount.domain;

import fr.eisti.pau.cdiscount.domain.wine.BestOffer;

public class Wine {
    private BestOffer BestOffer;
    private String Brand;
    private String Description;
    private String Id;
    private String Images;
    private String MainImageUrl;
    private String Name;
    private String Rating;
	public Wine() {
		super();
	}
	public Wine(fr.eisti.pau.cdiscount.domain.wine.BestOffer bestOffer,
			String brand, String description, String id, String images,
			String mainImageUrl, String name, String rating) {
		super();
		BestOffer = bestOffer;
		Brand = brand;
		Description = description;
		Id = id;
		Images = images;
		MainImageUrl = mainImageUrl;
		Name = name;
		Rating = rating;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wine other = (Wine) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}
	public BestOffer getBestOffer() {
		return BestOffer;
	}
	public void setBestOffer(BestOffer bestOffer) {
		BestOffer = bestOffer;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getImages() {
		return Images;
	}
	public void setImages(String images) {
		Images = images;
	}
	public String getMainImageUrl() {
		return MainImageUrl;
	}
	public void setMainImageUrl(String mainImageUrl) {
		MainImageUrl = mainImageUrl;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getRating() {
		return Rating;
	}
	public void setRating(String rating) {
		Rating = rating;
	}    
}
