package com.jsp.ECommerce1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ECommerce1.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
