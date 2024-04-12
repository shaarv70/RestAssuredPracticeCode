package com.rest;

import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/*here we are using objectmapper class to convert java object (hashmap in this case ) to Json (like we are using explicitly)
Restassured is not performing serialization, normally restassured perfom serialization internally for us using jackson databind dependency*/
//Serialization:Converting of Java object Json/Xml object through serializers (JAckson,Gson,JaxB etc.)
public class SerializationDemo {
	RequestSpecification requestSpecification; 
	ResponseSpecification responseSpecification;

	@BeforeClass
	public void before_class()
	{ 
		RequestSpecBuilder requestspecbuilder = new RequestSpecBuilder();
		requestspecbuilder.setBaseUri("https://e9a0c3c8-2904-4397-a3d3-434df4e1973c.mock.pstmn.io");
	//	requestspecbuilder.setBaseUri("https://api.postman.com");
		requestspecbuilder.addHeader("x-api-key","PMAK-649e4ac06fcfef196e4bdf45-d4e9e21189547e439427e428e4559d338d");
		requestspecbuilder.log(LogDetail.ALL);
		requestSpecification= requestspecbuilder.build();


		ResponseSpecBuilder responsespecbuilder= new ResponseSpecBuilder();
		responseSpecification = responsespecbuilder.build();



	}


	/*  @Test
	   public void post_request_payload_asMap() throws JsonProcessingException
	   {      
              //this example will work with url:https://api.postman.com
		    HashMap<String,Object> mainobj= new HashMap<String, Object>();

		    HashMap<String, String> childobj= new HashMap<String, String>();
		    childobj.put("name", "testworkspace");
		    childobj.put("type", "personal");
		    childobj.put("description","test workspace for testing");
		    mainobj.put("workspace",childobj);

		    ObjectMapper objectmapper= new ObjectMapper();
		    String mainobjectstr= objectmapper.writeValueAsString(mainobj);// this method converts java object(maybe map,list etc) to Json object and return Json object as string 

		    given(requestSpecification).
		    body(mainobjectstr).post("/workspaces"); //Since body can take string as the parameter


	   }*/


	/*@Test
	public void post_request_payload_asList() throws JsonProcessingException
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

		ObjectMapper objectmapper= new ObjectMapper();
		String mainobjectstr= objectmapper.writeValueAsString(mainlist);// this method converts java object to Json object and return Json object as string 


		given(requestSpecification).
		body(mainobjectstr).

		when().
		post("/post").

		then().spec(responseSpecification).log().all(); 


	}*/


/*	@Test
	public void post_request_payload_JSonObject_Node() 
	{
		ObjectMapper objectmapper= new ObjectMapper();

		ObjectNode objectnode = objectmapper.createObjectNode();//creating a json object directly not using Javaobject 
		objectnode.put("name", "testingworkspace");
		objectnode.put("type", "personal");
		objectnode.put("description","test workspace for testing");

		ObjectNode mainobject =objectmapper.createObjectNode();
		mainobject.set("workspace",objectnode);

		//String mainobjectstr= objectmapper.writeValueAsString(mainobject); we can use this also

		given(requestSpecification).
		body(mainobject).post("/workspaces").//we can here use mainobject directly since it a JSon object or we can also convert it into a string

		then().spec(responseSpecification).log().all(); 
x
	}*/

	@Test
	public void post_request_payload_asuJSon_arrayNode() throws JsonProcessingException 
	{
		ObjectMapper objectMapper= new ObjectMapper();
		ArrayNode arraynode= objectMapper.createArrayNode();

		ObjectNode obj1= objectMapper.createObjectNode();
		obj1.put("id", "901");
		obj1.put("type","personal");

		ObjectNode obj2= objectMapper.createObjectNode();
		obj2.put("id", "902");
		obj2.put("type", "personal");

		arraynode.add(obj1);
		arraynode.add(obj2);

		String mainobjectstr= objectMapper.writeValueAsString(arraynode); //we can use this if we want to return array in string


		given(requestSpecification).
		body(mainobjectstr).

		when().
		post("/post").

		then().spec(responseSpecification).log().all(); 


	}





}
