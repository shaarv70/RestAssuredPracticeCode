package com.rest;

import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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

public class UsingDataProviderInPojo {
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
		responseSpecification = responsespecbuilder.build();

	}

    
	@Test(dataProvider ="workspace")
	public void Serialize_and_deserialize(String name,String type,String description) throws JsonProcessingException
	{
		Workspace workspace = new Workspace(name,type,description); 
		WorkspaceRoot object= new WorkspaceRoot(workspace);

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
	
	
	  @DataProvider(name="workspace")
	   public  Object[][] dataprovider()
	   {
		   return new Object[][] {
			   {"testing1","personal","description"},
			   {"testing2","personal","test"}
			   
			   };
	   }



}
