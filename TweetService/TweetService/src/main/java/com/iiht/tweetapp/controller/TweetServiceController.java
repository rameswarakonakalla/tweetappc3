package com.iiht.tweetapp.controller;

import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.UUIDSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.tweetapp.config.AuthFeign;
import com.iiht.tweetapp.exception.InvalidTokenException;
import com.iiht.tweetapp.exception.TweetNotFoundException;
import com.iiht.tweetapp.model.TweetDetails;
import com.iiht.tweetapp.service.*;

import lombok.extern.slf4j.Slf4j;

import com.iiht.tweetapp.config.AppConfigs;

@RestController
@RequestMapping("/apps/v1.0/tweets")
@Slf4j
public class TweetServiceController {
	
	@Autowired
	TweetServices tweetservice;
	
	@Autowired
	private AuthFeign authFeign;
	
	
	@Autowired
    private KafkaTemplate<String, UUID> kafkaTemplate;
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllTweets(@RequestHeader("Authorization") String token) throws InvalidTokenException{
		log.info("inside tweet service controller to get all tweets");
		if(authFeign.getValidity(token).getBody().isValid()) {
		return tweetservice.getAllTweets();
		}
		log.info("inside tweet service controller ,token expired login again");
		throw new InvalidTokenException("Token Expired or Invalid , Login again ...");
	}
	@GetMapping("/{username}")
	public ResponseEntity<Object> getTweetsByUser(@RequestHeader("Authorization") String token,
			@PathVariable String username) throws InvalidTokenException{
		log.info("inside tweet service controller to get tweets by username");
		if(authFeign.getValidity(token).getBody().isValid()) {
		return tweetservice.getTweetsByUser(username);
		}
		throw new InvalidTokenException("Token Expired or Invalid , Login again ...");
	}
	@PostMapping("/{username}/add")
	public ResponseEntity<Object> addTweet(@RequestHeader("Authorization") String token,@PathVariable String username,@RequestBody TweetDetails tweetDetails) throws InvalidTokenException{
		log.info("inside tweet service controller to add a tweets");
		if(authFeign.getValidity(token).getBody().isValid()) {
			return tweetservice.addTweet(username, tweetDetails);
		}
		throw new InvalidTokenException("Token Expired or Invalid , Login again ...");	
	}
	@PutMapping("/{username}/update/{id}")
	public ResponseEntity<Object> updateTweet(@RequestHeader("Authorization") String token,
			@PathVariable("username") String username,@PathVariable("id") long id,
			@RequestBody TweetDetails tweetDetails) throws TweetNotFoundException, InvalidTokenException{
		log.info("inside tweet service controller to update tweets");
		if(authFeign.getValidity(token).getBody().isValid()) {
		return tweetservice.updateTweet(id, tweetDetails);
		}
		throw new InvalidTokenException("Token Expired or Invalid , Login again ...");
	}
	@DeleteMapping("/{username}/delete/{id}")
	public ResponseEntity<Object> deleteTweet(@RequestHeader("Authorization") String token,
			@PathVariable("username") String username,@PathVariable("id") long id) throws TweetNotFoundException, InvalidTokenException{
		log.info("deleted");
		log.info("inside tweet service controller to delete tweets");
//		Properties props = new Properties();
//        props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, longSerializer.class.getName());
		if(authFeign.getValidity(token).getBody().isValid()) {
//			kafkaTemplate.send(AppConfigs.topicName,"delete", id);
			
			return tweetservice.deleteTweet(id);
		}
		throw new InvalidTokenException("Token Expired or Invalid , Login again ...");
		
	}
	@PutMapping("/{username}/like/{id}")
	public ResponseEntity<Object> likeTweet(@RequestHeader("Authorization") String token,
			@PathVariable("username") String username,@PathVariable("id") long id) throws TweetNotFoundException, InvalidTokenException{
		log.info("inside tweet service controller to like tweets");
		if(authFeign.getValidity(token).getBody().isValid()) {
			System.out.println(username);
			return tweetservice.likeTweet(username,id);
		}
		throw new InvalidTokenException("Token Expired or Invalid , Login again ...");
	}
	@PutMapping("/{username}/unlike/{id}")
	public ResponseEntity<Object> uLikeTweet(@RequestHeader("Authorization") String token,
			@PathVariable("username") String username,@PathVariable("id") long id) throws TweetNotFoundException, InvalidTokenException{
		log.info("inside tweet service controller to unlike tweets");
		if(authFeign.getValidity(token).getBody().isValid()) {
			return tweetservice.unLikeTweet(username,id);
		}
		throw new InvalidTokenException("Token Expired or Invalid , Login again ...");
	}
	@PostMapping("/{username}/reply/{id}")
	public ResponseEntity<Object> replyTweet(@RequestHeader("Authorization") String token,
			@PathVariable("username") String username,
			@PathVariable("id") long id,@RequestBody String reply) throws TweetNotFoundException, InvalidTokenException{
		log.info("inside tweet service controller to reply to tweets");
		if(authFeign.getValidity(token).getBody().isValid()) {
			return tweetservice.replyTweet(username,id, reply);
		}
		throw new InvalidTokenException("Token Expired or Invalid , Login again ...");
		
	}

}
