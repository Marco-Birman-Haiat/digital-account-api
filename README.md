<details><summary>English Version</summary>

# Digital Account REST API

Welcome to the documentation for the Digital Account REST API! This API allows users to manage digital accounts, perform transfers, and access transaction history. The API is built using Java and the Spring Framework, with a focus on object-oriented design, layered architecture, and SOLID principles.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Setting Up the Environment](#setting-up-the-environment)
  - [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
  - [Create Account](#create-account)
  - [Transfer Funds](#transfer-funds)
  - [Transaction History](#transaction-history)
- [Database](#database)
- [Project Structure](#project-structure)


## Technologies Used

- Java
- Spring Framework
  - Spring Boot
  - Spring Web
  - Spring Data JPA
- Hibernate
- MySQL (running in a Docker container)

## Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- Java (version 11 or higher);
- Docker and docker-compose;
- Maven;

### Setting Up the Environment

1. Clone this repository to your local machine.
2. Launch the docker container by running the docker compose script
   ```bash
   docker-compose up -d
   ```

3. Access the created container and  then run the SQL script to generate the database. It can be done by entering the container or through any MySql GUI
   ```sql
   CREATE DATABASE IF NOT EXISTS digitalAccountDb;
   ```

4. The database will be running with the following settings:
```yaml
spring.datasource.url=jdbc:mysql://localhost:3306/digitalAccountDb  
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver  
spring.datasource.username=root  
spring.datasource.password=root
```

### Running the Application

To launch the API server, run the following command in the project directory:

```bash
mvn spring-boot:run
```


The API will be accessible at `http://localhost:8080` for validation. But all the operations must be done in a api request tool (like Postman or Insomnia) using this base url;

## API Endpoints

### Create Account

**Endpoint:** `POST {base-url}/accounts`  
**Description:** Create a new digital account.  
**Request Body:**
```json
// Create account
{
	"name": "beneficiary_name",
	"document": "999.999.999-99",
	"available-value": 1000
}
```

**Response:**

```json
{
	"id": "XXXXX",
	"name": "beneficiary_name", 
	"document": "beneficiary_document",
	"available-value": account_initial_value
}
```

### Transfer Funds

**Endpoint:** `POST {base-url}/transfers`  
**Description:** Execute a fund transfer between two accounts.  
**Request Body:** Transfer details (sender, recipient, amount, etc.)  

```json
{ "sender-document": "documento_do_emissor", "receiver-document": "documento_do_receptor", "value": valor_da_transacao_numerico }
```

**Response:** Transfer confirmation.

```json
{
    "id": "XXXXX",
    "available-value": sender_available_value_after_transaction,
    "receiver-document": "receiver_document",
    "sender-document": "sender_document",
    "datetime": "date_iso"
}
```


### Transaction History

**Endpoint:** `GET {base-url}/accounts/{accountId}/transaction-history`  
**Description:** Retrieve transaction history for a specific account.  
**Path Parameter:** `accountId` (Account ID).  
**Response:** List of transactions. Done and received;

## Database

The application uses a MySQL database named `digitalAccountDb` to store account and transaction information. The tables used are `accounts` and `transfers`.

## Project Structure

The project follows a layered architecture:

- **Controllers:** Handle HTTP requests, validate input, and manage responses.
- **Services:** Implement business logic and perform operations on the repository.
- **Repositories:** Provide database access through Spring Data JPA


</details>