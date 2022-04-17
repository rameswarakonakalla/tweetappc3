package com.iiht.tweetapp.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(value="user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
	
	    @Column
	    private String firstName;
	    @Column
	    private String lastName;
	    @PrimaryKey
	    private String userName;
	    @Column
	    private String password;
	    @Column
	    private long contactNo;
	    
}
