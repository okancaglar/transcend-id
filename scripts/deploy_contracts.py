#!/usr/bin/env python3

import os
from web3 import Web3
from solcx import compile_standard, install_solc
import json

# Install the Solidity compiler
install_solc("0.8.0")

# Ganache URL
ganache_url = "http://127.0.0.1:8545"  # Adjust port if Ganache GUI uses a different one
w3 = Web3(Web3.HTTPProvider(ganache_url))

# Check connection to Ganache
if not w3.is_connected():
    print("Failed to connect to Ganache. Make sure it is running.")
    exit(1)

# Use the first account from Ganache
w3.eth.default_account = w3.eth.accounts[0]

# Load and compile contracts
def compile_contract(file_path):
    with open(file_path, "r") as file:
        source_code = file.read()
    
    compiled_sol = compile_standard(
        {
            "language": "Solidity",
            "sources": {file_path: {"content": source_code}},
            "settings": {
                "outputSelection": {
                    "*": {
                        "*": ["abi", "metadata", "evm.bytecode", "evm.sourceMap"]
                    }
                }
            },
        },
        solc_version="0.8.0",
    )
    contract_name = list(compiled_sol["contracts"][file_path].keys())[0]
    abi = compiled_sol["contracts"][file_path][contract_name]["abi"]
    bytecode = compiled_sol["contracts"][file_path][contract_name]["evm"]["bytecode"]["object"]
    return abi, bytecode

# Compile both contracts
registry_abi, registry_bytecode = compile_contract("/home/marcus-aurelius/Projects/SchoolProjects/DecentrelizedSystemsAndApplications/TranscendID/transcend-id/contracts/ImmigrantRegistry.sol")
location_abi, location_bytecode = compile_contract("/home/marcus-aurelius/Projects/SchoolProjects/DecentrelizedSystemsAndApplications/TranscendID/transcend-id/contracts/ImmigrantLocationLog.sol")

# Deploy a contract
def deploy_contract(abi, bytecode):
    Contract = w3.eth.contract(abi=abi, bytecode=bytecode)
    tx_hash = Contract.constructor().transact()
    tx_receipt = w3.eth.wait_for_transaction_receipt(tx_hash)
    return tx_receipt.contractAddress

# Deploy ImmigrantRegistry contract
registry_address = deploy_contract(registry_abi, registry_bytecode)
print(f"ImmigrantRegistry deployed at: {registry_address}")

# Deploy ImmigrantLocationContract
location_address = deploy_contract(location_abi, location_bytecode)
print(f"ImmigrantLocationContract deployed at: {location_address}")



# Save deployed contract details to a JSON file

deployed_info = {
    "ImmigrantRegistry": {
        "address": registry_address,
        "abi": registry_abi
    },
    "ImmigrantLocationContract": {
        "address": location_address,
        "abi": location_abi
    }
}

with open("deployed_contracts.json", "w") as f:
    json.dump(deployed_info, f, indent=4)

print("Deployment info saved to deployed_contracts.json")

