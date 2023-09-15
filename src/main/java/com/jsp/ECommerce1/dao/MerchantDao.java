package com.jsp.ECommerce1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jsp.ECommerce1.dto.Merchant;
import com.jsp.ECommerce1.repository.MerchantRepository;

@Repository
public class MerchantDao {

	@Autowired
	private MerchantRepository merchantRepository;

	public Merchant saveMerchant(Merchant merchant) {

		return merchantRepository.save(merchant);
	}

}
