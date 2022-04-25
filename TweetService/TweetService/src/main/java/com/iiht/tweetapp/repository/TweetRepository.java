package com.iiht.tweetapp.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.tweetapp.model.TweetDetails;

@Repository
public interface TweetRepository extends JpaRepository<TweetDetails, Long>{
	Optional<TweetDetails> findByUsername(String username);
}
