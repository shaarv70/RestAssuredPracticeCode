package complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionrootResponse extends CollectionrootBase 
{
	
	CollectionResponse collection;

	public CollectionrootResponse(CollectionResponse collection) {
		
		this.collection = collection;
	}

	public CollectionrootResponse() {
		
	}

	public CollectionResponse getCollection() {
		return collection;
	}

	public void setCollection(CollectionResponse collection) {
		this.collection = collection;
	} 

}
