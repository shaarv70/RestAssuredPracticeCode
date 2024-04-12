package com.rest;

import static io.restassured.RestAssured.requestSpecification;
import java.util.List;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import static io.restassured.RestAssured.*;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

/* OAuth is used for authorization and not for authentication purpose if we want to use OAuth for authentication 
  purpose then we need to use openIDconnect , so OAuth with openIDconnect can be used for both authentication and authorization
 openID connect is an authorization scheme*/

public class OAuthDemo {
	
	
	ResponseSpecification responseSpecification;
    String access_token="ya29.a0AbVbY6OxeD7nPN4Yo8P5pEjuhLbYQfpL6JmNlFIq2aQ7gRe3qm-QBb8_w1I2HXCCs-VdAEmaqfRW1HZIY9tDWa3X63H0X8qfuvmYFma10Cr-k8fMi0h7Zupw2Bc7zef5enX8O48rxru8X6koCnoNQbBA097PoWWlaCgYKAfgSARISFQFWKvPliqUYrSui0fASd3UEGANC2Q0167";

	@BeforeClass
	public void before_class()
	{
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder();
		requestspecbuilder.setBaseUri("https://gmail.googleapis.com");
		requestspecbuilder.log(LogDetail.ALL);
		requestspecbuilder.addHeader("Content-Type","application/json;charset=utf-8");
		requestspecbuilder.addHeader("Authorization", "Bearer "+access_token);
		requestSpecification = requestspecbuilder.build(); 


        ResponseSpecBuilder responsespecbuilder= new ResponseSpecBuilder();
		responsespecbuilder.log(LogDetail.ALL);
		responsespecbuilder.expectContentType(ContentType.JSON);
		responsespecbuilder.expectStatusCode(200);
		responseSpecification = responsespecbuilder.build();

	}

	
/*	@Test
	public void get_userprofile()
	{
		given().
		basePath("/gmail/v1").
		pathParam("userID","sharmaarvindsharma935@gmail.com").
		
		when().
		get("/users/{userID}/profile").
		
		
		then().spec(responseSpecification).assertThat().body("messagesTotal",Matchers.greaterThan(1000));
		
		
	}
	
	
	@Test
	public void send_Message()
	{   
		String payload= "From: sharmaarvindsharma935@gmail.com\r\n"
				+ "To: arvindsharma50480@gmail.com\r\n"
				+ "Subject: RestAssured Test Email\r\n"
				+ "\r\n"
				+ "Sending from Gmail API testing";
		
		String base64urlencodedmess= Base64.getUrlEncoder().encodeToString(payload.getBytes());
		
		HashMap<String,String> mymap= new HashMap<>();
		mymap.put("raw", base64urlencodedmess);
		
		given().
		body(mymap).
		basePath("/gmail/v1").
		pathParam("userID","sharmaarvindsharma935@gmail.com").
		
		when().
		post("/users/{userID}/messages/send").
		
		
		then().spec(responseSpecification).assertThat();
		
}*/
	
	@Test
	public void List()
	{
		given().
		basePath("/gmail/v1").
		pathParam("userID","sharmaarvindsharma935@gmail.com").
		
		when().
		get("/users/{userID}/messages").
		
		
		then().spec(responseSpecification);
		
	}
	
	
	@Test
	public void get()
	{
		given().
		basePath("/gmail/v1").
		pathParam("userID","sharmaarvindsharma935@gmail.com").
		pathParam("id", "189b0ee5c6fc5bbd").
		
		when().
		get("/users/{userID}/messages/{id}").
		
		
		then().spec(responseSpecification);
		
	}
	
	
	@Test
	public void Delete()
	{
		given().
		basePath("/gmail/v1").
		pathParam("userID","sharmaarvindsharma935@gmail.com").
		pathParam("id", "189b00facedf35d2").
		
		when().delete("/users/{userID}/messages/{id}").
		
		
		
		then().spec(responseSpecification).statusCode(HttpStatus.SC_NO_CONTENT);
	}
	
	
	@Test
	public void modify()
	{   
		List<String> addlabelid= new ArrayList<>();
		addlabelid.add("INBOX");
		
		HashMap<String,List<String>> mymap= new HashMap<>();
		mymap.put("addLabelIds", addlabelid);
		
		given().
		basePath("/gmail/v1").
		pathParam("userID","sharmaarvindsharma935@gmail.com").
		pathParam("id", "189b0ee5c6fc5bbd").body(mymap).
		
		when().post("/users/{userID}/messages/{id}/modify").
		
		
		
		then().spec(responseSpecification).assertThat().body("threadId",Matchers.equalTo("189b0ee5c6fc5bbd"));
	}
	
	
	
	
	
	
	
	
	

}
