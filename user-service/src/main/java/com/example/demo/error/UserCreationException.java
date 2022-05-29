package com.example.demo.error;

public class UserCreationException extends Exception{

	public UserCreationException() {
		super();
	}
	
	public UserCreationException(String message) {
		super(message);
	}
	
}
