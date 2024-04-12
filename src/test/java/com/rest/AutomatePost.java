package com.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

//Responsespecification is also an interface
//Before class will work in the same way as requestspecefication

public class AutomatePost {



	@BeforeClass
	public void before_class()
	{
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder();
		requestspecbuilder.setBaseUri("https://api.postman.com");
		requestspecbuilder.addHeader("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d");
		requestspecbuilder.setContentType(ContentType.JSON);
	
		//requestspecbuilder.log(LogDetail.ALL);
		RestAssured.requestSpecification = requestspecbuilder.build(); 
        
		


		ResponseSpecBuilder responsespecbuilder= new ResponseSpecBuilder().
		expectStatusCode(200).
		expectContentType(ContentType.JSON).
		log(LogDetail.ALL);
		RestAssured.responseSpecification = responsespecbuilder.build();
        
	}

   
	/*   @Test
	   public void post_request_BDDStyle()
	   {
		   String payload= "   {\r\n"
		   		+ "    \"workspace\": {\r\n"
		   		+ "        \"id\": \"6ff1a213-616e-4e87-8852-afc8b8b79a26\",\r\n"
		   		+ "        \"name\": \"My Workspace3\",\r\n"
		   		+ "        \"type\": \"personal\",\r\n"
		   		+ "        \"description\": \"This is your personal workspace to play around in. Only you can see the collections, APIs, and other entities you create here - unless you share them with your team\"\r\n"
		   		+ "       \r\n"
		   		+ "    }\r\n"
		   		+ "}";
		   
		   
		   
		   given().
		   body(payload).
		   
		   when().
		   post("/workspaces").
		      
		    
		   then().
		   assertThat().body("workspace.name", equalTo("My Workspace3"),"workspace.id",matchesPattern("[a-z0-9-]{36}$"));
		   
		   
		}
	   
	   
	   
	   
	   @Test
	   public void post_request_non_BDDStyle()
	   {
		   String payload= "   {\r\n"
			   		+ "    \"workspace\": {\r\n"
			   		+ "        \"id\": \"6ff1a213-616e-4e87-8852-afc8b8b79a26\",\r\n"
			   		+ "        \"name\": \"My Workspace4\",\r\n"
			   		+ "        \"type\": \"personal\",\r\n"
			   		+ "        \"description\": \"This is your personal workspace to play around in. Only you can see the collections, APIs, and other entities you create here - unless you share them with your team\"\r\n"
			   		+ "       \r\n"
			   		+ "    }\r\n"
			   		+ "}";
		   
		  Response response= with().body(payload).post("/workspaces");
		  assertThat(response.<String>path("workspace.name"),equalTo("My Workspace4"));
		  assertThat(response.<String>path("workspace.id"),matchesPattern("[a-z0-9-]{36}$"));
		   
		   
	   }
	   
	   
	   
	   @Test
	   public void post_request_non_BDDStyle_usingfile()
	   {
	   
	         File file= new File("src\\test\\resources\\CreateWorkspacePayload.json");
	         
	        Response response= with().body(file).post("/workspaces");
	        
	         assertThat(response.<String>path("workspace.name"),equalTo("My Workspace4"));
			 assertThat(response.<String>path("workspace.id"),matchesPattern("[a-z0-9-]{36}$"));
	   
	    }*/
	   
	   
	   @Test
	   public void post_request_payload_asMap()
	   {
		   
		    HashMap<String,Object> mainobj= new HashMap<String, Object>();
		    
		    HashMap<String, String> childobj= new HashMap<String, String>();
		    childobj.put("name", "testworkspace");
		    childobj.put("type", "personal");
		    childobj.put("description","test workspace for testing");
		    mainobj.put("workspace",childobj);
		    
		    
		    with().body(mainobj).post("/workspaces");
		    
		   
	   }
	   
	   
	   
	
	
}	