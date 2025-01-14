package com.example.demo.services.blockchainservice;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDService {

	public String createUUID() {
		return UUID.randomUUID().toString();
	}

}
