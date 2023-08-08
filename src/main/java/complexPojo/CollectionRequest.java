package complexPojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionRequest extends CollectionBase
{
	List<FolderRequest> Item;
	
	public CollectionRequest() {
		
	}
	public CollectionRequest(Info info,List<FolderRequest> item) {
		super(info);
		this.Item = item;
	}
	
	public List<FolderRequest> getItem() {
		return Item;
	}
	public void setItem(List<FolderRequest> item) {
		Item = item;
	}

}
