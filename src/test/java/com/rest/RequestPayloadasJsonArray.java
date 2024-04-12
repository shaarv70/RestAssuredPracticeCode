package com.rest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class RequestPayloadasJsonArray 
{   
	 ResponseSpecification responseSpecification;
//RestAssured supports content type as encoding  	
	
	@BeforeClass
	public void before_class()
	{
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder();
		requestspecbuilder.setBaseUri("https://e9a0c3c8-2904-4397-a3d3-434df4e1973c.mock.pstmn.io");
		requestspecbuilder.addHeader("x-mock-match-request-body","true");
	  //  requestspecbuilder.setConfig(config.encoderConfig(EncoderConfig.
	//  encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));//this line means we are setting the 
	    //default content type as false and setting the restassured supported encoded type format
		requestspecbuilder.setContentType("application/json;charset=utf-8");//we can also use the commented part
	    requestspecbuilder.log(LogDetail.ALL);
		RestAssured.requestSpecification = requestspecbuilder.build(); 
        
		


		ResponseSpecBuilder responsespecbuilder= new ResponseSpecBuilder().
		expectStatusCode(200).
		expectContentType(ContentType.JSON).
		log(LogDetail.ALL);
		responseSpecification = responsespecbuilder.build();
    }
	
	   @Test
	  public void request_payloadas_Json_array()
	  {   
		 HashMap<String,String> obj1= new HashMap<String, String>();
		 obj1.put("id","901");
		 obj1.put("type","personal");
		 
		 HashMap<String,String> obj2= new HashMap<String, String>();
		 obj2.put("id","902");
		 obj2.put("type","personal");
		 
		 List<HashMap<String,String>> mainlist= new ArrayList<HashMap<String,String>>();
		 mainlist.add(obj1);
		 mainlist.add(obj2);
		  
		 given().
		 body(mainlist).
		 
		 when().
		 post("/post").
		 
		 then().spec(responseSpecification).
		 assertThat().statusCode(200).body("mess",equalTo("successful"));
		 
		
	  }


	
	
	
}
