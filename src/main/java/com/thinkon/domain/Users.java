/**
 * Created by Phanindra on 2024-10-09
 */
package com.thinkon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
	@SequenceGenerator(name = "mySeqGen", sequenceName = "user_id_seq", initialValue = 1, allocationSize = 1)
	private int userId;
	@NotNull
	@Column(unique = true)
	private String userName;
	@NotNull
	private String firstName;
	private String lastName;
	@Email
	@Column(unique = true)
	private String email;
	private String phoneNo;
}
