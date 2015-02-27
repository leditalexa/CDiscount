package fr.eisti.pau.cdiscount.domain.wine;

public class PriceDetails {
	private String ReferencePrice;
	private Saving saving;
	
	
	public PriceDetails() {
	}


	public PriceDetails(String referencePrice, Saving saving) {
		ReferencePrice = referencePrice;
		this.saving = saving;
	}


	public String getReferencePrice() {
		return ReferencePrice;
	}


	public void setReferencePrice(String referencePrice) {
		ReferencePrice = referencePrice;
	}


	public Saving getSaving() {
		return saving;
	}


	public void setSaving(Saving saving) {
		this.saving = saving;
	}
	
	
}
