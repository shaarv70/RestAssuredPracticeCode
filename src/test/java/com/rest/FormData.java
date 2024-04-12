package com.rest;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static io.restassured.config.RestAssuredConfig.config;

import org.testng.annotations.Test;

import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;

public class FormData {
	
	
	// it is nothing but if we want to send the data to the server in multiple parts and of differen type like in file and in json, in plain text
	
/*	@Test
	public void multipart_formdata()
	{
		
		given().
		baseUri("https://postman-echo.com").
		multiPart("foo1","bar1").
		multiPart("foo2","bar2").
		log().all().
		
		
		
		when().post("/post").
		
		then().
		log().all().
		assertThat().statusCode(200);
	
	}*/
	
	
/*	@Test
	public void multipart_formdata_uploadfile()
	{
		String attributes = "{\"name\":\"Idea.txt\",\"parent\":{\"id\":\"123456\"}}";
		File file= new File(System.getProperty("user.dir")+"//target//Idea.txt");
		given().
		baseUri("https://postman-echo.com").
		multiPart("File",file).
		multiPart("attributes",attributes,"application/json").//IF we dont specify the content type it will take it as a plain text
		log().all().
		
		
		
		when().post("/post").
		
		then().
		log().all().
		assertThat().statusCode(200);
	
	}*/
	
	@Test
	public void multipart_formdata_downloadfile() throws IOException
	{
		
		
		InputStream is= given().
		baseUri("https://raw.githubusercontent.com").
		log().all().
		
		
		
		when().post("/appium-boneyard/sample-code/master/sample-code/apps/ContactManager/ContactManager-selendroid.apk").
		
		then().extract().response().asInputStream();
		
		OutputStream os= new FileOutputStream("ContactManager-selendroid.apk");
		byte[] bytes= new byte[is.available()];
		
		is.read(bytes);
		os.write(bytes);
		os.close();
		
	}
	
	@Test
	public void form_url_encoded_data()
	{
		given().
		baseUri("https://postman-echo.com").
		config(config().encoderConfig(EncoderConfig.encoderConfig().
				appendDefaultContentCharsetToContentTypeIfUndefined(false))).
		formParam("key1","value1").
		formParam("key 2", "value 2").
		log().all().
		
		when().
		post("/post").
		
		then().
		log().all().
		assertThat().
		statusCode(200);
	    
}}
