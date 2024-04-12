package com.rest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

/* If we do not use static imports then we have to use RestAssured classname alongwith all the methods which will result in 
 less readiblity in this way- 
       RestAssured.given().
		when().
		then();
  */
public class TestRest
{
	
	@Test
	public void test()
	{
		given().
		when().
		then();
	}

}
