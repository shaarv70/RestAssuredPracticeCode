package com.rest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchema {
	
	@Test
	public void json_schema_demo()
	{
		
		given().
		baseUri("https://postman-echo.com").
		log().all().
		
		
		when().get("/get").
		
		
		then().
		log().all().
		assertThat().statusCode(200).assertThat().
		body(matchesJsonSchemaInClasspath("JsonSchemaFile.json"));
		
		
		
		
	}

}
