package com.rest;


import java.util.Base64;

import org.apache.http.message.HeaderValueFormatter;
import org.testng.annotations.Test;

/*Basic authentication is not safe since here anyone can decode the base 64 encoded message.
 
In digest authentication the client first sends the request without username and password, then server will send the challenge 
to the client by sending certain parametrs like realm, nonce etc, then client use this parameters
and then it encrypots the usernamre and password using hashing algortithm, then server decryppts the same with the same hashing algortithm
and all these parameters(nonce etc) are sent by server as part of particular response header.So here we are not sending username and 
pasword as string, so it is mosre secure than  basic authentication.

In bearer authentication when client tries to login bu username and password then server will send the cleint a bearer token 
in response with which the client sends the subsequest requests having that bearer token. 
 */
public class BasicAndDigestAuthentication {
	
	@Test
	public void Base64Encoding()
	{
		String headervalue= "admin123";
		
		String encodedmess= Base64.getEncoder().encodeToString(headervalue.getBytes());
		System.out.println("Encoded mess:"+ encodedmess);
		
	    byte[] decodedmess= Base64.getDecoder().decode(encodedmess);
		System.out.println("decoded mess:"+ new String(decodedmess));
		
		
	}

}
