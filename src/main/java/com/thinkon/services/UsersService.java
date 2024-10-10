/**
 * Created by Phanindra on 2024-10-09
 */
package com.thinkon.services;

import org.springframework.data.domain.PageRequest;

import com.thinkon.model.UserPagedList;
import com.thinkon.model.UsersDTO;

/**
 * 
 */
public interface UsersService {

	/**
	 * @param userDto
	 * @return UsersDTO
	 */
	UsersDTO saveUsers(UsersDTO userDto);

	/**
	 * @param userID
	 * @param usersDTO
	 * @return UsersDTO
	 */
	UsersDTO updateUserDetails(Integer userID, UsersDTO usersDTO);

	/**
	 * @param userID
	 * @return UsersDTO
	 */
	UsersDTO getUserById(Integer userID);

	/**
	 * @param userID
	 */
	void deleteUserById(Integer userID);

	/**
	 * @param pageRequest
	 * @param firstName
	 * @param lastName
	 * @return UserPagedList
	 */
	UserPagedList listUsers(PageRequest pageRequest, String firstName, String lastName);

}
