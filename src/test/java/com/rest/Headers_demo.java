package com.rest;


import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Headers_demo {
	
/*
	@Test
	public void headers()
	{
		given().
		baseUri("https://e9a0c3c8-2904-4397-a3d3-434df4e1973c.mock.pstmn.io").
		header("header","value2").
		header("x-mock-match-request-headers","header")
		
		.when()
		.get("/get").
		
		then().
		log().all().
		assertThat().statusCode(200);
		
	}
	
	@Test
	public void second_way()
	{
		Header header1= new Header("header","value2");
		Header header2= new Header("x-mock-match-request-headers","header");
		
		given().
		baseUri("https://e9a0c3c8-2904-4397-a3d3-434df4e1973c.mock.pstmn.io").
		header(header1).header(header2).
		
		
		when()
		.get("/get").
		
		then().
		log().all().
		assertThat().statusCode(200);
		
	}
	
	
	public void third_way()
	{
		Header header1= new Header("header","value2");
		Header header2= new Header("x-mock-match-request-headers","header");
		
		Headers headers= new Headers(header1,header2);
		
		given().
		baseUri("https://e9a0c3c8-2904-4397-a3d3-434df4e1973c.mock.pstmn.io").
		headers(headers).
		
		
		when()
		.get("/get").
		
		then().
		log().all().
		assertThat().statusCode(200);
		
	}
	
	@Test
	public void using_Map()
	{
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("header","value1");
		headers.put("x-mock-match-request-headers","header");
		
		
		given().
		baseUri("https://e9a0c3c8-2904-4397-a3d3-434df4e1973c.mock.pstmn.io").
		headers(headers).
		
		
		when()
		.get("/get").
		
		then().
		log().all().
		assertThat().statusCode(200);
		
	}
	
	
	@Test
	public void multivalue_header()
	{
		Header header1= new Header("multivalueheader","value1");
		Header header2= new Header("multivalueheader","value2");
		
		Headers headers= new Headers(header1,header2);
		
		
		
		given().
		baseUri("https://e9a0c3c8-2904-4397-a3d3-434df4e1973c.mock.pstmn.io").
		headers(headers). //this will also work in same way below
	//	header("multivalueheader","value1","value2").// this will make 2 headers with same key but different values
		log().headers().
		
		
		when()
		.get("/get").
		
		then().
		log().all().
		assertThat().statusCode(200);
		
	}
	
	
	@Test
	public void assert_on_response_header()
	{
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("header","value1");
		headers.put("x-mock-match-request-headers","header");
		
		
		given().
		baseUri("https://e9a0c3c8-2904-4397-a3d3-434df4e1973c.mock.pstmn.io").
		headers(headers). //this will also work in same way below
	//	header("multivalueheader","value1","value2").// this will make 2 headers with same key but different values
		log().headers().
		
		
		when()
		.get("/get").
		
		then().
		log().all().
		assertThat().statusCode(200).headers("responseheader","resvalue1",
				"X-RateLimit-Limit","120");
		//header("responseheader", "resvalue1").
		//header("X-RateLimit-Limit","120");
		//we can also use hashmap for multiple headers
		
	}
	

	@Test
	public void extract_from_response_header()
	{
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("header","value1");
		headers.put("x-mock-match-request-headers","header");
		
		
		Headers extractedheaders= given().
		baseUri("https://e9a0c3c8-2904-4397-a3d3-434df4e1973c.mock.pstmn.io").
		headers(headers). 
	    
		
		
		when()
		.get("/get").
		
		then().
		
		assertThat().statusCode(200).extract().headers();
		
	//	System.out.println("header name:"+extractedheaders.get("responseheader").getName());
	//	System.out.println("header value:"+extractedheaders.get("responseheader").getValue());
	//	System.out.println("header value:"+extractedheaders.getValue("responseheader"));//Above line and this line both are having same o/p
		
		//if we want to print all headers
		
		for (Header extracted:extractedheaders)
		{
			System.out.println(extracted.getName()+ " = " +extracted.getValue() );
		}
		
*/		
		
		@Test
		public void extract_multivalue_response_header()
		{
			Map<String,String> headers = new HashMap<String, String>();
			headers.put("header","value3");
			headers.put("x-mock-match-request-headers","header");
			
			
			Headers extractedheaders= given().
			baseUri("https://e9a0c3c8-2904-4397-a3d3-434df4e1973c.mock.pstmn.io").
			headers(headers). 
		    
			
			
			when()
			.get("/get").
			
			then().
			assertThat().statusCode(200).extract().headers();
			List<String> allheader= extractedheaders.getValues("multiValueHeader");
			
			for (String value:allheader)
			{
				System.out.println(extractedheaders.get("multiValueHeader").getName()+" = "+ value);
			}
			
			
			
			
		
		
		
		
		
	}
	
	
	
	
	
	
	

}
