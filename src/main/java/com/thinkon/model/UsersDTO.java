/**
 * Created by Phanindra on 2024-10-09
 */
package com.thinkon.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDTO {

	private int userId;

	@NotBlank(message = "Invalid Name: User Name is Blank")
	@NotNull(message = "Invalid Name: User Name is NULL")
	private String userName;
	@NotBlank(message = "Invalid Name: First Name is Blank")
	@NotNull(message = "Invalid Name: First Name is NULL")
	private String firstName;
	private String lastName;
	@Email(message = "Invalid email address")
	@NotBlank(message = "Invalid Email: Email address is Blank")
	@NotNull
	private String email;
	@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number - Enter 10 digit numbers only")
	private String phoneNo;
}
