package complexPojo;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionResponse extends CollectionBase{
	
	List<FolderResponse> Item;
	
	public CollectionResponse() {
		
	}
	public CollectionResponse(Info info, List<FolderResponse> item) {
		super(info);
		this.Item = item;
	}
	
	public List<FolderResponse> getItem() {
		return Item;
	}
	public void setItem(List<FolderResponse> item) {
		Item = item;
	}

}
