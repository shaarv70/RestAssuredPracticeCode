package complexPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionrootRequest extends CollectionrootBase 
{
	
	CollectionRequest collection;

	public CollectionrootRequest(CollectionRequest collection) {
		
		this.collection = collection;
	}

	public CollectionrootRequest() {
		
	}

	public CollectionRequest getCollection() {
		return collection;
	}

	public void setCollection(CollectionRequest collection) {
		this.collection = collection;
	} 

}
