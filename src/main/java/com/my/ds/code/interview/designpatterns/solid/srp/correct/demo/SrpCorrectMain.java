package com.my.ds.code.interview.designpatterns.solid.srp.correct.demo;

import com.my.ds.code.interview.designpatterns.solid.srp.correct.impl.SrpUserController;

import java.io.IOException;

public class SrpCorrectMain {

	private static final String VALID_USER_JSON = "{\"name\": \"Randy\", \"email\": \"randy@email.com\", \"address\":\"110 Sugar lane\"}";
	
	private static final String INVALID_USER_JSON = "{\"name\": \"Sam\", \"email\": \"sam@email\", \"address\":\"111 Sugar lane\"}";

	public static void main(String[] args) throws IOException {
		SrpUserController controller = new SrpUserController();
		
		String response = controller.createUser(VALID_USER_JSON);	
		if(!response.equalsIgnoreCase("SUCCESS")) {
			System.err.println("Failed");
		}
		System.out.println("Valid JSON received response: "+response);
		response = controller.createUser(INVALID_USER_JSON);
		if(!response.equalsIgnoreCase("ERROR")) {
			System.err.println("Failed");
		}
		System.out.println("Invalid JSON received response: "+response);
	}

}
