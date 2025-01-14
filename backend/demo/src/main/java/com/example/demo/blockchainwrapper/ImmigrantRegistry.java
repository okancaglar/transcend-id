package com.example.demo.blockchainwrapper;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
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
import org.web3j.tuples.generated.Tuple7;
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
public class ImmigrantRegistry extends Contract {
    public static final String BINARY = "6080604052348015600e575f5ffd5b50610f088061001c5f395ff3fe608060405234801561000f575f5ffd5b5060043610610034575f3560e01c806339804c2014610038578063c9a9a9f21461006e575b5f5ffd5b610052600480360381019061004d91906107f0565b61008a565b6040516100659796959493929190610897565b60405180910390f35b61008860048036038101906100839190610935565b6104eb565b005b60608060608060608060605f5f896040516100a59190610ad0565b90815260200160405180910390206040518060e00160405290815f820180546100cd90610b13565b80601f01602080910402602001604051908101604052809291908181526020018280546100f990610b13565b80156101445780601f1061011b57610100808354040283529160200191610144565b820191905f5260205f20905b81548152906001019060200180831161012757829003601f168201915b5050505050815260200160018201805461015d90610b13565b80601f016020809104026020016040519081016040528092919081815260200182805461018990610b13565b80156101d45780601f106101ab576101008083540402835291602001916101d4565b820191905f5260205f20905b8154815290600101906020018083116101b757829003601f168201915b505050505081526020016002820180546101ed90610b13565b80601f016020809104026020016040519081016040528092919081815260200182805461021990610b13565b80156102645780601f1061023b57610100808354040283529160200191610264565b820191905f5260205f20905b81548152906001019060200180831161024757829003601f168201915b5050505050815260200160038201805461027d90610b13565b80601f01602080910402602001604051908101604052809291908181526020018280546102a990610b13565b80156102f45780601f106102cb576101008083540402835291602001916102f4565b820191905f5260205f20905b8154815290600101906020018083116102d757829003601f168201915b5050505050815260200160048201805461030d90610b13565b80601f016020809104026020016040519081016040528092919081815260200182805461033990610b13565b80156103845780601f1061035b57610100808354040283529160200191610384565b820191905f5260205f20905b81548152906001019060200180831161036757829003601f168201915b5050505050815260200160058201805461039d90610b13565b80601f01602080910402602001604051908101604052809291908181526020018280546103c990610b13565b80156104145780601f106103eb57610100808354040283529160200191610414565b820191905f5260205f20905b8154815290600101906020018083116103f757829003601f168201915b5050505050815260200160068201805461042d90610b13565b80601f016020809104026020016040519081016040528092919081815260200182805461045990610b13565b80156104a45780601f1061047b576101008083540402835291602001916104a4565b820191905f5260205f20905b81548152906001019060200180831161048757829003601f168201915b5050505050815250509050805f015181602001518260400151836060015184608001518560a001518660c00151975097509750975097509750975050919395979092949650565b5f5f886040516104fb9190610ad0565b90815260200160405180910390205f01805461051690610b13565b905014610558576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161054f90610bb3565b60405180910390fd5b6040518060e00160405280888152602001878152602001868152602001858152602001848152602001838152602001828152505f8860405161059a9190610ad0565b90815260200160405180910390205f820151815f0190816105bb9190610d7a565b5060208201518160010190816105d19190610d7a565b5060408201518160020190816105e79190610d7a565b5060608201518160030190816105fd9190610d7a565b5060808201518160040190816106139190610d7a565b5060a08201518160050190816106299190610d7a565b5060c082015181600601908161063f9190610d7a565b50905050866040516106519190610ad0565b60405180910390207ff8b8c17660126f73accfe96a3ab1e08d40c4af57ae1f3fb43b6c5322ba13bcdb87878787878760405161069296959493929190610e49565b60405180910390a250505050505050565b5f604051905090565b5f5ffd5b5f5ffd5b5f5ffd5b5f5ffd5b5f601f19601f8301169050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b610702826106bc565b810181811067ffffffffffffffff82111715610721576107206106cc565b5b80604052505050565b5f6107336106a3565b905061073f82826106f9565b919050565b5f67ffffffffffffffff82111561075e5761075d6106cc565b5b610767826106bc565b9050602081019050919050565b828183375f83830152505050565b5f61079461078f84610744565b61072a565b9050828152602081018484840111156107b0576107af6106b8565b5b6107bb848285610774565b509392505050565b5f82601f8301126107d7576107d66106b4565b5b81356107e7848260208601610782565b91505092915050565b5f60208284031215610805576108046106ac565b5b5f82013567ffffffffffffffff811115610822576108216106b0565b5b61082e848285016107c3565b91505092915050565b5f81519050919050565b5f82825260208201905092915050565b8281835e5f83830152505050565b5f61086982610837565b6108738185610841565b9350610883818560208601610851565b61088c816106bc565b840191505092915050565b5f60e0820190508181035f8301526108af818a61085f565b905081810360208301526108c3818961085f565b905081810360408301526108d7818861085f565b905081810360608301526108eb818761085f565b905081810360808301526108ff818661085f565b905081810360a0830152610913818561085f565b905081810360c0830152610927818461085f565b905098975050505050505050565b5f5f5f5f5f5f5f60e0888a0312156109505761094f6106ac565b5b5f88013567ffffffffffffffff81111561096d5761096c6106b0565b5b6109798a828b016107c3565b975050602088013567ffffffffffffffff81111561099a576109996106b0565b5b6109a68a828b016107c3565b965050604088013567ffffffffffffffff8111156109c7576109c66106b0565b5b6109d38a828b016107c3565b955050606088013567ffffffffffffffff8111156109f4576109f36106b0565b5b610a008a828b016107c3565b945050608088013567ffffffffffffffff811115610a2157610a206106b0565b5b610a2d8a828b016107c3565b93505060a088013567ffffffffffffffff811115610a4e57610a4d6106b0565b5b610a5a8a828b016107c3565b92505060c088013567ffffffffffffffff811115610a7b57610a7a6106b0565b5b610a878a828b016107c3565b91505092959891949750929550565b5f81905092915050565b5f610aaa82610837565b610ab48185610a96565b9350610ac4818560208601610851565b80840191505092915050565b5f610adb8284610aa0565b915081905092915050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f6002820490506001821680610b2a57607f821691505b602082108103610b3d57610b3c610ae6565b5b50919050565b7f496d6d696772616e742077697468207468697320494420616c726561647920655f8201527f78697374732e0000000000000000000000000000000000000000000000000000602082015250565b5f610b9d602683610841565b9150610ba882610b43565b604082019050919050565b5f6020820190508181035f830152610bca81610b91565b9050919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f60088302610c2d7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610bf2565b610c378683610bf2565b95508019841693508086168417925050509392505050565b5f819050919050565b5f819050919050565b5f610c7b610c76610c7184610c4f565b610c58565b610c4f565b9050919050565b5f819050919050565b610c9483610c61565b610ca8610ca082610c82565b848454610bfe565b825550505050565b5f5f905090565b610cbf610cb0565b610cca818484610c8b565b505050565b5b81811015610ced57610ce25f82610cb7565b600181019050610cd0565b5050565b601f821115610d3257610d0381610bd1565b610d0c84610be3565b81016020851015610d1b578190505b610d2f610d2785610be3565b830182610ccf565b50505b505050565b5f82821c905092915050565b5f610d525f1984600802610d37565b1980831691505092915050565b5f610d6a8383610d43565b9150826002028217905092915050565b610d8382610837565b67ffffffffffffffff811115610d9c57610d9b6106cc565b5b610da68254610b13565b610db1828285610cf1565b5f60209050601f831160018114610de2575f8415610dd0578287015190505b610dda8582610d5f565b865550610e41565b601f198416610df086610bd1565b5f5b82811015610e1757848901518255600182019150602085019450602081019050610df2565b86831015610e345784890151610e30601f891682610d43565b8355505b6001600288020188555050505b505050505050565b5f60c0820190508181035f830152610e61818961085f565b90508181036020830152610e75818861085f565b90508181036040830152610e89818761085f565b90508181036060830152610e9d818661085f565b90508181036080830152610eb1818561085f565b905081810360a0830152610ec5818461085f565b905097965050505050505056fea264697066735822122095ae2b516dd46603d65f64927642cd546bbe378849f9d52b01b227b7d4b711cd64736f6c634300081c0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_CREATEIMMIGRANT = "createImmigrant";

    public static final String FUNC_GETIMMIGRANT = "getImmigrant";

    public static final Event IMMIGRANTCREATED_EVENT = new Event("ImmigrantCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected ImmigrantRegistry(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ImmigrantRegistry(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ImmigrantRegistry(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ImmigrantRegistry(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ImmigrantCreatedEventResponse> getImmigrantCreatedEvents(
            TransactionReceipt transactionReceipt) {

        ArrayList<ImmigrantCreatedEventResponse> responses = new ArrayList<ImmigrantCreatedEventResponse>();
        for (Log log : transactionReceipt.getLogs()) {
            Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(IMMIGRANTCREATED_EVENT, log);

            ImmigrantCreatedEventResponse typedResponse = new ImmigrantCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.publicKey = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.lastName = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.ethnicity = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.creationTime = (String) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.officerId = (String) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ImmigrantCreatedEventResponse getImmigrantCreatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(IMMIGRANTCREATED_EVENT, log);
        ImmigrantCreatedEventResponse typedResponse = new ImmigrantCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.id = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.publicKey = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.lastName = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.ethnicity = (String) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.creationTime = (String) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.officerId = (String) eventValues.getNonIndexedValues().get(5).getValue();
        return typedResponse;
    }

    public Flowable<ImmigrantCreatedEventResponse> immigrantCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getImmigrantCreatedEventFromLog(log));
    }

    public Flowable<ImmigrantCreatedEventResponse> immigrantCreatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(IMMIGRANTCREATED_EVENT));
        return immigrantCreatedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> createImmigrant(String _id, String _publicKey,
            String _name, String _lastName, String _ethnicity, String _creationTime,
            String _officerId) {
        final Function function = new Function(
                FUNC_CREATEIMMIGRANT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_id), 
                new org.web3j.abi.datatypes.Utf8String(_publicKey), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_lastName), 
                new org.web3j.abi.datatypes.Utf8String(_ethnicity), 
                new org.web3j.abi.datatypes.Utf8String(_creationTime), 
                new org.web3j.abi.datatypes.Utf8String(_officerId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple7<String, String, String, String, String, String, String>> getImmigrant(
            String _id) {
        final Function function = new Function(FUNC_GETIMMIGRANT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple7<String, String, String, String, String, String, String>>(function,
                new Callable<Tuple7<String, String, String, String, String, String, String>>() {
                    @Override
                    public Tuple7<String, String, String, String, String, String, String> call()
                            throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<String, String, String, String, String, String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (String) results.get(6).getValue());
                    }
                });
    }

    @Deprecated
    public static ImmigrantRegistry load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ImmigrantRegistry(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ImmigrantRegistry load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ImmigrantRegistry(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ImmigrantRegistry load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ImmigrantRegistry(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ImmigrantRegistry load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ImmigrantRegistry(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ImmigrantRegistry> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ImmigrantRegistry.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ImmigrantRegistry> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ImmigrantRegistry.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<ImmigrantRegistry> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ImmigrantRegistry.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ImmigrantRegistry> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ImmigrantRegistry.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class ImmigrantCreatedEventResponse extends BaseEventResponse {
        public byte[] id;

        public String publicKey;

        public String name;

        public String lastName;

        public String ethnicity;

        public String creationTime;

        public String officerId;
    }
}
