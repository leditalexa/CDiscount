package fr.eisti.pau.cdiscount.util;


public class ResponseEntity {

	private String message;
	private Object content;
	private int code;
	
	public ResponseEntity() {
		super();
	}

	public ResponseEntity(String message, Object content, int code) {
		this.message = message;
		this.content = content;
		this.code = code;
	}
	
	public ResponseEntity(Builder b){
		this.message = b.message;
		this.content = b.content;
		this.code = b.code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public static class Builder{
		private String message;
		private Object content;
		private int code;
		
		public Builder(){
		}
		
		public Builder message(String message){
			this.message = message;
			return this;
		}
		
		public Builder content(Object content){
			this.content = content;
			return this;
		}
		
		public Builder code(int code){
			this.code = code;
			return this;
		}
		
		public ResponseEntity build(){
			return new ResponseEntity(this);
		}
	}
	
}
