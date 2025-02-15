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
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
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
public class ImmigrantLocationLog extends Contract {
    public static final String BINARY = "6080604052348015600e575f5ffd5b50610c598061001c5f395ff3fe608060405234801561000f575f5ffd5b5060043610610034575f3560e01c806383ed34d914610038578063c6a9e37b14610068575b5f5ffd5b610052600480360381019061004d91906105a1565b610084565b60405161005f9190610778565b60405180910390f35b610082600480360381019061007d9190610798565b61033c565b005b60605f8260405161009591906108a6565b9081526020016040518091039020805480602002602001604051908101604052809291908181526020015f905b82821015610331578382905f5260205f2090600402016040518060800160405290815f820180546100f2906108e9565b80601f016020809104026020016040519081016040528092919081815260200182805461011e906108e9565b80156101695780601f1061014057610100808354040283529160200191610169565b820191905f5260205f20905b81548152906001019060200180831161014c57829003601f168201915b50505050508152602001600182018054610182906108e9565b80601f01602080910402602001604051908101604052809291908181526020018280546101ae906108e9565b80156101f95780601f106101d0576101008083540402835291602001916101f9565b820191905f5260205f20905b8154815290600101906020018083116101dc57829003601f168201915b50505050508152602001600282018054610212906108e9565b80601f016020809104026020016040519081016040528092919081815260200182805461023e906108e9565b80156102895780601f1061026057610100808354040283529160200191610289565b820191905f5260205f20905b81548152906001019060200180831161026c57829003601f168201915b505050505081526020016003820180546102a2906108e9565b80601f01602080910402602001604051908101604052809291908181526020018280546102ce906108e9565b80156103195780601f106102f057610100808354040283529160200191610319565b820191905f5260205f20905b8154815290600101906020018083116102fc57829003601f168201915b505050505081525050815260200190600101906100c2565b505050509050919050565b5f60405180608001604052808681526020018581526020018481526020018381525090505f8560405161036f91906108a6565b908152602001604051809103902081908060018154018082558091505060019003905f5260205f2090600402015f909190919091505f820151815f0190816103b79190610ac2565b5060208201518160010190816103cd9190610ac2565b5060408201518160020190816103e39190610ac2565b5060608201518160030190816103f99190610ac2565b5050508460405161040a91906108a6565b60405180910390207ffe28aff1dcdd502760609e6ba4c93fdd2806e9743a771e9dd313f0fdc085862485858560405161044593929190610bd9565b60405180910390a25050505050565b5f604051905090565b5f5ffd5b5f5ffd5b5f5ffd5b5f5ffd5b5f601f19601f8301169050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b6104b38261046d565b810181811067ffffffffffffffff821117156104d2576104d161047d565b5b80604052505050565b5f6104e4610454565b90506104f082826104aa565b919050565b5f67ffffffffffffffff82111561050f5761050e61047d565b5b6105188261046d565b9050602081019050919050565b828183375f83830152505050565b5f610545610540846104f5565b6104db565b90508281526020810184848401111561056157610560610469565b5b61056c848285610525565b509392505050565b5f82601f83011261058857610587610465565b5b8135610598848260208601610533565b91505092915050565b5f602082840312156105b6576105b561045d565b5b5f82013567ffffffffffffffff8111156105d3576105d2610461565b5b6105df84828501610574565b91505092915050565b5f81519050919050565b5f82825260208201905092915050565b5f819050602082019050919050565b5f81519050919050565b5f82825260208201905092915050565b8281835e5f83830152505050565b5f61064382610611565b61064d818561061b565b935061065d81856020860161062b565b6106668161046d565b840191505092915050565b5f608083015f8301518482035f86015261068b8282610639565b915050602083015184820360208601526106a58282610639565b915050604083015184820360408601526106bf8282610639565b915050606083015184820360608601526106d98282610639565b9150508091505092915050565b5f6106f18383610671565b905092915050565b5f602082019050919050565b5f61070f826105e8565b61071981856105f2565b93508360208202850161072b85610602565b805f5b85811015610766578484038952815161074785826106e6565b9450610752836106f9565b925060208a0199505060018101905061072e565b50829750879550505050505092915050565b5f6020820190508181035f8301526107908184610705565b905092915050565b5f5f5f5f608085870312156107b0576107af61045d565b5b5f85013567ffffffffffffffff8111156107cd576107cc610461565b5b6107d987828801610574565b945050602085013567ffffffffffffffff8111156107fa576107f9610461565b5b61080687828801610574565b935050604085013567ffffffffffffffff81111561082757610826610461565b5b61083387828801610574565b925050606085013567ffffffffffffffff81111561085457610853610461565b5b61086087828801610574565b91505092959194509250565b5f81905092915050565b5f61088082610611565b61088a818561086c565b935061089a81856020860161062b565b80840191505092915050565b5f6108b18284610876565b915081905092915050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f600282049050600182168061090057607f821691505b602082108103610913576109126108bc565b5b50919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f600883026109757fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8261093a565b61097f868361093a565b95508019841693508086168417925050509392505050565b5f819050919050565b5f819050919050565b5f6109c36109be6109b984610997565b6109a0565b610997565b9050919050565b5f819050919050565b6109dc836109a9565b6109f06109e8826109ca565b848454610946565b825550505050565b5f5f905090565b610a076109f8565b610a128184846109d3565b505050565b5b81811015610a3557610a2a5f826109ff565b600181019050610a18565b5050565b601f821115610a7a57610a4b81610919565b610a548461092b565b81016020851015610a63578190505b610a77610a6f8561092b565b830182610a17565b50505b505050565b5f82821c905092915050565b5f610a9a5f1984600802610a7f565b1980831691505092915050565b5f610ab28383610a8b565b9150826002028217905092915050565b610acb82610611565b67ffffffffffffffff811115610ae457610ae361047d565b5b610aee82546108e9565b610af9828285610a39565b5f60209050601f831160018114610b2a575f8415610b18578287015190505b610b228582610aa7565b865550610b89565b601f198416610b3886610919565b5f5b82811015610b5f57848901518255600182019150602085019450602081019050610b3a565b86831015610b7c5784890151610b78601f891682610a8b565b8355505b6001600288020188555050505b505050505050565b5f82825260208201905092915050565b5f610bab82610611565b610bb58185610b91565b9350610bc581856020860161062b565b610bce8161046d565b840191505092915050565b5f6060820190508181035f830152610bf18186610ba1565b90508181036020830152610c058185610ba1565b90508181036040830152610c198184610ba1565b905094935050505056fea26469706673582212203818de6d948819aef6380ed2e75b7e225bd0bd74b75221b8c630ea2be2cd516964736f6c634300081c0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_GETLOCATIONLOGS = "getLocationLogs";

    public static final String FUNC_LOGLOCATION = "logLocation";

    public static final Event LOCATIONLOGGED_EVENT = new Event("LocationLogged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected ImmigrantLocationLog(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ImmigrantLocationLog(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ImmigrantLocationLog(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ImmigrantLocationLog(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<LocationLoggedEventResponse> getLocationLoggedEvents(
            TransactionReceipt transactionReceipt) {
        ArrayList<LocationLoggedEventResponse> responses = new ArrayList<LocationLoggedEventResponse>();
        for (Log log : transactionReceipt.getLogs()) {
            Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(LOCATIONLOGGED_EVENT, log);

            LocationLoggedEventResponse typedResponse = new LocationLoggedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.immigrantId = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.location = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.timestamp = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.officerId = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static LocationLoggedEventResponse getLocationLoggedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(LOCATIONLOGGED_EVENT, log);
        LocationLoggedEventResponse typedResponse = new LocationLoggedEventResponse();
        typedResponse.log = log;
        typedResponse.immigrantId = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.location = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.timestamp = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.officerId = (String) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<LocationLoggedEventResponse> locationLoggedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getLocationLoggedEventFromLog(log));
    }

    public Flowable<LocationLoggedEventResponse> locationLoggedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOCATIONLOGGED_EVENT));
        return locationLoggedEventFlowable(filter);
    }

    public RemoteFunctionCall<List> getLocationLogs(String _immigrantId) {
        final Function function = new Function(FUNC_GETLOCATIONLOGS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_immigrantId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<LocationLog>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> logLocation(String _immigrantId, String _location,
            String _timestamp, String _officerId) {
        final Function function = new Function(
                FUNC_LOGLOCATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_immigrantId), 
                new org.web3j.abi.datatypes.Utf8String(_location), 
                new org.web3j.abi.datatypes.Utf8String(_timestamp), 
                new org.web3j.abi.datatypes.Utf8String(_officerId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ImmigrantLocationLog load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ImmigrantLocationLog(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ImmigrantLocationLog load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ImmigrantLocationLog(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ImmigrantLocationLog load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ImmigrantLocationLog(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ImmigrantLocationLog load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ImmigrantLocationLog(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ImmigrantLocationLog> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ImmigrantLocationLog.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ImmigrantLocationLog> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ImmigrantLocationLog.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<ImmigrantLocationLog> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ImmigrantLocationLog.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<ImmigrantLocationLog> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ImmigrantLocationLog.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class LocationLog extends DynamicStruct {
        public String immigrantId;

        public String location;

        public String timestamp;

        public String officerId;

        public LocationLog(String immigrantId, String location, String timestamp,
                String officerId) {
            super(new org.web3j.abi.datatypes.Utf8String(immigrantId), 
                    new org.web3j.abi.datatypes.Utf8String(location), 
                    new org.web3j.abi.datatypes.Utf8String(timestamp), 
                    new org.web3j.abi.datatypes.Utf8String(officerId));
            this.immigrantId = immigrantId;
            this.location = location;
            this.timestamp = timestamp;
            this.officerId = officerId;
        }

        public LocationLog(Utf8String immigrantId, Utf8String location, Utf8String timestamp,
                Utf8String officerId) {
            super(immigrantId, location, timestamp, officerId);
            this.immigrantId = immigrantId.getValue();
            this.location = location.getValue();
            this.timestamp = timestamp.getValue();
            this.officerId = officerId.getValue();
        }
    }

    public static class LocationLoggedEventResponse extends BaseEventResponse {
        public byte[] immigrantId;

        public String location;

        public String timestamp;

        public String officerId;
    }
}
