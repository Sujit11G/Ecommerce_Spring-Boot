package com.jsp.ECommerce1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ECommerce1.dto.Merchant;
import com.jsp.ECommerce1.dto.Product;
import com.jsp.ECommerce1.services.MerchantServices;

@RestController
public class MerchantController {

	@Autowired
	MerchantServices merchantServices;

	@PostMapping("/merchant")
	public Merchant saveMerchant(@RequestBody Merchant merchant) {

		return merchantServices.saveMerchant(merchant);
	}

	@PostMapping("/merchant/{mId}/product")
	public String saveProductbyMerchant(@RequestBody Product product, @PathVariable int mId) {

		return merchantServices.saveProductbyMerchant(product, mId);
	}
	
	
}
