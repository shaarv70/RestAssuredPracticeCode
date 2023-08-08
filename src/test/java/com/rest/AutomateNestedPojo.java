package com.rest;

import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import NestedPojo.Workspace;
import NestedPojo.WorkspaceRoot;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class AutomateNestedPojo {
	ResponseSpecification responseSpecification;


	@BeforeClass
	public void before_class()
	{
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder();
		requestspecbuilder.setBaseUri("https://api.postman.com");
		requestspecbuilder.addHeader("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d");
		requestspecbuilder.setContentType(ContentType.JSON);
		requestspecbuilder.log(LogDetail.ALL);
		requestSpecification = requestspecbuilder.build(); 




		ResponseSpecBuilder responsespecbuilder= new ResponseSpecBuilder();
		responsespecbuilder.log(LogDetail.ALL);
		responseSpecification = responsespecbuilder.build();

	}


	@Test
	public void Serialize_and_deserialize() throws JsonProcessingException
	{
		Workspace workspace = new Workspace("testingPojo","personal","for testing"); 
		WorkspaceRoot object= new WorkspaceRoot(workspace);
       HashMap<String,String> mymap= new HashMap<String,String>();
       workspace.setMyhashmap(mymap);// setting the value of hashmap as empty
		
		WorkspaceRoot after= given().

				body(object).
				
				when().
				post("/workspaces").
				
				then().spec(responseSpecification).
				statusCode(200).
				extract().response().as(WorkspaceRoot.class);
		 
		assertThat(workspace.getName(),equalTo(after.getWorkspace().getName()));
		assertThat(after.getWorkspace().getId(), matchesPattern("[a-z0-9-]{36}$"));		

}



}
