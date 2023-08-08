package complexPojo;

import java.util.List;
import complexPojo.Url;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestResponse extends RequestBase {
	
	Url url;     
	
	
	public RequestResponse(Url url, String method, Body body, List<Header> header, String description) {
		
		super(method,body,header,description);
		this.url = url;
		
	}
	
	public RequestResponse() {
		
	}

	public Url getUrl() {
		return url;
	}
	

}
