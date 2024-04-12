package com.rest;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class StaticImports 
{
    @Test
	public void test()

	{
		given().

		         baseUri("https://api.postman.com").header("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d").     
		when().

		         get("/workspaces").
		then().

		         statusCode(200).
		         body("name", is(equalTo("Arvind")),"email",is(equalTo("arvindsharma50480@gmail.com")));

	}



}
