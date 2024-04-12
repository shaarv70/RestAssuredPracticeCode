package com.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import static io.restassured.RestAssured.*;

//rEQUESTsPECEFICATION is used to move from BDD to non BDD style

public class RequestSpecificationDemo {
	
   RequestSpecification requestspecefication;
	
	@BeforeClass
	public void before_class()
	{
		//First way - this will work where dont need to use given()(test 2)
		
		       // requestspecefication= given().
		/*        requestspecefication= with().
		        baseUri("https://api.postman.com").
				header("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d");
        */
		
		//Second way - this will work in case of given().spec(requestspecefication) or only given(requestspecefication)
		
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder();
		requestspecbuilder.setBaseUri("https://api.postman.com");
		requestspecbuilder.addHeader("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d");
		requestspecbuilder.log(LogDetail.ALL);
	//	requestspecefication = requestspecbuilder.build();    
		
		
	/*	we can write in this way also
		requestspecbuilder.setBaseUri("https://api.postman.com").addHeader(null, null).log(null) we can write in this way also
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder().setBaseUri("https://api.postman.com").addHeader(null, null).log(null)*/
		
		
		
		
		//Default specefic requsition(if in case of multiple specefic requisition)
		RestAssured.requestSpecification = requestspecbuilder.build(); // this will work with test4() below where dont need to use given()
		
	}
	
	
	
	@Test
	public void test1()
	{   //Since given is returning  the requestspecefication interface reference then we can write 
		       
		//given(requestspecefication).  //we can write in this way also
		given().spec(requestspecefication).//alternative way //work in case of requestspecbuilder
		header("Dummy header","dummy"). 
		
		
		when().
		get("/workspaces").

		then().
        log().all().
        assertThat().
        statusCode(200);
		
	}
	
	
	@Test
	public void test2()
	{   //work in case of requestspecification
		
		Response response= requestspecefication.get("/workspaces").then().log().all().extract().response(); //Response is an interface
		assertThat(response.statusCode(),is(equalTo(200)));
		
	}
	
	
	@Test
	public void test3()
	{   
		Response response= requestspecefication.get("/workspaces").then().log().all().extract().response();  //Response is an interface
		assertThat(response.statusCode(),is(equalTo(200)));
		assertThat(response.path("workspaces[0].name").toString(),equalTo("My Workspace"));
		
	}
	
	
	@Test
	public void test4()
	{   
		//Default specefic requsition case
		Response response= get("/workspaces").then().log().all().extract().response();              
		assertThat(response.statusCode(),is(equalTo(200)));
		assertThat(response.path("workspaces[0].name").toString(),equalTo("My Workspace"));
		
	}
	
	
	@Test
	public void query_test()
	{ // if we want to fetch any value from requestspecification then we can use this  
		
		
		QueryableRequestSpecification queryablerequestspecification = SpecificationQuerier.query(RestAssured.requestSpecification);
		System.out.println(queryablerequestspecification.getBaseUri());
		System.out.println(queryablerequestspecification.getHeaders());
	}

	
	
	
	
	
	
	
	
	

}
