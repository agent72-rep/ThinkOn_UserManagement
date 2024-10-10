/**
 * Created by Phanindra on 2024-10-09
 */
package com.thinkon.services;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.thinkon.domain.Users;
import com.thinkon.model.UserPagedList;
import com.thinkon.model.UsersDTO;
import com.thinkon.repositories.UsersRepository;
import com.thinkon.web.controller.NotFoundException;
import com.thinkon.web.mappers.UsersMapper;

import lombok.RequiredArgsConstructor;

/**
 * 
 */
@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

	private final UsersMapper usersMapper;
	private final UsersRepository usersRepository;

	@Override
	public UsersDTO saveUsers(UsersDTO userDTO) {
		return usersMapper.UserstoUsersDTO(usersRepository.save(usersMapper.UsersDTOtoUsers(userDTO)));
	}

	@Override
	public UsersDTO updateUserDetails(Integer userID, UsersDTO usersDTO) {
		Users userDetails = usersRepository.findById(userID).orElseThrow(NotFoundException::new);

		if (usersDTO.getEmail() != null) {
			userDetails.setEmail(usersDTO.getEmail());
		}
		userDetails.setFirstName(usersDTO.getFirstName());

		if (usersDTO.getLastName() != null) {
			userDetails.setLastName(usersDTO.getLastName());
		}
		if (usersDTO.getPhoneNo() != null) {
			userDetails.setPhoneNo(usersDTO.getPhoneNo());
		}
		userDetails.setUserName(usersDTO.getUserName());

		return usersMapper.UserstoUsersDTO(usersRepository.save(userDetails));
	}

	@Override
	public UsersDTO getUserById(Integer userID) {
		return usersMapper.UserstoUsersDTO(usersRepository.findById(userID).orElseThrow(NotFoundException::new));
	}

	@Override
	public void deleteUserById(Integer userID) {
		usersRepository.deleteById(userID);
	}

	@Override
	public UserPagedList listUsers(PageRequest pageRequest, String firstName, String lastName) {

		UserPagedList userPagedList;
		Page<Users> userPage;

		if (StringUtils.hasText(firstName) && StringUtils.hasText(lastName)) {
			// search both
			userPage = usersRepository.findAllByFirstNameAndLastNameOrderByUserId(firstName, lastName, pageRequest);
		} else if (StringUtils.hasText(firstName)) {
			// search only first name
			userPage = usersRepository.findAllByFirstNameOrderByUserId(firstName, pageRequest);
		} else if (StringUtils.hasText(lastName)) {
			// search only last name
			userPage = usersRepository.findAllByLastNameOrderByUserId(lastName, pageRequest);
		} else {
			userPage = usersRepository.findAll(pageRequest);
		}

		userPagedList = new UserPagedList(
				userPage.getContent().stream().map(usersMapper::UserstoUsersDTO).collect(Collectors.toList()),
				PageRequest.of(userPage.getPageable().getPageNumber(), userPage.getPageable().getPageSize()),
				userPage.getTotalElements());

		return userPagedList;
	}
}
