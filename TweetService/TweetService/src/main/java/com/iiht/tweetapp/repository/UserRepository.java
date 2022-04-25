package com.iiht.tweetapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iiht.tweetapp.model.UserData;


@Repository
public interface UserRepository extends JpaRepository<UserData, String> {

}