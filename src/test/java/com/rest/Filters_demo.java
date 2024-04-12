package com.rest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Filters_demo {

//we can use filters in place of logging	
	
	RequestSpecification requestSpecification; 
	ResponseSpecification responseSpecification;
	
	@BeforeClass
	public void before_class() throws FileNotFoundException
	{
		PrintStream FileoutputStream = new PrintStream(new File("restAssured.Log"));
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder();
		requestspecbuilder.addFilter(new RequestLoggingFilter(FileoutputStream));
		requestspecbuilder.addFilter(new ResponseLoggingFilter(LogDetail.STATUS,FileoutputStream));
		requestspecbuilder.setContentType("application/json;charset=UTF-8");
		requestSpecification = requestspecbuilder.build(); 
        
		


		ResponseSpecBuilder responsespecbuilder= new ResponseSpecBuilder();
		responseSpecification = responsespecbuilder.build();
        
	}
	
	
	
	
	
	
/*	@Test
	public void logging_filtger()
	{
		given().baseUri("https://postman-echo.com").
		filter(new RequestLoggingFilter(LogDetail.BODY)).     //if we put empty arguments then whole request and response will be shown 
		filter(new ResponseLoggingFilter(LogDetail.STATUS)).
		
		
		when().
		get("/get").
		
		then().
		assertThat().
		statusCode(200);
		
	}*/
	
	
	
	
/*	@Test
	public void logging_filter_toLogFile() throws FileNotFoundException
	{    
		PrintStream FileoutputStream = new PrintStream(new File("restAssured.Log"));
		given().baseUri("https://postman-echo.com").
		//filter(new RequestLoggingFilter(FileoutputStream)).//Here it will print the complete request and response to the log file   
	
		filter(new RequestLoggingFilter(LogDetail.BODY,FileoutputStream)).
		filter(new ResponseLoggingFilter(LogDetail.STATUS,FileoutputStream)).
		
		
		when().
		get("/get").
		
		then().
		assertThat().
		statusCode(200);
		
	}*/
	
	
	
	@Test
	public void logging_filter_resusability()
	{    
		
		given(requestSpecification).
		baseUri("https://postman-echo.com").
		formParam("foo1","value1").
		
		
		when().get("/get").
	   
	    then().spec(responseSpecification).
		assertThat().
		statusCode(200);
		
	}

	
	
	

}
