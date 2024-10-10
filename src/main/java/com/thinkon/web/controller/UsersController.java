
package com.thinkon.web.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thinkon.model.UserPagedList;
import com.thinkon.model.UsersDTO;
import com.thinkon.services.UsersService;

import lombok.RequiredArgsConstructor;

/**
 * Created by Phanindra on 2024-10-09
 * 
 * REST Controller for User Management which has the following functions
 * 
 * Saves user details
 * Edits user details
 * Deletes user details
 * Retrieves a specific user
 * Gets a list of all users or specific users based on first / last name
 * 
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class UsersController {

	private final UsersService usersService;

	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	private static final Integer DEFAULT_PAGE_SIZE = 25;

	@GetMapping(produces = { "application/json" }, path = "users")
	public ResponseEntity<UserPagedList> listBeers(
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName) {

		if (pageNumber == null || pageNumber < 0) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}

		if (pageSize == null || pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}

		UserPagedList userList = usersService.listUsers(PageRequest.of(pageNumber, pageSize), firstName, lastName);

		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@PostMapping(path = "users")
	public ResponseEntity<UsersDTO> saveUserDetails(@RequestBody @Validated UsersDTO userDTO) {

		return new ResponseEntity<UsersDTO>(usersService.saveUsers(userDTO), HttpStatus.CREATED);
	}

	@PutMapping("users/{id}")
	public ResponseEntity<UsersDTO> updateUserById(@PathVariable("id") String userId,
			@RequestBody @Validated UsersDTO usersDTO) {

		return new ResponseEntity<>(usersService.updateUserDetails(Integer.valueOf(userId), usersDTO), HttpStatus.OK);

	}

	@GetMapping("users/{id}")
	public ResponseEntity<UsersDTO> gettransactionById(@PathVariable("id") String userID) {
		UsersDTO usersDTO = new UsersDTO();
		try {

			usersDTO = usersService.getUserById(Integer.valueOf(userID));

		} catch (NumberFormatException ex) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);

	}

	@DeleteMapping({ "users/{id}" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("id") String userID) {
		try {
			usersService.deleteUserById(Integer.valueOf(userID));
		} catch (NumberFormatException ex) {

		}
	}
}
