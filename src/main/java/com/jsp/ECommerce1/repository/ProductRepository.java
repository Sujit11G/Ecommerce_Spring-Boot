package com.jsp.ECommerce1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ECommerce1.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
