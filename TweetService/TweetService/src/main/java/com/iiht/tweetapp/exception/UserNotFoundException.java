package com.iiht.tweetapp.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {

	public UserNotFoundException(String msg) {
		super(msg);
	}
}
