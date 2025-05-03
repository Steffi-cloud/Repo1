package com.example.demo.exception;

public class DatabaseErrorResponse {
	private int status;
	public DatabaseErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	private String message;

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
