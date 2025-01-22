package com.example.demo.dtos.response;

public class ImmigrantRegistryResponseData {
	private String uuid;
	private String privateKey;

	public ImmigrantRegistryResponseData() {
	}

	public ImmigrantRegistryResponseData(String uuid, String privateKey) {
		this.uuid = uuid;
		this.privateKey = privateKey;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
}
