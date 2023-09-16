package com.rest;

import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
//Assertthat will not execute the remaning statements if it gets failed
//Given return the requestspecefication interface reference
public class AutomateGet 
{

	@Test
	public void test()
	{
		given().
		baseUri("https://api.postman.com").
		header("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d").

		when().
		get("/workspaces").

		then().

		log().all().

		assertThat().

		statusCode(200);
	}

	@Test
	public void validate_reponse_body()
	{

		given().

		baseUri("https://api.postman.com").header("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d").

		when().

		get("/workspaces").


		then().

		log().all().
		assertThat().statusCode(200).
		body("workspaces.name", hasItems("My Workspace", "My Workspace2", "My Workspace1"), //expression inside commas are groovy gpath expressions
				"workspaces.type",hasItems("personal", "personal", "personal"),
				"workspaces[0].name",is(equalTo("My Workspace")),
				"workspaces.size()",equalTo(3),//here we are using groovy method and fetching the size of arraylist 
				"workspaces.name",hasItem("My Workspace2"));

	}

	@Test
	public void extract_response()       
	{

		Response res=  given().

				baseUri("https://api.postman.com").header("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d").

				when().

				get("/workspaces").

				then().
				assertThat().statusCode(200).extract().response();// this will return the response object having body, cookies and header


		System.out.println(res.asString());
		//System.out.println(res.path("workspaces[0].name"));// it will extract single value 
      
	}

	@Test
	public void extract_single_value_from_response() //alternative way to extract single value      
	{
		String res= given().

				baseUri("https://api.postman.com").header("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d").

				when().

				get("/workspaces").

				then().

				assertThat().statusCode(200).extract().response().asString();

		// or we can write also :  assertThat().statusCode(200).extract().response().path("workspaces[1].name")
		      System.out.println(JsonPath.from(res).getString("workspaces[1].name"));

    }
	
    @Test
	public void hamcresh_assertion_on_response()
	{
    	String name= given().

				baseUri("https://api.postman.com").
				header("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d").

				when().

				get("/workspaces").

				then().

				assertThat().statusCode(200).extract().response().path("workspaces[0].name");
    	
    	        assertThat(name,equalTo("My Workspace1"));
    	       
    	       //we can also use testNg assertions
    	       
    	    //   Assert.assertEquals(name,"My Workspace");
    	
    	   
    }
          









}
