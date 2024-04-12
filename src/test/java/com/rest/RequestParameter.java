package com.rest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class RequestParameter {
	
//Difference  bw path parameter and query parameter is that if I want to fetch a particular resource from the server then I will use 
// path param, but if I want to have some more filters on that filter like I want the employee having name as Arvind and also the size of the 
	//resource would be 10 then in that case we will use query param.
	
	@Test
	public void single_query_parameter()
	{
		
		given().
		baseUri("https://postman-echo.com").
		//param("foo1", "bar1").   -- below and this statment can be used for sending query parameter
		queryParam("foo1", "bar1").
		log().all().
		
		
		when().get("/get").
		
		
		then().log().all().assertThat().statusCode(200);
	}
	
	
	
	
	@Test
	public void multiple_query_parameter()
	{
		HashMap<String,String> query= new HashMap<String, String>();
		query.put("foo1","bar1");
		query.put("foo2", "bar2");
		
		
		given().
		baseUri("https://postman-echo.com").queryParams(query).log().all().
		/*	queryParam("foo1","bar1").
		queryParam("foo2","bar2"); we can use in this way or we can also pass through hashmap */
		
		
		when().get("/get").
		
		then().
		log().all().
		assertThat().statusCode(200);
	
		
	}
	
	
	
	
	
	@Test
	public void multivalue_query_parameter()
	{
		
		given().
		baseUri("https://postman-echo.com").
		queryParams("foo1","bar1,bar2,bar3").log().all().
		/*	queryParam("foo1","bar1;bar2;bar3"). we can write in this way also*/
		
		
		
		when().get("/get").
		
		then().
		log().all().
		assertThat().statusCode(200);
	
		
	}
	
	
	@Test
	public void path_parameter()
	{
		
		given().
		baseUri("https://reqres.in/").
		pathParam("userid", "2").log().all().
		
		
		when().get("/api/users/{userid}").
		
		then().
		log().all().
		assertThat().statusCode(200);
	
	}
	
	
	
	
	
	
	

}
