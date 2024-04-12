package com.rest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class AutomateDelete {
	
	
	@BeforeClass
	public void before_class()
	{
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder();
		requestspecbuilder.setBaseUri("https://api.postman.com");
		//requestspecbuilder.addHeader("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d");
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
	
	
	/*    @Test
	    public void delete_bddStyle()
	    {
	    	String workspaceID="f979a6dd-fa9e-4db2-9993-41ae45b48038";
	    	
	    	given().pathParam("workspaceID", workspaceID).
	    	
	    	when().delete("/workspaces/{workspaceID}").
	    	
	    	then().statusCode(200).
	    	assertThat().
	    	body("workspace.id",equalTo(workspaceID));
	    	
	    }*/
	    
	    
	    
	
	    @Test
	    public void validate_delete_non_bddStyle()
	    {
	    	String workspaceID="9543f693-3f29-458b-8981-cc4d793c28ba";	
	    	Response response=with().pathParam("workspaceID", workspaceID).delete("/workspaces/{workspaceID}");
	    	
	    	assertThat(response.<String>path("workspace.id"),equalTo(workspaceID));
	    }

}
