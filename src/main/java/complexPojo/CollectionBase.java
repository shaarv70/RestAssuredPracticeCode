package complexPojo;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CollectionBase {
	
	Info info;
	
	
	public CollectionBase() {
		
	}
	public CollectionBase(Info info ) {
		super();
		this.info = info;
		
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	
}
