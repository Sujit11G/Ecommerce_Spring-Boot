package com.jsp.ECommerce1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ECommerce1.dto.Product;
import com.jsp.ECommerce1.dto.User;
import com.jsp.ECommerce1.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public User saveUser(@RequestBody  User user) {
		
		return userService.saveUser(user);
	}
	
	@GetMapping("/user/products")
	public List<Product> getAllProducts(){
		 
		return userService.getAllProducts();
	}
	
	@PostMapping("user/cart/{uId}/{pId}")
	public String addProductinCart(@PathVariable int uId,@PathVariable int pId) {
		
		return userService.addProductToCart(uId, pId);
	}
	
	@PostMapping("/user/order/{uId}/{pId}")
	public String orderPlace(@PathVariable int uId,@PathVariable int pId) {
		
		return userService.orderPlace(uId, pId);
	}
}
