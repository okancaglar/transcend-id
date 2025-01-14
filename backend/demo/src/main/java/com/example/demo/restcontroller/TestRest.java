package com.example.demo.restcontroller;

import com.example.demo.services.blockchainservice.BlockchainService;
import com.example.demo.services.blockchainservice.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {

	private BlockchainService blockchainService;
	private UUIDService uuidService;

	@Autowired
	public TestRest(BlockchainService service, UUIDService uuidService) {
		this.blockchainService = service;
		this.uuidService = uuidService;
	}

	@GetMapping("/")
	public String getIndex(){


		String[] keyPair = blockchainService.generateKeyPair();
		String publicKey = keyPair[1];
		String privateKey = keyPair[0];

		System.out.println(publicKey);
		System.out.println(privateKey);

		String uuid = uuidService.createUUID();
		String name = "test 1";
		String lastname = "test 1l";
		String ethnicity = "syrian";
		String creationTime = "10-12-2024 15:30";
		String officerId = "1";


		blockchainService.saveImmigrantOnChain(uuid, publicKey, name, lastname, ethnicity, creationTime, officerId);

		return "public key: " + publicKey + "\nprivate key: " + privateKey ;
	}



}
