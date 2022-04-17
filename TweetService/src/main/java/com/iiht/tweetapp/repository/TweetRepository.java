package com.iiht.tweetapp.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.iiht.tweetapp.model.TweetDetails;

@Repository
public interface TweetRepository extends CassandraRepository<TweetDetails, UUID>{
	Optional<TweetDetails> findByUsername(String username);
}
