package complexPojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class RequestRequest extends RequestBase{
	
	private String url; //Since we have separate classes for request thats why here we are passing url as string as it will go in request payload
	
	
	public RequestRequest(String url, String method, Body body, List<Header> header, String description) {
	   
		super(method,body,header,description);
		this.url = url;
		
	}
	
	public RequestRequest() {
		
	}

	public Object getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
