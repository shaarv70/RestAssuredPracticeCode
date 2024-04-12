package com.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.util.Collections;
import org.testng.annotations.Test;

public class HamcrestMethods
{
	
	@Test
	public void hamcrest_Methods_Learning()
	{

		given().

		baseUri("https://api.postman.com").header("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d").

		when().

		get("/workspaces").


		then().

		log().all().
		assertThat().statusCode(200).
		
		//body("workspaces.name",contains("My Workspace", "My Workspace2", "My Workspace1"));
		//body("workspaces.name",empty());//failed
		body("workspaces.name",containsInAnyOrder("My Workspace1", "My Workspace2", "My Workspace"),
		     "workspaces.name",is(not(emptyArray())),
		     "workspaces.name",hasSize(3), //here we are using hamcrest method
		     "workspaces.name",everyItem(startsWith("My")),
		     "workspaces[0]",hasKey("id"),
		     "workspaces[1]",hasValue("My Workspace2"),
		    "workspaces[0]",hasEntry("id","6ff1a213-616e-4e87-8852-afc8b8b79a26" ),
		    "workspaces[0]",not(equalTo(Collections.EMPTY_MAP)),
		    "workspaces[0].name",allOf(startsWith("My"),containsString("My Workspace")));
		    
		
		
		 //  empty()-It is only used with collections, if we want to use with string then emptyString() or isemptyString() can be used  
		//hasItems()- this method will check only for the those elements and will not look on other elements in collection
		//contains()- will check only for those elements and their order also 
	     //containsInAnyOrder- will check only for the elements but not their order
		//hasKey()- it will check whthere the object has id or not 
		//allof()- here all methods of matchers should pass and it works only on strings
		//anyof()- here one of the matcher should match and it works only on strings
		
			
	
	
}}
