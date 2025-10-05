package com.example.ordersystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity {
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String product;
	private int quantity;
	private double price;

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
