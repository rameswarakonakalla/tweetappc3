package com.iiht.tweetapp.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.iiht.tweetapp.model.AuthResponse;



@FeignClient(name = "${authservice.client.name}", 
url = "${authservice.client.url}")
public interface AuthFeign {

	/**
	 * 
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/apps/v1.0/tweets/validate")
	public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization") final String token);
}
