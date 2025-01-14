package com.example.demo.blockchainwrapper;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/hyperledger-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.2.
 */
@SuppressWarnings("rawtypes")
public class SimpleTransaction extends Contract {
    public static final String BINARY = "6080604052348015600e575f5ffd5b506106fa8061001c5f395ff3fe608060405234801561000f575f5ffd5b506004361061003f575f3560e01c80632a1afcd9146100435780633bc5de301461006157806347064d6a1461007f575b5f5ffd5b61004b61009b565b604051610058919061028a565b60405180910390f35b610069610126565b604051610076919061028a565b60405180910390f35b61009960048036038101906100949190610313565b6101b5565b005b5f80546100a79061038b565b80601f01602080910402602001604051908101604052809291908181526020018280546100d39061038b565b801561011e5780601f106100f55761010080835404028352916020019161011e565b820191905f5260205f20905b81548152906001019060200180831161010157829003601f168201915b505050505081565b60605f80546101349061038b565b80601f01602080910402602001604051908101604052809291908181526020018280546101609061038b565b80156101ab5780601f10610182576101008083540402835291602001916101ab565b820191905f5260205f20905b81548152906001019060200180831161018e57829003601f168201915b5050505050905090565b81815f91826101c592919061059b565b503373ffffffffffffffffffffffffffffffffffffffff167f40febe3efa738b4c01591748406de2c1f0351e46453c08c030351268e87bcdff838360405161020e9291906106a2565b60405180910390a25050565b5f81519050919050565b5f82825260208201905092915050565b8281835e5f83830152505050565b5f601f19601f8301169050919050565b5f61025c8261021a565b6102668185610224565b9350610276818560208601610234565b61027f81610242565b840191505092915050565b5f6020820190508181035f8301526102a28184610252565b905092915050565b5f5ffd5b5f5ffd5b5f5ffd5b5f5ffd5b5f5ffd5b5f5f83601f8401126102d3576102d26102b2565b5b8235905067ffffffffffffffff8111156102f0576102ef6102b6565b5b60208301915083600182028301111561030c5761030b6102ba565b5b9250929050565b5f5f60208385031215610329576103286102aa565b5b5f83013567ffffffffffffffff811115610346576103456102ae565b5b610352858286016102be565b92509250509250929050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f60028204905060018216806103a257607f821691505b6020821081036103b5576103b461035e565b5b50919050565b5f82905092915050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f6008830261044e7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610413565b6104588683610413565b95508019841693508086168417925050509392505050565b5f819050919050565b5f819050919050565b5f61049c61049761049284610470565b610479565b610470565b9050919050565b5f819050919050565b6104b583610482565b6104c96104c1826104a3565b84845461041f565b825550505050565b5f5f905090565b6104e06104d1565b6104eb8184846104ac565b505050565b5b8181101561050e576105035f826104d8565b6001810190506104f1565b5050565b601f82111561055357610524816103f2565b61052d84610404565b8101602085101561053c578190505b61055061054885610404565b8301826104f0565b50505b505050565b5f82821c905092915050565b5f6105735f1984600802610558565b1980831691505092915050565b5f61058b8383610564565b9150826002028217905092915050565b6105a583836103bb565b67ffffffffffffffff8111156105be576105bd6103c5565b5b6105c8825461038b565b6105d3828285610512565b5f601f831160018114610600575f84156105ee578287013590505b6105f88582610580565b86555061065f565b601f19841661060e866103f2565b5f5b8281101561063557848901358255600182019150602085019450602081019050610610565b86831015610652578489013561064e601f891682610564565b8355505b6001600288020188555050505b50505050505050565b828183375f83830152505050565b5f6106818385610224565b935061068e838584610668565b61069783610242565b840190509392505050565b5f6020820190508181035f8301526106bb818486610676565b9050939250505056fea264697066735822122045350430863df0f0c6fd08861222e1b4766a326c9d1aa3151142bf1b80db38a664736f6c634300081c0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_GETDATA = "getData";

    public static final String FUNC_SETDATA = "setData";

    public static final String FUNC_STOREDDATA = "storedData";

    public static final Event DATASAVED_EVENT = new Event("DataSaved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected SimpleTransaction(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SimpleTransaction(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SimpleTransaction(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SimpleTransaction(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<DataSavedEventResponse> getDataSavedEvents(
            TransactionReceipt transactionReceipt) {

        ArrayList<DataSavedEventResponse> responses = new ArrayList<DataSavedEventResponse>();
        for (Log log : transactionReceipt.getLogs()) {
            Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DATASAVED_EVENT, log);
            DataSavedEventResponse typedResponse = new DataSavedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.data = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static DataSavedEventResponse getDataSavedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(DATASAVED_EVENT, log);
        DataSavedEventResponse typedResponse = new DataSavedEventResponse();
        typedResponse.log = log;
        typedResponse.sender = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.data = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<DataSavedEventResponse> dataSavedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getDataSavedEventFromLog(log));
    }

    public Flowable<DataSavedEventResponse> dataSavedEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DATASAVED_EVENT));
        return dataSavedEventFlowable(filter);
    }

    public RemoteFunctionCall<String> getData() {
        final Function function = new Function(FUNC_GETDATA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setData(String _data) {
        final Function function = new Function(
                FUNC_SETDATA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> storedData() {
        final Function function = new Function(FUNC_STOREDDATA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static SimpleTransaction load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleTransaction(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SimpleTransaction load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SimpleTransaction(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SimpleTransaction load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new SimpleTransaction(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SimpleTransaction load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SimpleTransaction(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SimpleTransaction> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SimpleTransaction.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SimpleTransaction> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleTransaction.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<SimpleTransaction> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SimpleTransaction.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SimpleTransaction> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SimpleTransaction.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<?> references) {

    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class DataSavedEventResponse extends BaseEventResponse {
        public String sender;

        public String data;
    }
}
