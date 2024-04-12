package complexPojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public abstract class RequestBase {
	 
	private String method;
	Body body;
	List<Header> header;
	private String description;
	
	
	protected RequestBase(String method, Body body, List<Header> header, String description) {
		
		
		this.method = method;
		this.body = body;
		this.header = header;
		this.description = description;
	}
	
	protected RequestBase() {
		
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
	public List<Header> getHeader() {
		return header;
	}
	public void setHeader(List<Header> header) {
		this.header = header;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
