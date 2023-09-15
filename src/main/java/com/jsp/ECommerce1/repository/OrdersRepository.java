package com.jsp.ECommerce1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ECommerce1.dto.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}
