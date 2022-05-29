package com.example.demo.error;

public class UserNotFoundException extends Exception{

	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
}
