# Blockchain-Based Immigrant Identity System

## **System Overview**

This system is a private (closed) blockchain network designed to manage immigrant identities and location logs. Access is restricted to authorized government officers, ensuring a secure and controlled environment.

---

## **Users**

### **Officer**
- Each government officer has a unique user ID and password.
- Login credentials are securely stored in a MySQL database.

---

## **System Functionality**

### **1. Login**
- Government officers can log in using their unique ID and password.

---

### **2. Create Immigrant Identity**
- The system generates a **public-private key pair** for the immigrant:
  - **Private Key**: Not stored anywhere in the system.
  - **Public Key**: Stored in the “identity contract” on the blockchain for verification.
- The immigrant's identity is recorded immutably on the blockchain, including:
  - A **unique ID** (determined by a chosen algorithm).
  - The immigrant’s **public key**.
  - **Name**, **surname**, and **ethnicity**.
  - The **creation timestamp**.
  - The **government officer ID** who created the transaction.
- After recording the identity:
  - The system returns the **private key** and **unique ID** to the user interface for the officer to provide to the immigrant.
- This process completes the immigrant’s identity creation.

---

### **3. Validate Immigrant Identity**
- To verify an immigrant’s identity:
  1. The officer retrieves the immigrant’s **ID** and **private key**.
  2. The **private key** is used in the frontend to sign a message.
  3. The **signed message** and **ID** are sent to the backend.
  4. The backend:
     - Fetches the immigrant’s **public key** from the blockchain network.
     - Verifies the **signature** using the public key.
  5. The result of the verification is returned to the frontend.

---

### **4. Create Location Log**
- Officers can log an immigrant’s location at a specific time:
  1. The officer provides the **immigrant’s ID** and the **location** to the backend service.
  2. The backend creates an **immutable transaction** on the blockchain, containing:
     - Immigrant’s **ID**.
     - **Location information**.
     - **Timestamp** of the entry.

---

## **Purpose**
This system ensures:
1. **Immutable Records**: All identity and location data are securely stored on the blockchain.
2. **Accountability**: Each transaction is traceable to the officer who performed it.
3. **Data Integrity**: The system uses cryptographic keys to verify identities without storing sensitive private keys.

---

## **Technology Stack**
- **Blockchain Network**: Ganache (local blockchain).
- **Smart Contracts**: Written in Solidity.
- **Backend**: Spring Boot.
- **Database**: MySQL (for officer credentials).
- **Integration Scripts**: Python (Web3.py).

---

## **Setup and Usage**
1. Deploy the blockchain network using Ganache.
2. Compile and deploy the `ImmigrantRegistry` smart contract to the blockchain.
3. Use the Spring Boot backend for managing user interactions and API endpoints.
4. Use Python scripts to interact with the blockchain for deploying contracts and verifying data.

