package com.iiht.tweetapp.exception;

@SuppressWarnings("serial")
public class UserExistsException extends RuntimeException {
	/**
	 * @param message
	 */
	public UserExistsException(String message) {
		super(message);
	}

	
}
