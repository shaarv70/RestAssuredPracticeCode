package com.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;
import static io.restassured.RestAssured.*;

//Responsespecification is also an interface
//Before class will work in the same way as requestspecefication

public class ResponseSpecificationDemo {
	
 //  ResponseSpecification responsepecefication;
	
	@BeforeClass
	public void before_class()
	{
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder();
		requestspecbuilder.setBaseUri("https://api.postman.com");
		requestspecbuilder.addHeader("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d");
		requestspecbuilder.log(LogDetail.ALL);
		RestAssured.requestSpecification = requestspecbuilder.build(); // this will work with test4() below

		
	//	responsepecefication= RestAssured.expect().statusCode(200).contentType(ContentType.JSON).logDetail(LogDetail.ALL);
		
		ResponseSpecBuilder responsespecbuilder= new ResponseSpecBuilder().
				expectStatusCode(200).
				expectContentType(ContentType.JSON).
				log(LogDetail.ALL);
	
	//	responsepecefication=responsespecbuilder.build();
		
		
		//Default response specefication
		RestAssured.responseSpecification= responsespecbuilder.build();
		
	}
	
	
	// we can't use then(responsepecefication) same like given(requestpecefication) because we don have then then() overloaded
	
	
/*	@Test
	public void validate_statuscode()
	{   
		get("/workspaces").
		then().spec(responsepecefication);            //Response is an interface
		      
	}
	
	
	@Test
	public void validate_responsebody()
	{   
		Response response= get("/workspaces").then().spec(responsepecefication).extract().response();               
		
		assertThat(response.path("workspaces[0].name").toString(),equalTo("My Workspace"));
		
	}
*/	
	
	@Test
	public void default_test()
	{   
		Response response= get("/workspaces").then().extract().response();               
		
		assertThat(response.path("workspaces[0].name").toString(),equalTo("My Workspace"));
		
	}
	
	
	
	@Test
	public void default_test_first()    //First method with default response
	{   
		 get("/workspaces") ;          
		
    }
	

	
	
	
	
	
	
	
	
	

}
