package complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class FolderBase {

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

public FolderBase(String name) {
		
		this.name = name;
	}

	public FolderBase() {
		
	}
	
	
	
	
   



}
