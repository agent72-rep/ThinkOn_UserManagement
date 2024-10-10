package com.thinkon.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thinkon.domain.Users;

/**
 * Created by Phanindra on 2024-10-09
 */
public interface UsersRepository extends JpaRepository<Users, Integer> {

	Page<Users> findAllByFirstNameAndLastNameOrderByUserId(String firstName, String lastName, Pageable pageable);

	Page<Users> findAllByFirstNameOrderByUserId(String firstName, Pageable pageable);

	Page<Users> findAllByLastNameOrderByUserId(String lastName, Pageable pageable);
}
