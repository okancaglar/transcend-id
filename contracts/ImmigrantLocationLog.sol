// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

/**
 * @title ImmigrantLocationLog
 * @dev A simple contract to store immigrant location logs, all fields as strings.
 */
contract ImmigrantLocationLog {
    struct LocationLog {
        string immigrantId;  // e.g., the same UUID used in ImmigrantRegistry
        string location;     // e.g., "City A, Checkpoint 3"
        string timestamp;    // e.g., "10-12-2024 16:00"
        string officerId;    // e.g., "2"
    }

    // Maps an immigrant's ID (string) to an array of LocationLog structs
    mapping(string => LocationLog[]) private locationLogs;

    // Emitted when a new location log is added
    event LocationLogged(
        string indexed immigrantId,
        string location,
        string timestamp,
        string officerId
    );

    /**
     * @notice Logs a location entry for an immigrant.
     * @param _immigrantId The unique ID (string) of the immigrant (matches ImmigrantRegistry ID).
     * @param _location The location details (string).
     * @param _timestamp The date/time of the log (string).
     * @param _officerId The ID (string) of the officer recording this entry.
     */
    function logLocation(
        string memory _immigrantId,
        string memory _location,
        string memory _timestamp,
        string memory _officerId
    ) public {
        LocationLog memory newLog = LocationLog({
            immigrantId: _immigrantId,
            location: _location,
            timestamp: _timestamp,
            officerId: _officerId
        });

        locationLogs[_immigrantId].push(newLog);

        emit LocationLogged(_immigrantId, _location, _timestamp, _officerId);
    }

    /**
     * @notice Retrieves all location logs for a specific immigrant ID.
     * @param _immigrantId The unique ID (string) of the immigrant.
     * @return An array of LocationLog structs, each field stored as a string.
     */
    function getLocationLogs(
        string memory _immigrantId
    ) 
        public 
        view 
        returns (LocationLog[] memory) 
    {
        return locationLogs[_immigrantId];
    }
}

