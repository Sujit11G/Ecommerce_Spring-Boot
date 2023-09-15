package com.jsp.ECommerce1.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn
	private User user;
	
//	@Temporal(TemporalType.TIMESTAMP)
//    private Date orderDate;
	
	@ManyToOne
	@JoinColumn
	private Merchant merchant;
	
	@ManyToMany
	@JoinTable(name = "orders_product", joinColumns = @JoinColumn(name = "orders_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;
}
