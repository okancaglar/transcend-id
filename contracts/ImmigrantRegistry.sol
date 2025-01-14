// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/**
 * @title ImmigrantRegistry
 * @dev A simple contract for storing immigrant information as strings.
 */
contract ImmigrantRegistry {
    struct Immigrant {
        string id;              // e.g. UUID generated off-chain
        string publicKey;       // e.g. from generateKeyPair()[1]
        string name;            // e.g. "test 1"
        string lastName;        // e.g. "test 1l"
        string ethnicity;       // e.g. "syrian"
        string creationTime;    // e.g. "10-12-2024 15:30"
        string officerId;       // e.g. "1"
    }

    // Mapping from immigrant's unique ID (string) to Immigrant struct
    mapping(string => Immigrant) private immigrants;

    // Emitted whenever a new immigrant is created
    event ImmigrantCreated(
        string indexed id,
        string publicKey,
        string name,
        string lastName,
        string ethnicity,
        string creationTime,
        string officerId
    );

    /**
     * @notice Creates a new immigrant record on the blockchain.
     * @param _id The immigrant's unique ID (for example, a UUID).
     * @param _publicKey The immigrant's public key (as a string).
     * @param _name The immigrant's first name.
     * @param _lastName The immigrant's last name.
     * @param _ethnicity The immigrant's ethnicity.
     * @param _creationTime The timestamp of creation (string format).
     * @param _officerId The ID of the officer who created this record.
     */
    function createImmigrant(
        string memory _id,
        string memory _publicKey,
        string memory _name,
        string memory _lastName,
        string memory _ethnicity,
        string memory _creationTime,
        string memory _officerId
    ) public {
        // Make sure this ID doesn't already exist
        require(
            bytes(immigrants[_id].id).length == 0,
            "Immigrant with this ID already exists."
        );

        immigrants[_id] = Immigrant({
            id: _id,
            publicKey: _publicKey,
            name: _name,
            lastName: _lastName,
            ethnicity: _ethnicity,
            creationTime: _creationTime,
            officerId: _officerId
        });

        emit ImmigrantCreated(
            _id,
            _publicKey,
            _name,
            _lastName,
            _ethnicity,
            _creationTime,
            _officerId
        );
    }

    /**
     * @notice Retrieves the immigrant information by ID.
     * @param _id The immigrant's unique ID.
     * @return All immigrant fields as a tuple of strings.
     */
    function getImmigrant(
        string memory _id
    )
        public
        view
        returns (
            string memory,
            string memory,
            string memory,
            string memory,
            string memory,
            string memory,
            string memory
        )
    {
        Immigrant memory i = immigrants[_id];
        return (
            i.id,
            i.publicKey,
            i.name,
            i.lastName,
            i.ethnicity,
            i.creationTime,
            i.officerId
        );
    }
}

