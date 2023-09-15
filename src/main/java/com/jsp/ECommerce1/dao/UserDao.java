package com.jsp.ECommerce1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.ECommerce1.dto.User;
import com.jsp.ECommerce1.repository.UserRepository;

@Repository
public class UserDao {

	@Autowired
	UserRepository userRepository;

	public User SaveUser(User user) {

		return userRepository.save(user);
	}
}
