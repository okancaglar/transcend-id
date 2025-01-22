package com.example.demo.dtos.response;

public class OfficerLoginResponseData {

	private String token;
	private long expirationTime;

	public void setToken(String token) {
		this.token = token;
	}

	public void setExpirationTime(long expirationTime) {
		this.expirationTime = expirationTime;
	}

	public OfficerLoginResponseData(String token, long expirationTime) {
		this.token = token;
		this.expirationTime = expirationTime;
	}

	public String getToken() {
		return token;
	}

	public long getExpirationTime() {
		return expirationTime;
	}
}
