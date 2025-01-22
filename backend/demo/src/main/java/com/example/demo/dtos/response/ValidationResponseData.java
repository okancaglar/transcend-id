package com.example.demo.dtos.response;

public class ValidationResponseData {

	private boolean isValid;

	public ValidationResponseData() {
	}

	public ValidationResponseData(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean valid) {
		isValid = valid;
	}
}
