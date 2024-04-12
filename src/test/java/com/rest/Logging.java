package com.rest;

import static io.restassured.RestAssured.*;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import io.restassured.config.LogConfig;


public class Logging 
{
//	@Test
/*	public void logging()
	{
		given().

		baseUri("https://api.postman.com").
		header("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d").
		log().ifValidationFails().// if validation faild in response body then it will log 
		//log().headers().//if we want to log only headers in request
		//log().all().
		//log().cookies().
		//log().body().we can use these logs in request 
		//log().params(). 
		when().

		get("/workspaces").


		then().
		log().ifValidationFails().// if validation faild in response body then it will log
		//log().ifError().    //this will log response header & body if there is an error in executing request body

		//log().body().  //if we want to log only body in response
		// log().all().
		//log().cookies().//if we want to log only cookies in response

		assertThat().statusCode(200);



	}*/

	@Test
	public void log_only_if_validation_fails()   //alternagtive way if we dont want to write log().ifValidationFails(). in boith request and response body
	{

		given().
        baseUri("https://api.postman.com").
		header("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d").
		config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
		
		when().
        get("/workspaces").


		then().
		assertThat().statusCode(200);

}

	     @Test
	     public void BlackListing_headers()   //if we do not want to show any sensitive information of header in request body like api key
	     {
	    	given().
	         baseUri("https://api.postman.com").
	 		header("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d").
	  		config(config.logConfig(LogConfig.logConfig().blacklistHeader("x-api-key"))).//here we have passed only xapikey, we can send collection also
	        log().all().  	
	 		
	 		when().
	         get("/workspaces").


	 		then().
	 		assertThat().statusCode(200);
	    	 
	     }
	     
	     @Test
	     public void BlackListing_headers_Using_collection()   //if we do not want to show any sensitive information of header in request body like api key
	     {
	    	Set<String> header= new HashSet<String>();
	    	header.add("x-api-key");
	    	header.add("Accept");
	    	given().
	        baseUri("https://api.postman.com").
	 		header("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d").
	  		config(config.logConfig(LogConfig.logConfig().blacklistHeaders(header))).
	        log().all().  	
	 		
	 		when().
	         get("/workspaces").


	 		then().
	 		assertThat().statusCode(200);
	    	 
	     }
	
	






}
