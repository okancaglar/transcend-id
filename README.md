# transcend-id
Blockchain based immigrant identity and tracking system


System Analysis and Design

System:
The system is a private (closed) blockchain network that can only be accessed by authorized government officers.

Users:

    Officer: Each officer has a user ID and password, stored in a MySQL database.

System Functionality:

    Login
    Government officers can log in with their unique ID and password.

    Create Immigrant Identity
        First, the system generates a public-private key pair for the immigrant. The private key is not stored anywhere in the system.
        The public key is stored in the “identity contract” on the blockchain network for verification.
        The identity is then recorded on the blockchain (making it immutable). The identity contract contains the following fields:
            A unique ID (determined by a chosen algorithm).
            The immigrant’s public key.
            Name, surname, and ethnicity.
            The creation timestamp.
            The government officer who created this transaction.
        The system returns the private key and the immigrant’s ID to the user interface so the officer can provide them to the immigrant.
        This completes the immigrant creation process.

    Validate Immigrant Identity
        To validate an immigrant’s identity, the officer retrieves the immigrant’s ID and private key.
        The private key is used in the frontend to sign a message.
        The signed message and the ID are then sent to the backend service.
        The backend fetches the immigrant’s public key from the blockchain network and verifies the signature.
        The verification result is sent back to the frontend.

    Create Location Log
        The officer can record the immigrant’s location for a specific time.
        The officer provides the immigrant’s ID and the location to the backend service.
        The backend creates an immutable transaction on the blockchain network containing the immigrant’s ID, the location, and a timestamp.
