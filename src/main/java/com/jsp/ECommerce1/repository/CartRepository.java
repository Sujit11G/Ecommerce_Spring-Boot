package com.jsp.ECommerce1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ECommerce1.dto.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
