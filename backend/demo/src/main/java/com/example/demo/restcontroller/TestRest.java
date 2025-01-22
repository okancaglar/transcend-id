package com.example.demo.restcontroller;

import com.example.demo.dtos.response.ImmigrantBlockchainData;
import com.example.demo.dtos.response.LocationLogData;
import com.example.demo.dtos.request.OfficerLoginData;
import com.example.demo.exceptions.APIException;
import com.example.demo.services.blockchainservice.BlockchainService;
import com.example.demo.services.blockchainservice.JwtService;
import com.example.demo.services.blockchainservice.OfficerService;
import com.example.demo.services.blockchainservice.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/test")
@RestController
public class TestRest {

	private final BlockchainService blockchainService;
	private final UUIDService uuidService;
	private final OfficerService officerService;
	private final JwtService jwtService;
	private String uuidG = "";

	@Autowired
	public TestRest(BlockchainService service, UUIDService uuidService, OfficerService officerService, JwtService jwtService) {
		this.blockchainService = service;
		this.uuidService = uuidService;
		this.officerService = officerService;
		this.jwtService = jwtService;
	}

	@GetMapping("/")
	public String getIndex() throws APIException {


		String[] keyPair = blockchainService.generateKeyPair();
		String publicKey = keyPair[1];
		String privateKey = keyPair[0];



		System.out.println(publicKey);
		System.out.println(privateKey);

		String uuid = uuidService.createUUID();
		this.uuidG = uuid;
		System.out.println(uuid);
		String name = "test 1";
		String lastname = "test 1l";
		String ethnicity = "syrian";
		String creationTime = "10-12-2024 15:30";
		String officerId = "1";


		blockchainService.saveImmigrantOnChain(uuid, publicKey, name, lastname, ethnicity, creationTime, officerId);

		LocationLogData logData = new LocationLogData(this.uuidG, "izmir",  "10-12-2024 15:30",
				"1");

		blockchainService.logImmigrantLocation(logData);


		return "public key: " + publicKey + "\nprivate key: " + privateKey + "\n";
	}


	@GetMapping("/get/{id}")
	public String getUser(@PathVariable(value = "id") String id) throws APIException {

		ImmigrantBlockchainData immigrantBlockchainData = blockchainService.getImmigrantByUUID(id);

		List<LocationLogData> fetchedLogTest = blockchainService.getImmigrantLocationLogs(id);
		StringBuilder logs = new StringBuilder();

		for (LocationLogData data : fetchedLogTest) {
			logs.append(data.getImmigrantId() + "|" + data.getLocation() + "|" + data.getTimestamp() + "\n");
		}

		return immigrantBlockchainData.getUuid() + "\n" +
				immigrantBlockchainData.getName() + "\n" +
				immigrantBlockchainData.getCreationTime() + "\n" +
				logs.toString();

	}

	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestBody OfficerLoginData officerLoginData) throws APIException {

		officerService.authenticate(officerLoginData);
		String token = jwtService.generateToken(officerService.getOfficerById(Integer.parseInt(officerLoginData.getOfficerId())));
		return ResponseEntity.ok(token);

	}

}
