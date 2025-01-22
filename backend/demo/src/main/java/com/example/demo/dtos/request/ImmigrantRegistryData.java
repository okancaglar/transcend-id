package com.example.demo.dtos.request;

public class ImmigrantRegistryData {
	private String name;
	private String surname;
	private String ethnicity;


	public ImmigrantRegistryData() {}

	public ImmigrantRegistryData(String name, String surname, String ethnicity) {
		this.name = name;
		this.surname = surname;
		this.ethnicity = ethnicity;
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
}

