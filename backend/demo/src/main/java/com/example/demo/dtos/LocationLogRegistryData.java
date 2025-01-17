package com.example.demo.dtos;

public class LocationLogRegistryData {

	private String immigrantId;
	private String location;

	public LocationLogRegistryData() {
	}

	public LocationLogRegistryData(String immigrantId, String location) {
		this.immigrantId = immigrantId;
		this.location = location;
	}

	public String getImmigrantId() {
		return immigrantId;
	}

	public void setImmigrantId(String immigrantId) {
		this.immigrantId = immigrantId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
