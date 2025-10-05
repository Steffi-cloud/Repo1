package com.yourorg.dto;

import java.util.Date;

public class UserProfileResponse {
	private String userId;
	private String name;
	private String phone;
	private String email;
	private String address;
	private Date createdAt;
	
	public UserProfileResponse() {
		
	}

	public UserProfileResponse(String userId, String name, String phone, String email, String address, Date createdAt) {
		super();
		this.userId = userId;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.createdAt = createdAt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
