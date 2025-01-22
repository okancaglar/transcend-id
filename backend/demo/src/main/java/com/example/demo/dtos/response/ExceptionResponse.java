package com.example.demo.dtos.response;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

	private HttpStatus status;
	private String timestamp;
	private String message;


	public ExceptionResponse(String message, HttpStatus status, String timestamp) {
		this.message = message;
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
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
