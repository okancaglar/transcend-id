package com.example.demo.dtos.request;

public class ImmigrantGetRequestData {
	private String uuid;

	public ImmigrantGetRequestData() {
	}

	public ImmigrantGetRequestData(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
