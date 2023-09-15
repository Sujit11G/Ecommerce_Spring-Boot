package com.jsp.ECommerce1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ECommerce1.dto.CartProduct;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {

}
