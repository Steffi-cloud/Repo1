package com.example.demo.exception;

import java.sql.SQLException;

public class EntityNotFoundException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public EntityNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
