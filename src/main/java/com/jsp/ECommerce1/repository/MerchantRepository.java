package com.jsp.ECommerce1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ECommerce1.dto.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
	

}
