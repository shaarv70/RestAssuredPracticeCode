package NestedPojo;

import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


/*   deserialization: allowsetters (If property to be used after desrialization but not during serialization then will set allowsetters as true) 
     serialization: allowgetter   (If property to be used during serialization but not during deserialization then will set allowgetters as true) 
 
 */



//Below jackson annotatations we can not only use to class level but also to field level or property level
//@JsonInclude(JsonInclude.Include.NON_NULL)/*Since the id value in request payload is going as null as we are not setting its value, so to not include those values which have null values we use this annotation,*/ 
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)/*Since the value of i by default is 0 and not null so to not include the default vaues we use  */                                           

@JsonIgnoreProperties(value = "id",allowSetters = true)// this will allow id to deserialize  but ignore in case of serialization 
//@JsonIgnoreProperties(value = {"id","i"},allowSetters = true,allowGetters = false)//we can also use multiple properties

public class Workspace {
	
//JsonIgnore will directly ignore th property during serialization and de serialization	
//	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@JsonIgnore   //since i is unused we can also use @JsonIgnore at field level or we can also use with methods
	private int i; 
	
//	@JsonInclude(JsonInclude.Include.NON_NULL)// we can use non empty for non  null also since empty is a parent of null 
	private String id;// we cant use JsonIgnore with id since id is coming in response body during deserialization,although it is not present in serialization process
	
//	@JsonInclude(JsonInclude.Include.NON_EMPTY)/*Since we are passing the empty hashmap in main class so to not include the empty map in payload */
	@JsonIgnore 
	private HashMap<String,String> myhashmap;
	
	private String name;
	private String type;
	private String description;
	
	public HashMap<String, String> getMyhashmap() {
		return myhashmap;
	}

	public void setMyhashmap(HashMap<String, String> myhashmap) {
		this.myhashmap = myhashmap;
	}
	
    @JsonIgnore
	public int getI() {
		return i;
	}

    @JsonIgnore
	public void setI(int i) {
		this.i = i;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Workspace() {
		
		
	}
	
	public Workspace(String name, String type, String description) {
		this.name = name;
		this.type = type;
		this.description = description;
	}
	
	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
