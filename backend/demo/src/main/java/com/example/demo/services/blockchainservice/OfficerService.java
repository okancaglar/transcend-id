package com.example.demo.services.blockchainservice;


import com.example.demo.dtos.request.OfficerLoginData;
import com.example.demo.entities.Officer;
import com.example.demo.exceptions.APIException;
import com.example.demo.repositories.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OfficerService {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final OfficerRepository officerRepository;

	@Autowired
	public OfficerService(AuthenticationManager authenticationManager, JwtService jwtService, OfficerRepository officerRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.officerRepository = officerRepository;
	}


	public void authenticate(OfficerLoginData officerLoginData) throws APIException
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(officerLoginData.getOfficerId(),
					officerLoginData.getPassword()));
		} catch (AuthenticationException ex) {
			throw new APIException("Authorization failed", HttpStatus.UNAUTHORIZED, LocalDateTime.now().toString());
		}
	}


	public Officer getOfficerById(int id) {
		return officerRepository.findById(id).orElse(null);
	}

}
