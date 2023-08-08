package com.rest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;

public class RequestPayloadasComplexJson 
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
		requestSpecification = requestspecbuilder.build(); 
        
		


		ResponseSpecBuilder responsespecbuilder= new ResponseSpecBuilder().
		expectStatusCode(200).
		expectContentType("application/json;charset=utf-8").
		log(LogDetail.ALL);
		responseSpecification = responsespecbuilder.build();
    }
	
	   @Test
	  public void request_payloadas_Json_array()
	  {   
		   List <Integer> myid= new ArrayList<Integer>();
		   myid.add(1);
		   myid.add(2);
		   
		   HashMap<String,Object> batter1= new HashMap<String, Object>();
		   batter1.put("id",myid);
		   batter1.put("type","personaltype");
		   
		   HashMap<String,Object> batter2= new HashMap<String, Object>();
		   batter2.put("id","401");
		   batter2.put("type","blazed");
		   
		   List<HashMap<String,Object>> batter= new ArrayList<HashMap<String,Object>>();
		   batter.add(batter1);
		   batter.add(batter2);
		   
		   HashMap<String,Object> batterjunior= new HashMap<String, Object>();
		   batterjunior.put("batter",batter);
		  
		   List<Integer> toppingsid= new ArrayList<Integer>();
		   toppingsid.add(3);
		   toppingsid.add(5);
		   toppingsid.add(6);
		   
		   List<String> toppingstype= new ArrayList<String>();
		   toppingstype.add("blazed");
		   toppingstype.add("olive");
		   
		   HashMap<String,Object> toppings1= new HashMap<String, Object>();
		   toppings1.put("id", toppingsid);
		   toppings1.put("type", toppingstype);
		   
		   HashMap<String,Object> toppings2 =new HashMap<String, Object>();
		   toppings2.put("id", "5000");
		   toppings2.put("type", "none");
		   
		   
		   List<HashMap<String,Object>> toppings= new ArrayList<HashMap<String,Object>>();
		   toppings.add(toppings1);
		   toppings.add(toppings2);
		  
		   HashMap <String,Object> main= new HashMap<String, Object>();
		   main.put("id","1000");
		   main.put("type","personal");
		   main.put("name","cake");
		   main.put("ppu","0.005");
		   main.put("batters",batterjunior);
		   main.put("toppings",toppings);
		   
		   
		   
		 given().
		 body(main).
		 
		 when().
		 post("/postcomplexJSon").
		 
		 then().spec(responseSpecification).
		 assertThat().body("mess",equalTo("Success"));
		 
		
	  }


	
	
	
}
