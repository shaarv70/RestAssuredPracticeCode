package complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//this will be used in case if we do have some data members define in our pojo 
public class Info {                          //but some extra things coming in response, so this ignore all the unknown properties coming
	                                         //in response but we do not have here 

	private String name;
	private String description;
	private String Schema;
	
	
	public Info() {

	}
	
	
	public Info(String name, String description, String schema) {
		this.name = name;
		this.description = description;
		this.Schema = schema;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSchema() {
		return Schema;
	}
	public void setSchema(String schema) {
		Schema = schema;
	}
}
