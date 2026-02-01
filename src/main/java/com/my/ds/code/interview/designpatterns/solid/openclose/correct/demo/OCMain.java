package com.my.ds.code.interview.designpatterns.solid.openclose.correct.demo;

import com.my.ds.code.interview.designpatterns.solid.openclose.correct.impl.OCUserController;

import java.io.IOException;

public class OCMain {

	private static final String VALID_USER_JSON = "{\"name\": \"Randy\", \"email\": \"randy@email.com\", \"address\":\"110 Sugar lane\"}";
	
	private static final String INVALID_USER_JSON = "{\"name\": \"Sam\", \"email\": \"sam@email\", \"address\":\"111 Sugar lane\"}";

	public static void main(String[] args) throws IOException {
		OCUserController controller = new OCUserController();
		
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
