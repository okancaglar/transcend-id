// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/**
 * @title SimpleTransaction
 * @dev Stores a single string and lets you update/read it
 */
contract SimpleTransaction {
    // Public variable automatically gets a getter: "storedData()"
    string public storedData;

    // Event fired whenever 'storedData' is updated
    event DataSaved(address indexed sender, string data);

    /**
     * @notice Sets a new string value and emits an event
     * @param _data The string to store
     */
    function setData(string calldata _data) external {
        storedData = _data;
        emit DataSaved(msg.sender, _data);
    }

    /**
     * @notice Returns the currently stored string
     * @return The string stored in 'storedData'
     */
    function getData() external view returns (string memory) {
        return storedData;
    }
}

