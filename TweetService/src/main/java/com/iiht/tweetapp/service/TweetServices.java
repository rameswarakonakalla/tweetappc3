package com.iiht.tweetapp.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iiht.tweetapp.exception.TweetNotFoundException;
import com.iiht.tweetapp.model.TweetDetails;

@Service
public interface TweetServices {
	public ResponseEntity<Object> getAllTweets();
	public ResponseEntity<Object> getTweetsByUser(String username);
	public ResponseEntity<Object> addTweet(String username,TweetDetails tweetDetails);
	public ResponseEntity<Object> updateTweet(UUID id,TweetDetails tweetDetails) throws TweetNotFoundException;
	public ResponseEntity<Object> deleteTweet(UUID id) throws TweetNotFoundException;
	public ResponseEntity<Object> likeTweet(String username,UUID id) throws TweetNotFoundException;
	public ResponseEntity<Object> replyTweet(String username,UUID id,String reply) throws TweetNotFoundException;
	ResponseEntity<Object> unLikeTweet(String username,UUID id) throws TweetNotFoundException;
	
}
