package com.rest;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class NonStaticImports

{
	@Test
	public void test()
	{
		RestAssured.given()
		 
		             .baseUri("https://api.postman.com").header("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d").     //here we are using non static imports thats why classname has to be used
		when().
		         
		              get("/workspaces").
		then().
		      
		             statusCode(200)
		            .body("name", Matchers.is(Matchers.equalTo("Arvind")),"email",Matchers.is(Matchers.equalTo("arvindsharma50480@gmail.com")));
				
		      
	}

}
