package com.example.demo.dtos.response;

public class LocationLogData {
	private String immigrantId;
	private String location;
	private String timestamp;
	private String officerId;

	public LocationLogData() {}

	public LocationLogData(String immigrantId, String location, String timestamp, String officerId) {
		this.immigrantId = immigrantId;
		this.location = location;
		this.timestamp = timestamp;
		this.officerId = officerId;
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getOfficerId() {
		return officerId;
	}

	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}
}
