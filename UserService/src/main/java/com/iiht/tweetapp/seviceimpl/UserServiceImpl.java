package com.iiht.tweetapp.seviceimpl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iiht.tweetapp.exception.UserExistsException;
import com.iiht.tweetapp.model.AuthResponse;
import com.iiht.tweetapp.model.LoginDetails;
import com.iiht.tweetapp.model.ResponseMessage;
import com.iiht.tweetapp.model.UserData;
import com.iiht.tweetapp.repository.UserRepository;
import com.iiht.tweetapp.service.UserServices;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserServices{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomerDetailsService custdetailservice;
	@Autowired
	private JwtUtil jwtutil;

	@Override
	public ResponseEntity<AuthResponse> login(LoginDetails loginDetails) {
		log.info("inside user service implementation to login");
		final UserDetails userdetails = custdetailservice.loadUserByUsername(loginDetails.getUsername());
		String uid = "";
		String generateToken = "";
		Optional<UserData> user=userRepository.findById(userdetails.getUsername());
		if (user.isPresent() && userdetails.getPassword().equals(loginDetails.getPassword())) {
			uid = loginDetails.getUsername();
			generateToken = jwtutil.generateToken(userdetails);
			log.info("login successful");
			return new ResponseEntity<>(new AuthResponse(uid,true,generateToken,user.get().getFirstName(),user.get().getLastName()), HttpStatus.OK);
		} else {
			log.info("At Login : ");
			log.error("Not Accesible");
			return new ResponseEntity<>(new AuthResponse(loginDetails.getUsername(),false,"In Correct Password",null,null), HttpStatus.FORBIDDEN);
		}
	}

	@Override
	public ResponseEntity<Object> register(UserData userData) {
		log.info("inside user service implementation to register user");
		Optional<UserData> user=userRepository.findById(userData.getUserName());
		if(user.isEmpty()) {
			try {
				log.info("registered successfully");
				userRepository.save(userData);
				return new ResponseEntity<>("User Added Successfully", HttpStatus.CREATED);
			}
			catch(RuntimeException ex) {
				log.info("User already exixts");
				throw new UserExistsException("Login id already Exists");
			}
		
		}
		else {
			log.info("Username already exists");
			throw new UserExistsException("Username already Exists");
		}
	}

	@Override
	public ResponseEntity<Object> forgotPassword(LoginDetails data){
            Optional<UserData> user = userRepository.findById(data.getUsername());
            if (user.isEmpty()) {
            	log.info("user name not found");
                throw new UsernameNotFoundException("Username Not Found, Resister");
            }
            log.info("resetting the password");
            user.get().setPassword(data.getPassword());
            userRepository.save(user.get());
            return new ResponseEntity<>("Password reset successfull",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AuthResponse> validate(String authToken) {
		String token1 = authToken.substring(7);
		AuthResponse res = new AuthResponse();
		if (Boolean.TRUE.equals(jwtutil.validateToken(token1))) {
			res.setUsername(jwtutil.extractUsername(token1));
			res.setValid(true);
			Optional<UserData> user1=userRepository.findById(jwtutil.extractUsername(token1));
			if(user1.isPresent()) {
				res.setUsername(user1.get().getUserName());
				res.setValid(true);
				res.setToken("token successfully validated");
				log.info("token successfully validated");
				return new ResponseEntity<>(res, HttpStatus.OK);
			}
		} else {
			res.setValid(false);
			res.setToken("Invalid Token Received");
			log.info("At Validity : ");
			log.error("Token Has Expired");
			return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Object> getAllUsers() {
		log.info("getting all the users");
		List<UserData> list = userRepository.findAll();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> searchByUsername(String username) {
		log.info("getting users with given username");
		List<UserData> list = userRepository.findAll().stream().filter(o->o.getUserName().toLowerCase().contains(username)).collect(Collectors.toList());
		return new ResponseEntity<>(new ResponseMessage(list),HttpStatus.OK);
	}
	

}
