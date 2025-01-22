package com.example.demo.controlleradvice;

import com.example.demo.dtos.response.ExceptionResponse;
import com.example.demo.exceptions.APIException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class BlockchainApiControllerAdvice {


	@ExceptionHandler({APIException.class})
	public ResponseEntity<ExceptionResponse> itemNotFoundHandler(APIException ex){
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(ex.getMessage(), ex.getStatus(), LocalDateTime.now().toString()),
				ex.getStatus());
	}



}
