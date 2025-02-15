package com.example.demo.services.blockchainservice;

import com.example.demo.dtos.response.ImmigrantBlockchainData;
import com.example.demo.dtos.response.LocationLogData;
import com.example.demo.exceptions.APIException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import com.example.demo.blockchainwrapper.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlockchainService {

	private Web3j web3j;
	private Credentials credentials;
	private ImmigrantRegistry registryContract;
	private ImmigrantLocationLog locationContract;

	private static final String VALIDATION_STRING = "ImmigrantValidationString";

	// Blockchain and contract configuration injected via application.properties or application.yml
	@Value("${blockchain.nodeUrl}")
	private String ganacheUrl;

	@Value("${blockchain.privateKey}")
	private String privateKey;

	@Value("${blockchain.registryContractAddress}")
	private String registryContractAddress;

	@Value("${blockchain.locationContractAddress}")
	private String locationContractAddress;

	@PostConstruct
	public void init() throws Exception {
		// 1. Connect to the blockchain
		System.out.println("before web3j build");
		this.web3j = Web3j.build(new HttpService(ganacheUrl));
		System.out.println("Connected to Ethereum client version: "
				+ web3j.web3ClientVersion().send().getWeb3ClientVersion());

		// 2. Load credentials
		this.credentials = Credentials.create(privateKey);

		// 3. Load deployed contracts
		System.out.println("registry contract loaded suc");
		this.locationContract = ImmigrantLocationLog.load(
				locationContractAddress, web3j, credentials, new DefaultGasProvider()
		);

		this.registryContract = ImmigrantRegistry.load(
				registryContractAddress, web3j, credentials, new DefaultGasProvider()
		);
		System.out.println("Connected to network ID: "
				+ web3j.netVersion().send().getNetVersion());

		// Verify contract validity
		System.out.println("Contracts loaded successfully.");
	}

	/**
	 * Generate a new Ethereum public-private key pair for the immigrant.
	 *
	 * @return A string array where [0] = private key and [1] = public key
	 */
	public String[] generateKeyPair() {
		try {
			ECKeyPair keyPair = Keys.createEcKeyPair();
			String privateKey = keyPair.getPrivateKey().toString(16);
			String publicKey = "0x" + Keys.getAddress(keyPair);
			return new String[]{privateKey, publicKey};
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error generating key pair.");
		}
	}

	/**
	 * Save an immigrant identity to the blockchain.
	 *
	 * @param uuid         Unique ID for the immigrant
	 * @param publicKey    Public key of the immigrant
	 * @param name         Immigrant's first name
	 * @param surname      Immigrant's surname
	 * @param ethnicity    Some ethnicity field
	 * @param creationTime e.g., "10-01-2025 12:00"
	 * @param officerId    The officer who is creating this record
	 */
	public void saveImmigrantOnChain(
			String uuid,
			String publicKey,
			String name,
			String surname,
			String ethnicity,
			String creationTime,
			String officerId
	) throws APIException {
		try {
			registryContract.createImmigrant(
					uuid,
					publicKey,
					name,
					surname,
					ethnicity,
					creationTime,
					officerId
			).send();
			System.out.println("Immigrant identity saved to blockchain.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new APIException("Error saving immigrant to blockchain.", HttpStatus.BAD_REQUEST,
					LocalDateTime.now().toString());
		}
	}

	/**
	 * Fetch an immigrant identity by UUID from the blockchain.
	 *
	 * @param uuid The unique ID used during createImmigrant
	 * @return ImmigrantData containing the chain-stored fields, or null if an error occurs
	 */
	public ImmigrantBlockchainData getImmigrantByUUID(String uuid) throws APIException {
		try {
			// Call the 'getImmigrant' function from the wrapper,
			// which returns a Tuple7 of strings.
			var result = registryContract.getImmigrant(uuid).send();
			// result is a Tuple7<String, String, String, String, String, String, String>:
			//   component1(): id
			//   component2(): publicKey
			//   component3(): name
			//   component4(): lastName
			//   component5(): ethnicity
			//   component6(): creationTime
			//   component7(): officerId

			return new ImmigrantBlockchainData(
					result.component1(), // id (UUID)
					result.component2(), // publicKey
					result.component3(), // name
					result.component4(), // lastName
					result.component5(), // ethnicity
					result.component6(), // creationTime
					result.component7()  // officerId
			);
		} catch (Exception e) {
			throw new APIException("Error fetching immigrant identity from blockchain.", HttpStatus.NO_CONTENT,
					LocalDateTime.now().toString());
		}
	}

	/**
	 * Log an immigrant's location on the blockchain.
	 */
	public void logImmigrantLocation(LocationLogData locationLogData) throws APIException {
		try {
			locationContract.logLocation(
					locationLogData.getImmigrantId(),
					locationLogData.getLocation(),
					locationLogData.getTimestamp(),
					locationLogData.getOfficerId()
			).send();
			System.out.println("Immigrant location logged on blockchain.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new APIException("Error logging immigrant location to blockchain.", HttpStatus.BAD_REQUEST,
					LocalDateTime.now().toString());
		}
	}

	/**
	 * Fetch location logs for an immigrant by their ID.
	 */
	public List<LocationLogData> getImmigrantLocationLogs(String immigrantId) throws APIException {
		try {

			// The generated method returns an array of LocationLog
			List<List<Object>> logs = locationContract.getLocationLogs(immigrantId).send();

			// Map logs to LocationLogData DTOs
			return logs.stream()
					.map(log -> new LocationLogData(
							log.get(0).toString(), // Immigrant ID
							log.get(1).toString(), // Location
							log.get(2).toString(), // Timestamp
							log.get(3).toString()  // Officer ID
					))
					.collect(Collectors.toList());

		} catch (Exception e) {
			e.printStackTrace();
			throw new APIException("Error fetching immigrant location logs from blockchain.", HttpStatus.NO_CONTENT,
					LocalDateTime.now().toString());
		}
	}

	public boolean isValidIdentity(String privateKeyHex, String providedPublicKeyHex) {
		try {
			BigInteger privateKey = new BigInteger(privateKeyHex, 16);
			ECKeyPair keyPair = ECKeyPair.create(privateKey);

			// Derive the public key from the private key
			String derivedPublicKey = "0x" + Keys.getAddress(keyPair.getPublicKey());

			System.out.println("Derived Public Key: " + derivedPublicKey);
			System.out.println("Provided Public Key: " + providedPublicKeyHex);

			// Compare the derived public key with the provided one
			return derivedPublicKey.equalsIgnoreCase(providedPublicKeyHex);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
