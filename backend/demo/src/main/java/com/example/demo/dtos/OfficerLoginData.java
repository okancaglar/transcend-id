package com.example.demo.dtos;

public class OfficerLoginData {

	private String officerId;
	private String password;

	public OfficerLoginData(){}

	public OfficerLoginData(String officerId, String password) {
		this.officerId = officerId;
		this.password = password;
	}

	public String getOfficerId() {
		return officerId;
	}

	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
