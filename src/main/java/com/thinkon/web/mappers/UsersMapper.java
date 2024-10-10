/**
 * Created by Phanindra on 2024-10-09
 */
package com.thinkon.web.mappers;

import org.mapstruct.Mapper;

import com.thinkon.domain.Users;
import com.thinkon.model.UsersDTO;

/**
 * 
 */
@Mapper
public interface UsersMapper {
	
	public Users UsersDTOtoUsers(UsersDTO usersDto);

	public UsersDTO UserstoUsersDTO(Users users);
}
