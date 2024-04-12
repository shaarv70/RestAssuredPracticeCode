package complexPojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Url {
	
	private String raw;
	private String protocol;
	private List<String>host;
	
	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public List<String> getHost() {
		return host;
	}

	public void setHost(List<String> host) {
		this.host = host;
	}

	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}

	private List<String>path;
	
	public Url(String raw, String protocol, List<String> host, List<String> path) {
		super();
		this.raw = raw;
		this.protocol = protocol;
		this.host = host;
		this.path = path;
	}

	public Url() {
		super();
		
	}

	
	
}
