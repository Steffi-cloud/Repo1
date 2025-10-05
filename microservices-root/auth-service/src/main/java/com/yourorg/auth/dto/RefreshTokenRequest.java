package com.yourorg.auth.dto;

public class RefreshTokenRequest {

	
	private String refreshToken;
	
	public RefreshTokenRequest() {
		
	}

	public RefreshTokenRequest(String refreshToken) {
		super();
		this.refreshToken = refreshToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
