package com.iiht.tweetapp.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(name="user_details")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
	
	    @Column
	    private String firstName;
	    @Column
	    private String lastName;
	    @Id
	    private String userName;
	    @Column
	    private String password;
	    @Column
	    private long contactNo;
	    
}
