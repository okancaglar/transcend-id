package com.example.demo.exceptions;


import org.springframework.http.HttpStatus;

public class APIException extends Exception{

	private HttpStatus status;
	private String timestamp;


	public APIException(String message, HttpStatus status, String timestamp) {
		super(message);
		this.status = status;
		this.timestamp = timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
