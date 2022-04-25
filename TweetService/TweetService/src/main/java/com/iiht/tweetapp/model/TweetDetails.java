package com.iiht.tweetapp.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tweet_details")
@Entity
public class TweetDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String handleName;
	@Column 
	private String message;
	@Column
	private LocalDateTime time;
	@Column
	private String username;
	@ElementCollection
    @CollectionTable(name = "likes", joinColumns = @JoinColumn(name="id"))
	@Column
	private List<String> likes;
	@ElementCollection
    @CollectionTable(name = "replies", joinColumns = @JoinColumn(name="id"))
	@Column
	private List<String> replies;
	@Column
	@JsonIgnore
	private boolean Status;

}
