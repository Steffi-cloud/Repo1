package com.yourorg.auth.dto;

public class AuthResponse {

	private String accessToken;

	private String refreshToken;

	private int expiresIn;

	public AuthResponse() {

	}

	public AuthResponse(String accessToken, String refreshToken, int expiresIn) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.expiresIn = expiresIn;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

}
