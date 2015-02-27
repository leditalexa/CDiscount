package fr.eisti.pau.cdiscount.domain.wine;

public class Saving {
	private String Type;
	private String Value;
	
	public Saving() {
		super();
	}

	public Saving(String type, String value) {
		Type = type;
		Value = value;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}
	
}
