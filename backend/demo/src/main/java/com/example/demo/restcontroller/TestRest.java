package com.example.demo.restcontroller;

import com.example.demo.blockchainwrapper.ImmigrantLocationLog;
import com.example.demo.dtos.ImmigrantBlockchainData;
import com.example.demo.dtos.LocationLogData;
import com.example.demo.services.blockchainservice.BlockchainService;
import com.example.demo.services.blockchainservice.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/test/")
@RestController
public class TestRest {

	private BlockchainService blockchainService;
	private UUIDService uuidService;
	private String uuidG = "";

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
	public String getUser(@PathVariable(value = "id") String id){

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

}
