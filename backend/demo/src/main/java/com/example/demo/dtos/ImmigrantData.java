package com.example.demo.dtos;

public class ImmigrantData {
	private String uuid;
	private String name;
	private String surname;
	private String ethnicity;
	private String creationTime;
	private String officerId;

	public ImmigrantData() {}

	public ImmigrantData(String uuid, String name, String surname, String ethnicity, String creationTime, String officerId) {
		this.uuid = uuid;
		this.name = name;
		this.surname = surname;
		this.ethnicity = ethnicity;
		this.creationTime = creationTime;
		this.officerId = officerId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getOfficerId() {
		return officerId;
	}

	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}
}

