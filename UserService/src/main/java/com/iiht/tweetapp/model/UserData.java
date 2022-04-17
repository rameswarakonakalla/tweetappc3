package com.iiht.tweetapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table(name="user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserData {
	
	    @Column
	    @Schema(description = "First name of the user",required = true,example="Konakalla")
	    @NotBlank(message="First name should not be empty")
	    private String firstName;
	    @Column
	    @Schema(description = "Last name of the user",required = true,example="Rameswara")
	    @NotBlank(message="Last name should not be empty")
	    private String lastName;
	    @Id
	    @Schema(description = "user name of the user",required = true,example="konakalla@gmail.com")
	    @Pattern(regexp = "[a-zA-Z0-9@.]*$", message = "user name should contain only alphabets and digits")
	    private String userName;
	    @Column
	    @Schema(description = "Password of the user",required = true,example="Konakalla")
	    @NotBlank(message="Password should not be empty")
	    @Size(min = 8, message = "minimum 8 Characters required")
	    private String password;
	    @Column
	    @Schema(description = "Contact Number of the user",required = true,example="9898989898")
	    private long contactNo;
	    
}
