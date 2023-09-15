package com.jsp.ECommerce1.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Merchant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnore
	@OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
	private List<Product> products;
	
	@JsonIgnore
	@OneToMany(mappedBy = "merchant",cascade = CascadeType.ALL)
	private List<Orders>orders;
}
