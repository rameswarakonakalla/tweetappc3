package com.iiht.tweetapp.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iiht.tweetapp.exception.TweetNotFoundException;
import com.iiht.tweetapp.model.TweetDetails;

@Service
public interface TweetServices {
	public ResponseEntity<Object> getAllTweets();
	public ResponseEntity<Object> getTweetsByUser(String username);
	public ResponseEntity<Object> addTweet(String username,TweetDetails tweetDetails);
	public ResponseEntity<Object> updateTweet(long id,TweetDetails tweetDetails) throws TweetNotFoundException;
	public ResponseEntity<Object> deleteTweet(long id) throws TweetNotFoundException;
	public ResponseEntity<Object> likeTweet(String username,long id) throws TweetNotFoundException;
	public ResponseEntity<Object> replyTweet(String username,long id,String reply) throws TweetNotFoundException;
	ResponseEntity<Object> unLikeTweet(String username,long id) throws TweetNotFoundException;
	
}
