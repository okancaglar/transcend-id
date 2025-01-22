package com.example.demo.restcontroller;

import com.example.demo.dtos.request.*;
import com.example.demo.dtos.response.*;
import com.example.demo.exceptions.APIException;
import com.example.demo.services.blockchainservice.BlockchainService;
import com.example.demo.services.blockchainservice.JwtService;
import com.example.demo.services.blockchainservice.OfficerService;
import com.example.demo.services.blockchainservice.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

@RestController()
@RequestMapping("/blockchain/api")
public class BlockChainOperationsController {

	private final BlockchainService blockchainService;
	private final OfficerService officerService;
	private final JwtService jwtService;
	private final UUIDService uuidService;
	private static final Logger logger = Logger.getLogger(BlockChainOperationsController.class.getName());

	@Autowired
	public BlockChainOperationsController(BlockchainService service, OfficerService officerService, JwtService jwtService, UUIDService uuidService){

		this.blockchainService = service;
		this.officerService = officerService;
		this.jwtService = jwtService;
		this.uuidService = uuidService;
	}


	@PostMapping("/login")
	public ResponseEntity<OfficerLoginResponseData> loginHandler(@RequestBody OfficerLoginData officerLoginData) throws APIException
	{
		System.out.println("some text at login");
		this.officerService.authenticate(officerLoginData);
		String token = jwtService.generateToken(officerService.getOfficerById(Integer.parseInt(officerLoginData.getOfficerId())));
		return new ResponseEntity<OfficerLoginResponseData>(new OfficerLoginResponseData(token, jwtService.getJwtExpiration()),
				HttpStatus.OK);
	}

	@PostMapping("/immigrant/register")
	public ResponseEntity<ImmigrantRegistryResponseData> registerHandler(@RequestBody ImmigrantRegistryData immigrantRegistryData) throws APIException {

		validateData(immigrantRegistryData);
		Authentication authenticatedOfficer = SecurityContextHolder.getContext().getAuthentication();
		String officerName = authenticatedOfficer.getName();

		if (officerName == null && authenticatedOfficer.getPrincipal() instanceof UserDetails) {
			officerName = ((UserDetails) authenticatedOfficer.getPrincipal()).getUsername();
		}
		System.out.println(officerName);

		String[] keyPair = blockchainService.generateKeyPair();
		String privateKey = keyPair[0];
		String publicKey = keyPair[1];

		String uuid = uuidService.createUUID();

		blockchainService.saveImmigrantOnChain(uuid, publicKey,immigrantRegistryData.getName(), immigrantRegistryData.getSurname(),
				immigrantRegistryData.getEthnicity(), getBlockchainCurrentTime(), authenticatedOfficer.getName());

		ImmigrantRegistryResponseData response = new ImmigrantRegistryResponseData(uuid, privateKey);
		System.out.println(response.getUuid());
		System.out.println(response.getPrivateKey());
		return new ResponseEntity<ImmigrantRegistryResponseData>(response, HttpStatus.OK);
	}

	@GetMapping("/immigrant/get")
	public ResponseEntity<ImmigrantBlockchainData> getImmigrantHandler(@RequestBody ImmigrantGetRequestData request) throws APIException {
		ImmigrantBlockchainData immigrantData = blockchainService.getImmigrantByUUID(request.getUuid());

		return new ResponseEntity<ImmigrantBlockchainData>(immigrantData, HttpStatus.OK);
	}

	private void validateData(ImmigrantRegistryData immigrantRegistryData) throws APIException {

		if (immigrantRegistryData.getName().isBlank() || immigrantRegistryData.getEthnicity().isBlank() ||
		immigrantRegistryData.getSurname().isBlank()) {
			throw new APIException("immigrant data is not valid", HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now().toString());
		}
	}

	@PostMapping("/location/log")
	public ResponseEntity<String> locationLogHandler(@RequestBody LocationLogRegistryData locationLog) throws APIException {
		Authentication authenticatedOfficer = SecurityContextHolder.getContext().getAuthentication();
		String officerId = authenticatedOfficer.getName();

		if (officerId == null && authenticatedOfficer.getPrincipal() instanceof UserDetails) {
			officerId = ((UserDetails) authenticatedOfficer.getPrincipal()).getUsername();
		}
		System.out.println(locationLog.getImmigrantId());

		blockchainService.logImmigrantLocation(new LocationLogData(locationLog.getImmigrantId(), locationLog.getLocation(),
		getBlockchainCurrentTime(),	officerId));

		return new ResponseEntity<>("Location logging operation is successful", HttpStatus.OK);
	}

	@GetMapping("/location/get")
	public ResponseEntity<List<LocationLogData>> getLocationLogs(@RequestBody GetLocationLogsRequestData getLogs) throws APIException {
		return new ResponseEntity<>(blockchainService.getImmigrantLocationLogs(getLogs.getUuid()), HttpStatus.OK);
	}

	@PostMapping("/immigrant/validation")
	public ResponseEntity<?> immigrantValidationHandler(@RequestBody ValidationRequestData validationRequestData) throws APIException {

		boolean validationResult = blockchainService.isValidIdentity(validationRequestData.getPrivateKey(),
				blockchainService.getImmigrantByUUID(validationRequestData.getImmigrantId()).getPublicKey());

		return new ResponseEntity<>(new ValidationResponseData(validationResult), HttpStatus.OK);

	}

	private String getBlockchainCurrentTime(){
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		return now.format(formatter);
	}


}
