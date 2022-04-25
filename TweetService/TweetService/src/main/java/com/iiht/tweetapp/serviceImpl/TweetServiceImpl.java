package com.iiht.tweetapp.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//import com.iiht.tweetapp.config.AppConfigs;
import com.iiht.tweetapp.exception.TweetNotFoundException;
import com.iiht.tweetapp.model.ResponseMessage;
import com.iiht.tweetapp.model.ResponseTweet;
import com.iiht.tweetapp.model.TweetDetails;
import com.iiht.tweetapp.model.UserData;
import com.iiht.tweetapp.repository.TweetRepository;
import com.iiht.tweetapp.repository.UserRepository;
import com.iiht.tweetapp.service.TweetServices;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TweetServiceImpl implements TweetServices{
	@Autowired
	private TweetRepository tweetRepository;
	@Autowired
	private UserRepository userRepository;
	public List<ResponseTweet> formatData(List<TweetDetails> list) {
		log.info("inside tweet service Implementation to format data");
		List<ResponseTweet> result=new ArrayList<>();
		for(TweetDetails tweet:list) {
			Optional<UserData> user=userRepository.findById(tweet.getUsername());
			ResponseTweet tweet1=new ResponseTweet(tweet.getId(),tweet.getHandleName(),tweet.getMessage(),
					tweet.getTime(),tweet.getUsername(),tweet.getLikes(),tweet.getReplies(),user.get().getFirstName(),
					user.get().getLastName(),tweet.isStatus());
			result.add(tweet1);
		}
		return result;
	}
	@Override
	public ResponseEntity<Object> getAllTweets() {
		log.info("inside tweet service Implementation to get all tweets");
		List<TweetDetails> tweets=tweetRepository.findAll().stream().filter(o->o.isStatus()).collect(Collectors.toList());
		if(!tweets.isEmpty() && tweets.size()>0)
			return new ResponseEntity<Object>(formatData(tweets),HttpStatus.OK);
		return new ResponseEntity<Object>(new ResponseMessage("No Tweets Found"),HttpStatus.NO_CONTENT);
	}
	@Override
	public ResponseEntity<Object> getTweetsByUser(String username) {
		log.info("inside tweet service Implementation to get tweets by username");
		List<TweetDetails> tweets=tweetRepository.findAll().stream().filter(o->o.getUsername().equals(username)&&o.isStatus()).collect(Collectors.toList());
		if(!tweets.isEmpty()  && tweets.size()>0)
			return new ResponseEntity<Object>(formatData(tweets),HttpStatus.OK);
		return new ResponseEntity<Object>(new ResponseMessage("No Tweets Found"),HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Object> addTweet(String username,TweetDetails tweetDetails) {
		log.info("inside tweet service Implementation to add tweet");
			//tweetDetails.setId(long.randomlong());
			tweetDetails.setUsername(username);
			tweetDetails.setStatus(true);
			tweetDetails.setTime(LocalDateTime.now());
			tweetRepository.save(tweetDetails);
			return new ResponseEntity<Object>("Added Tweet Successfully",HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Object> updateTweet(long id, TweetDetails tweetDetails) throws TweetNotFoundException {
		log.info("inside tweet service Implementation to update tweet data");
			Optional<TweetDetails> tweet=tweetRepository.findById(id);
			if(tweet.isEmpty()) {
				throw new TweetNotFoundException("Tweet not found exception");
			}
			tweet.get().setHandleName(tweetDetails.getHandleName());
			tweet.get().setMessage(tweetDetails.getMessage());
			tweet.get().setTime(LocalDateTime.now());
			tweetRepository.save(tweet.get());
			return new ResponseEntity<Object>("Updated Tweet Successfully",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deleteTweet(long id) throws TweetNotFoundException {
		log.info("inside tweet service Implementation to delete tweet");
			Optional<TweetDetails> tweet=tweetRepository.findById(id);
			if(tweet.isEmpty()) {
				throw new TweetNotFoundException("Tweet not found exception");
			}
			tweet.get().setStatus(false);
			tweetRepository.save(tweet.get());
			return new ResponseEntity<Object>("Deleting Tweet Successfully",HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<Object> likeTweet(String username,long id) throws TweetNotFoundException {
		log.info("inside tweet service Implementation to like tweet {}",id);
			Optional<TweetDetails> tweet=tweetRepository.findById(id);
			if(tweet.isEmpty()) {
				throw new TweetNotFoundException("Tweet not found exception");
			}
			if(tweet.get().getLikes()!=null) {
				tweet.get().getLikes().add(username);
				tweet.get().setLikes(tweet.get().getLikes());
			}
			else {
				List<String> l=new ArrayList<>();
				l.add(username);
				tweet.get().setLikes(l);
			}
			tweetRepository.save(tweet.get());
			return new ResponseEntity<Object>("liked the Tweet Successfully",HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<Object> unLikeTweet(String username,long id) throws TweetNotFoundException {
		log.info("inside tweet service Implementation to unlike tweet");
			Optional<TweetDetails> tweet=tweetRepository.findById(id);
			if(tweet.isEmpty()) {
				throw new TweetNotFoundException("Tweet not found exception");
			}
			if(tweet.get().getLikes()!=null) {
				tweet.get().getLikes().remove(username);
				tweet.get().setLikes(tweet.get().getLikes());
			}
			tweetRepository.save(tweet.get());
			return new ResponseEntity<Object>("Un-liked the Tweet Successfully",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> replyTweet(String username,long id, String reply) throws TweetNotFoundException {
		log.info("inside tweet service Implementation to reply tweet");
			Optional<TweetDetails> tweet=tweetRepository.findById(id);
			if(tweet.isEmpty()) {
				throw new TweetNotFoundException("Tweet not found exception");
			}
			Optional<UserData> user=userRepository.findById(username);
			reply=reply.replace("+", " ");
			reply=reply.replace("=", "");
			reply=user.get().getFirstName()+" "+user.get().getLastName()+"-"+reply;
			if(tweet.get().getReplies()!=null)
				tweet.get().getReplies().add(reply);
			else {
				List<String> l=new ArrayList<String>();
				l.add(reply);
				tweet.get().setReplies(l);
			}
			System.out.println(reply);
			System.out.println(tweet.get());
			tweetRepository.save(tweet.get());
			return new ResponseEntity<Object>("Replied to Tweet Successfully",HttpStatus.OK);
	}
	public ResponseEntity<Object> deleteTweetKafka(long id) throws TweetNotFoundException {
		log.info("inside tweet service Implementation to delete tweet");
		Optional<TweetDetails> tweet=tweetRepository.findById(id);
		if(tweet.isEmpty()) {
			throw new TweetNotFoundException("Tweet not found exception");
		}
		tweet.get().setStatus(false);
		tweetRepository.save(tweet.get());
		return new ResponseEntity<Object>("Deleting Tweet Successfully",HttpStatus.OK);
	}

}
