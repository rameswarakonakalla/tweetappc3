package com.iiht.tweetapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.iiht.tweetapp.model.AuthResponse;
import com.iiht.tweetapp.model.LoginDetails;
import com.iiht.tweetapp.model.UserData;


@Service
public interface UserServices {
	public ResponseEntity<AuthResponse> login(LoginDetails loginDetails);
	public ResponseEntity<Object> register(UserData user);
	public ResponseEntity<Object> forgotPassword(LoginDetails data);
	public ResponseEntity<AuthResponse> validate(String authToken);
	public ResponseEntity<Object> getAllUsers();
	public ResponseEntity<Object> searchByUsername(String username);
}
