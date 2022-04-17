package com.iiht.tweetapp.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDetails {
	@Schema(description = "user name of the user",required = true,example="konakalla@gmail.com")
    @Pattern(regexp = "[a-zA-Z0-9@.]*$", message = "user name should contain only alphabets and digits")
	private String username;
	@Schema(description = "Password of the user",required = true,example="Konakalla")
    @NotBlank(message="Password should not be empty")
    @Size(min = 8, message = "minimum 8 Characters required")
	private String password;
}
