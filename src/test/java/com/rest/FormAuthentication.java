package com.rest;

import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FormAuthentication {
	
	
	@BeforeClass
	public void beforeCLass()
	{
		RestAssured.requestSpecification= new RequestSpecBuilder().
				setBaseUri("https://localhost:8443").setRelaxedHTTPSValidation().
				build();
		
	}
	
	
	@Test
	public void authenticationusing_CSRFtoken()
	{
        SessionFilter filter = new SessionFilter(); //the default session id name is Jsessionid if it is not present in 
                                                    //the application then we have to send the name explicitly
                                                   
       //RestAssured.config = RestAssured.config().sessionConfig(new SessionConfig().sessionIdName("phpsessionid"));(for diiferent name sessionid)
		
        given().
        csrf("/login").
        auth().form(
                "dan", "dan123", new FormAuthConfig("/signin", "txtUsername", "txtPassword")
//                        .withAutoDetectionOfCsrf()
        ).
        filter(filter).
        log().all().
        
        when().
        get("/login").
		
		then().
		log().all().
		
		assertThat().
        statusCode(200);
		
		System.out.println("sessionid:"+filter.getSessionId()); //to fetch session id 
		
		
		
		given().
		sessionId(filter.getSessionId()).
		log().all().
		
		get("/profile/index").
		
		then().
		log().all().
		statusCode(200).body("html.body.div.p", equalTo("This is User Profile\\Index. Only authenticated people can see this"));
		//here we are sending the html path
	}
	
	
	
	@Test
	public void authenticationusing_CSRFtoken_cookie_demo()
	{
        SessionFilter filter = new SessionFilter(); 
                                                   
		
        given().
        csrf("/login").
        auth().form(
                "dan", "dan123", new FormAuthConfig("/signin", "txtUsername", "txtPassword")
//                        
        ).
        filter(filter).
        log().all().
        
        when().
        get("/login").
		
		then().
		log().all().
		
		assertThat().
        statusCode(200);
		
		System.out.println("sessionid:"+filter.getSessionId()); //to fetch session id 
		
		
		
		given().cookie("JSESSIONID",filter.getSessionId()).
		
		log().all().
		
		get("/profile/index").
		
		then().
		log().all().
		statusCode(200).body("html.body.div.p", equalTo("This is User Profile\\Index. Only authenticated people can see this"));
		//here we are sending the html path
	}
	
	
	
	@Test
	public void authenticationusing_CSRFtoken_cookiebuilder_demo()
	{
        SessionFilter filter = new SessionFilter(); 
        given().
        csrf("/login").
        auth().form(
                "dan", "dan123", new FormAuthConfig("/signin", "txtUsername", "txtPassword")
//                        
                ).
        filter(filter).
        log().all().
        
        when().
        get("/login").
		
		then().
		log().all().
		
		assertThat().
        statusCode(200);
		
		System.out.println("sessionid:"+filter.getSessionId()); //to fetch session id 
		
		
		Cookie cookie1= new Cookie.Builder("JSESSIONID",filter.getSessionId()).setSecured(true).setHttpOnly(true).setComment("mycookie").build();
		
		given().cookie(cookie1).
		
		log().all().
		
		get("/profile/index").
		
		then().
		log().all().
		statusCode(200).body("html.body.div.p", equalTo("This is User Profile\\Index. Only authenticated people can see this"));
		//here we are sending the html path
	}
	
	
	@Test
	public void authenticationusing_CSRFtoken_multiplecookie()
	{
        SessionFilter filter = new SessionFilter(); 
        given().
        csrf("/login").
        auth().form(
                "dan", "dan123", new FormAuthConfig("/signin", "txtUsername", "txtPassword")
//                        
                ).
        filter(filter).
        log().all().
        
        when().
        get("/login").
		
		then().
		log().all().
		
		assertThat().
        statusCode(200);
		
		System.out.println("sessionid:"+filter.getSessionId()); //to fetch session id 
		
		
		Cookie cookie1= new Cookie.Builder("JSESSIONID",filter.getSessionId()).setSecured(true).setHttpOnly(true).setComment("mycookie").build();
		Cookie cookie2= new Cookie.Builder("Dummy","mydummy").build();
		
		Cookies cookie= new Cookies(cookie1,cookie2);
		
		given().cookies(cookie).
		
		log().all().
		
		get("/profile/index").
		
		then().
		log().all().
		statusCode(200).body("html.body.div.p", equalTo("This is User Profile\\Index. Only authenticated people can see this"));
		
	}
	
	
	
	@Test
	public void authenticationusing_CSRFtoken_fetch_single_cookie()
	{
		
        	
	   	
		Response response= given().
       
		log().all().
		
		when().
		get("/profile/index").
		
		then().
		log().all().
		statusCode(200).extract().response();
	
	 System.out.println(response.getCookie("JSESSIONID"));
	  System.out.println(response.getDetailedCookie("JSESSIONID"));
		
	}
	
	
	
	@Test
	public void authenticationusing_CSRFtoken_fetch_multiple_cookie()
	{
		
      Response response = given().
       
		log().all().
		
		when().
		get("/profile/index").
		
		then().
		log().all().
		statusCode(200).extract().response();
	
       Map<String,String> mycookies= response.getCookies();
	
       for (Map.Entry<String,String> enteries:mycookies.entrySet())
       {
    	   System.out.println("cookie name:"+enteries.getKey());
    	   System.out.println("cookie value:"+enteries.getValue());
       }
      
       Cookies allcookies= response.getDetailedCookies();
       
       
       List<Cookie> mylist= allcookies.asList();
    		   
       
       
       for (Cookie cookie:mylist)
       {
    	  System.out.println("cookie attributes:"+cookie.toString());
       }
       
       
       
       
	}

}
