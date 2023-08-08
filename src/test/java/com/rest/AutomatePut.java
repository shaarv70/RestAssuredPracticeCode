package com.rest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AutomatePut {

	
	
	@BeforeClass
	public void before_class()
	{
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder();
		requestspecbuilder.setBaseUri("https://api.postman.com");
		requestspecbuilder.addHeader("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d");
		requestspecbuilder.setContentType(ContentType.JSON);
		//requestspecbuilder.log(LogDetail.ALL);
		RestAssured.requestSpecification = requestspecbuilder.build(); 
		//requestspecefication = requestspecbuilder.build(); 
		


		ResponseSpecBuilder responsespecbuilder= new ResponseSpecBuilder().
		expectStatusCode(200).
		expectContentType(ContentType.JSON).
		log(LogDetail.ALL);
	    RestAssured.responseSpecification = responsespecbuilder.build();
		//responsepecefication=responsespecbuilder.build();
		
		}
	
	
      @Test	
  /*    public void validate_post_request_bddStyle()
     {
    	  String payload= "{\r\n"
  		   		+ "    \"workspace\": {\r\n"
  		   		+ "        \"id\": \"f979a6dd-fa9e-4db2-9993-41ae45b48038\",\r\n"
  		   		+ "        \"name\": \"noworkspace\",\r\n"
  		   		+ "        \"type\": \"personal\",\r\n"
  		   		+ "        \"description\": \"This is your personal workspace to play around in. Only you can see the collections, APIs, and other entities you create here - unless you share them with your team\"\r\n"
  		   		+ "       \r\n"
  		   		+ "    }\r\n"
  		   		+ "}";
    	  
    	String workspaceId="f979a6dd-fa9e-4db2-9993-41ae45b48038";
    	 
    	given().body(payload).pathParam("workspaceId", workspaceId).
    	
    	when().put("/workspaces/{workspaceId}").
    	
    	then().
    	statusCode(200).
    	assertThat().
    	body("workspace.name",equalTo("noworkspace"),
    			                                 "workspace.id",matchesPattern("[a-z0-9-]{36}$"),
    			                                 "workspace.id",equalTo(workspaceId));
    	  
    	 
     }*/
	
      
      public void validate_post_request_non_bddStyle()
      {
    	  String payload= "{\r\n"
    		   		+ "    \"workspace\": {\r\n"
    		   		+ "        \"id\": \"f979a6dd-fa9e-4db2-9993-41ae45b48038\",\r\n"
    		   		+ "        \"name\": \"notmyworkspace\",\r\n"
    		   		+ "        \"type\": \"personal\",\r\n"
    		   		+ "        \"description\": \"This is your personal workspace to play around in. Only you can see the collections, APIs, and other entities you create here - unless you share them with your team\"\r\n"
    		   		+ "       \r\n"
    		   		+ "    }\r\n"
    		   		+ "}";
      	  
      	String workspaceId="f979a6dd-fa9e-4db2-9993-41ae45b48038";
      	 
    	  
    	  Response response= with().body(payload).pathParams("workspaceId", workspaceId).put("/workspaces/{workspaceId}");
    	  
    	  assertThat( response.<String>path("workspace.name"),equalTo("notmyworkspace"));    	  
    	  assertThat( response.<String>path("workspace.id"),equalTo(workspaceId));
    	  
    	  
      }
          
      
      
	
	
	

}
