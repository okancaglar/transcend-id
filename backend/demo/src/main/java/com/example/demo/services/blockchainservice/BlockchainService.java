package com.example.demo.services.blockchainservice;

import com.example.demo.dtos.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthEstimateGas;
import org.web3j.protocol.core.methods.response.EthGetCode;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import com.example.demo.blockchainwrapper.*;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

@Service
public class BlockchainService {

	private Web3j web3j;
	private Credentials credentials;
	private ImmigrantRegistry registryContract;
	private ImmigrantLocationLog locationContract;

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

		System.out.println(credentials.getEcKeyPair().getPrivateKey());

		System.out.println("credentials okay");
		// 3. Load deployed contracts

		StaticGasProvider gasProvider = new StaticGasProvider(
				BigInteger.valueOf(20_000_000_000L), // Gas price (in Wei)
				BigInteger.valueOf(10_000_000L)     // Gas limit
		);

		this.registryContract = ImmigrantRegistry.load(
				registryContractAddress, web3j, credentials, gasProvider
		);

		String address = registryContract.getContractAddress();
		System.out.println("Deployed at " + address);

		System.out.println(registryContract.isValid());

		EthGetCode ethGetCode = web3j.ethGetCode(registryContractAddress, DefaultBlockParameterName.LATEST).send();
		String code = ethGetCode.getCode();
		System.out.println("Bytecode at address " + registryContractAddress + ": " + code);





		if (code.equals("0x") || code.isEmpty()) {
			System.out.println("No code found at the address.");
		} else {
			System.out.println("Contract code exists.");
		}

		System.out.println("Connected to network ID: "
				+ web3j.netVersion().send().getNetVersion());

		System.out.println("Expected address: " + registryContractAddress);
		System.out.println("Loaded contract address: " + registryContract.getContractAddress());



		System.out.println("registry contract loaded suc");
		this.locationContract = ImmigrantLocationLog.load(
				locationContractAddress, web3j, credentials, new DefaultGasProvider()
		);
		String address2 = locationContract.getContractAddress();
		System.out.println("Deployed at " + address2);
		System.out.println("location contract loaded suc");

		String contractAddress2 = "0x2f85D247245F182fbBD835928f3395A1Af849647";

		// Verify contract validity

		System.out.println("Contracts loaded successfully.");

		EthEstimateGas estimateGas = web3j.ethEstimateGas(
				Transaction.createContractTransaction(
						credentials.getAddress(),
						BigInteger.ZERO, // No Ether sent
						BigInteger.valueOf(6_721_975L), // Gas limit
						ImmigrantRegistry.BINARY // Replace with actual bytecode
				)
		).send();

		if (estimateGas.hasError()) {
			System.out.println("Error estimating gas: " + estimateGas.getError().getMessage());
		} else {
			System.out.println("Estimated gas: " + estimateGas.getAmountUsed());
		}
	}

	/**
	 * Generate a new Ethereum public-private key pair for the immigrant.
	 *
	 * @return A string array where [0] = private key and [1] = public key
	 */
	public String[] generateKeyPair() {
		try {
			SecureRandom random = new SecureRandom();
			ECKeyPair keyPair = Keys.createEcKeyPair(random);
			String privateKey = keyPair.getPrivateKey().toString(16); // Hexadecimal private key
			String publicKey = Keys.getAddress(keyPair); // Ethereum address (public key)
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
	) {
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
			throw new RuntimeException("Error saving immigrant identity to blockchain.");
		}
	}

	/**
	 * Fetch an immigrant identity by UUID from the blockchain.
	 *
	 * @param uuid The unique ID used during createImmigrant
	 * @return ImmigrantData containing the chain-stored fields, or null if an error occurs
	 */
	public ImmigrantBlockchainData getImmigrantByUUID(String uuid) {
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
			e.printStackTrace();
			throw new RuntimeException("Error fetching immigrant identity from blockchain.", e);
		}
	}

	/**
	 * Log an immigrant's location on the blockchain.
	 */
	public void logImmigrantLocation(LocationLogData locationLogData) {
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
			throw new RuntimeException("Error logging immigrant location to blockchain.");
		}
	}

	/**
	 * Fetch location logs for an immigrant by their ID.
	 */
	public LocationLogData[] getImmigrantLocationLogs(String immigrantId) {
		try {
			// The generated method returns an array of LocationLog
			ImmigrantLocationLog.LocationLog[] logs = (ImmigrantLocationLog.LocationLog[]) locationContract.getLocationLogs(immigrantId).send().toArray();

			// Convert array -> stream -> map -> array
			return Arrays.stream(logs)
					.map(log -> new LocationLogData(
							log.immigrantId,
							log.location,
							log.timestamp,
							log.officerId
					))
					.toArray(LocationLogData[]::new);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error fetching immigrant location logs from blockchain.", e);
		}
	}
}
