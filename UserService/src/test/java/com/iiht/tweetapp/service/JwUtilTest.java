package com.iiht.tweetapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.iiht.tweetapp.seviceimpl.JwtUtil;


@ExtendWith(MockitoExtension.class)
 class JwUtilTest {

	@Mock
	UserDetails userdetails;
	
	@Mock
	JwtUtil jwt;
	

	@Test
	 void generateTokenTest() {
		userdetails = new User("kumar", "kumar", new ArrayList<>());
		Mockito.when(jwt.generateToken(userdetails)).thenReturn("token");
		String generateToken = jwt.generateToken(userdetails);
		assertNotNull(generateToken);
	}
	

	@Test
	 void validateTokenTest() {
		userdetails = new User("kumar", "kumar", new ArrayList<>());
		Mockito.when(jwt.generateToken(userdetails)).thenReturn("token");
		Mockito.when(jwt.validateToken("token")).thenReturn(true);
		String generateToken = jwt.generateToken(userdetails);
		Boolean validateToken = jwt.validateToken(generateToken);
		assertEquals(true, validateToken);
	}
	
	@Test
	 void validateTokenNegativeTest() {
		userdetails = new User("kumar", "kumar", new ArrayList<>());
		Mockito.when(jwt.generateToken(userdetails)).thenReturn("token1");
		Mockito.when(jwt.validateToken("token1")).thenReturn(false);
		String generateToken = jwt.generateToken(userdetails);
		Boolean validateToken = jwt.validateToken(generateToken);
		assertEquals(false, validateToken);
	}



}
