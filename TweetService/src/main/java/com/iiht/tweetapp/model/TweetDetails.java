package com.iiht.tweetapp.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "tweet_details")
public class TweetDetails {
	
	@PrimaryKey
	private UUID id;
	@Column
	private String handleName;
	@Column 
	private String message;
	@Column
	private LocalDateTime time;
	@Column
	private String username;
	@Column
	private Set<String> likes;
	@Column
	private List<String> replies;
	@Column
	@JsonIgnore
	private boolean Status;

}
