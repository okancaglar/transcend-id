package com.example.demo.restcontroller;

import com.example.demo.services.blockchainservice.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/blockchain/api")
public class BlockChainOperationsController {

	private BlockchainService blockchainService;


	@Autowired
	public BlockChainOperationsController(BlockchainService service){

		this.blockchainService = service;
	}



}
