package fr.eisti.pau.cdiscount.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;


public class CDiscountResponse {

	private String message;
	private Object content;
	private int code;
	
	public CDiscountResponse() {
		super();
	}

	public CDiscountResponse(String message, Object content, int code) {
		this.message = message;
		this.content = content;
		this.code = code;
	}
	
	public CDiscountResponse(Builder b){
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
	
	public static Response build(String message, Object content, int code){
		ResponseBuilder build = new ResponseBuilderImpl();
		CDiscountResponse.Builder entity = new CDiscountResponse.Builder();

		return build.entity(
				entity
				.message(message)
				.content(content)
				.code(code)
				.build()
				).build();
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
		
		public CDiscountResponse build(){
			return new CDiscountResponse(this);
		}
	}
	
}
