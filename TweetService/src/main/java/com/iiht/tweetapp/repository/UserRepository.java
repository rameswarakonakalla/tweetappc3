package com.iiht.tweetapp.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.iiht.tweetapp.model.UserData;


@Repository
public interface UserRepository extends CassandraRepository<UserData, String> {

}