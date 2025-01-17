package com.example.demo.restcontroller;


import com.example.demo.services.blockchainservice.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {

	private BlockchainService blockchainService;

	@Autowired
	public StaticController(BlockchainService service){
		this.blockchainService = service;
	}

	@GetMapping("/")
	public String loginPage() {
		return "login";
	}




}
