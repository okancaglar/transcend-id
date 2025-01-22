package com.example.demo.dtos.request;

public class ValidationRequestData {
	private String immigrantId;
	private String privateKey;

	public String getImmigrantId() {
		return immigrantId;
	}

	public void setImmigrantId(String immigrantId) {
		this.immigrantId = immigrantId;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
}
