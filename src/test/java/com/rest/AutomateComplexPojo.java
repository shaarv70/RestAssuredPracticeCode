package com.rest;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import complexPojo.Body;
import complexPojo.CollectionRequest;
import complexPojo.CollectionrootRequest;
import complexPojo.CollectionrootResponse;
import complexPojo.FolderRequest;
import complexPojo.FolderResponse;
import complexPojo.Header;
import complexPojo.Info;
import complexPojo.ItemRequest;
import complexPojo.ItemResponse;
import complexPojo.RequestRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;

public class AutomateComplexPojo 
{

	ResponseSpecification responseSpecification;


	@BeforeClass
	public void before_class()
	{
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder();
		requestspecbuilder.setBaseUri("https://api.postman.com");
		//requestspecbuilder.addHeader("x-api-key","PMAK-65db8945408f690001de2542-116b40287d706ff099ccc8f69c5a80c733");
		requestspecbuilder.log(LogDetail.ALL);
		requestspecbuilder.addHeader("Content-Type","application/json;charset=utf-8");
		requestSpecification = requestspecbuilder.build(); 




		ResponseSpecBuilder responsespecbuilder= new ResponseSpecBuilder();
		responsespecbuilder.log(LogDetail.ALL);
		responseSpecification = responsespecbuilder.build();

	}


	@Test
	public void Serialize_and_deserialize() throws JsonProcessingException, JSONException
	{
		Header header=  new Header("Content-Type","application/json;charset=utf-8");
		List<Header> header1= new ArrayList<Header>();
		header1.add(header);
        Body body= new Body("raw","{\"data\":\"123\"}");
        RequestRequest request= new RequestRequest("https://postman-echo.com/post","POST",body,header1,"This is a sample post request");
        ItemRequest request1= new ItemRequest("Sample Post Request",request);
        List<ItemRequest> mylist= new ArrayList<ItemRequest>();
		mylist.add(request1);
        FolderRequest myfolder= new FolderRequest("This is my folder",mylist);
        List<FolderRequest> folderlist= new ArrayList<FolderRequest>();
		folderlist.add(myfolder);
        Info info= new Info("Sample Collection","This is just a sample collection","https://schema.getpostman.com/json/collection/v2.1.0/collection.json");
        CollectionRequest collection1 = new CollectionRequest(info,folderlist);
        CollectionrootRequest mycollection = new CollectionrootRequest(collection1);
        String collectionuid=
				given().
				body(mycollection).when().
				post("/collections").
                then().spec(responseSpecification).extract().response().path("collection.uid");	

		CollectionrootResponse deserialzed = 
				given().
				pathParam("collectionuid",collectionuid).

				when().
				get("/collections/{collectionuid}").

				then().spec(responseSpecification).
				extract().response().as(CollectionrootResponse.class);

		ObjectMapper objectmapper = new ObjectMapper();

		String initial= objectmapper.writeValueAsString(mycollection);
		String after =objectmapper.writeValueAsString(deserialzed);
        
//JSONAseert library can be used to compare whole serialized and deserialized request and response string but they both are need to be in
		// string thats why we have converted them to String with the help of Object Mapper and also in JsonAsset we can exclude thr fields
		//which have different datatype in both request and response
		JSONAssert.assertEquals(initial, after, 
				new CustomComparator(JSONCompareMode.STRICT,  //it will check the order of reponse in the same order
				new Customization("collection.item[*].item[*].request.url",(o1,o2)->{return true;}//here we have used * for the list because in these list also maybe url also present, so to specify our url we have mention in this way
			)));
		
			List<String> URLRequestList= new ArrayList<>();
			List<String> URLResponseList=new ArrayList<>();
			
			for(ItemRequest itemrequest:mylist)
			{
				System.out.println("URL from request payload"+itemrequest.getRequest().getUrl().toString());
				URLRequestList.add(itemrequest.getRequest().getUrl().toString());
			}
		
			List<FolderResponse>folderresponselist= deserialzed.getCollection().getItem();
			for (FolderResponse myfolderresponse:folderresponselist)
			{
				List<ItemResponse>itemreponselist= myfolderresponse.getItem();
				for (ItemResponse itemresponse:itemreponselist)
				{
					System.out.println("URL from response payload"+itemresponse.getRequest().getUrl().getRaw());
					URLResponseList.add(itemresponse.getRequest().getUrl().getRaw());
				}
				
				assertThat(URLRequestList,containsInAnyOrder(URLResponseList.toArray()));
				
				
				
			}

			/* we can also compare two json responses by the below approach
			 * JSONObject json1 = new JSONObject(response1.asString()); JSONObject json2 =
			 * new JSONObject(response2.asString());
			 * 
			 * // Compare the JSON objects try { JSONAssert.assertEquals(json1, json2,
			 * false); System.out.println("The JSON responses are equal."); } catch
			 * (AssertionError e) { System.out.println("The JSON responses are not equal: "
			 * + e.getMessage()); }
			 */




	}

}
