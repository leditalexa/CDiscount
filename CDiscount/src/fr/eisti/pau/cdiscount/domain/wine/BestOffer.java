package fr.eisti.pau.cdiscount.domain.wine;

public class BestOffer {
    private String Condition;
    private String Id;
    private Boolean IsAvailable;
    private PriceDetails PriceDetails;
    private String ProductURL;
    private String SalePrice;
    private Seller Seller;
	public BestOffer() {
	}
	public BestOffer(String condition, String id, Boolean isAvailable,
			fr.eisti.pau.cdiscount.domain.wine.PriceDetails priceDetails,
			String productURL, String salePrice,
			fr.eisti.pau.cdiscount.domain.wine.Seller seller) {
		Condition = condition;
		Id = id;
		IsAvailable = isAvailable;
		PriceDetails = priceDetails;
		ProductURL = productURL;
		SalePrice = salePrice;
		Seller = seller;
	}
	public String getCondition() {
		return Condition;
	}
	public void setCondition(String condition) {
		Condition = condition;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Boolean getIsAvailable() {
		return IsAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		IsAvailable = isAvailable;
	}
	public PriceDetails getPriceDetails() {
		return PriceDetails;
	}
	public void setPriceDetails(PriceDetails priceDetails) {
		PriceDetails = priceDetails;
	}
	public String getProductURL() {
		return ProductURL;
	}
	public void setProductURL(String productURL) {
		ProductURL = productURL;
	}
	public String getSalePrice() {
		return SalePrice;
	}
	public void setSalePrice(String salePrice) {
		SalePrice = salePrice;
	}
	public Seller getSeller() {
		return Seller;
	}
	public void setSeller(Seller seller) {
		Seller = seller;
	}
    
    
	
}
