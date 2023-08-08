package com.rest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import SimplePojoPackage.SimplePojo;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;

import io.restassured.specification.ResponseSpecification;

public class PojoDemo
{
	
	 
	ResponseSpecification responseSpecification;

	@BeforeClass
	public void before_class()
	{ 
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder();
		requestspecbuilder.setBaseUri("https://e9a0c3c8-2904-4397-a3d3-434df4e1973c.mock.pstmn.io");
	
		requestspecbuilder.log(LogDetail.ALL);
		
		requestSpecification= requestspecbuilder.build();


		ResponseSpecBuilder responsespecbuilder= new ResponseSpecBuilder();
		responseSpecification = responsespecbuilder.build();

	    

}
	
 /*   @Test       
	public void Pojo_demo_serialization()
	{
	   //SimplePojo object = new SimplePojo("value1","value2");// we can also use setter method for setting the  value
    	SimplePojo object = new SimplePojo();
    	object.setKey1("value1");
    	object.setKey2("value2");
    	
         given().
         body(object).
         
         when().
         post("/postsimpleJson").
         
         then().spec(responseSpecification).
         assertThat().
         statusCode(200).
         body("key1", equalTo(object.getKey1()),
        		 "key2",equalTo(object.getKey2()));
       /*  body("key1",equalTo("value1"),
        	  "key2",equalTo("value2"));  	   
    	 or */
         
  //   }  


    
    @Test       
   	public void Pojo_demo_deserialization() throws JsonProcessingException
   	{
   	   SimplePojo object = new SimplePojo("value1","value2"); 
      
       	
           SimplePojo deserialized= given().
            body(object).
            
            when().
            post("/postsimpleJson").
            
            then().spec(responseSpecification).
            assertThat().
            statusCode(200).
            extract().response().
            as(SimplePojo.class);  //deserialization of json into pojoclass object
              
   			ObjectMapper objectmapper= new ObjectMapper();
   			
   			   String deserializedstring= objectmapper.writeValueAsString(deserialized);
   			   String objectstring= objectmapper.writeValueAsString(object);
   			   
   			   assertThat(objectmapper.readTree(objectstring),equalTo(objectmapper.readTree(deserializedstring)));
   			   
   			   
        
            
        }

        

}
