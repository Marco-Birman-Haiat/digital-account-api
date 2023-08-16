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


<br>
<details><summary>Versão Portugês</summary>

# API REST para Conta Digital

Bem-vindo à documentação para a API REST de Conta Digital! Esta API permite aos usuários gerenciar contas digitais, realizar transferências e acessar o histórico de transações. A API é construída utilizando Java e o Spring Framework, com foco em design orientado a objetos, arquitetura em camadas e princípios SOLID.

## Tabela de Conteúdos

- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Primeiros Passos](#primeiros-passos)
  - [Pré-requisitos](#pré-requisitos)
  - [Configurando o Ambiente](#configurando-o-ambiente)
  - [Executando a Aplicação](#executando-a-aplicação)
- [Endpoints da API](#endpoints-da-api)
  - [Criar Conta](#criar-conta)
  - [Fazer Transferência](#fazer-transferência)
  - [Histórico de Transações](#histórico-de-transações)
- [Banco de Dados](#banco-de-dados)
- [Estrutura do Projeto](#estrutura-do-projeto)

## Tecnologias Utilizadas

- Java
- Spring Framework
  - Spring Boot
  - Spring Web
  - Spring Data JPA
- Hibernate
- MySQL (executando em um container Docker)

## Primeiros Passos

### Pré-requisitos

Antes de começar, certifique-se de ter os seguintes componentes instalados:

- Java (versão 11 ou superior);
- Docker e docker-compose;
- Maven;

### Configurando o Ambiente

1. Clone este repositório para a sua máquina local.
2. Inicie o container Docker executando o script de composição do Docker
```bash
   docker-compose up -d
   ```

3. Acesse o container criado e então execute o script SQL para gerar o banco de dados. Isso pode ser feito acessando o container ou por meio de qualquer interface gráfica MySQL.
```sql
  CREATE DATABASE IF NOT EXISTS digitalAccountDb;
```

4. O banco de dados estará em execução com as seguintes configurações:
  ```yaml
  spring.datasource.url=jdbc:mysql://localhost:3306/digitalAccountDb  
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver  
  spring.datasource.username=root  
  spring.datasource.password=root
  ```

### Executando a Aplicação

Para iniciar o servidor da API, execute o seguinte comando no diretório do projeto:

```bash
mvn spring-boot:run
```


A API estará acessível em http://localhost:8080 para validação. No entanto, todas as operações devem ser realizadas em uma ferramenta de requisições API (como Postman ou Insomnia) utilizando esta URL base.

## Endpoints da API

### Criar uma conta

**Endpoint:** `POST {url-base}/accounts`  
**descrição:** Criar uma nova conta.  
**Corpo da requisição:**
```json
// criar conta
{
	"name": "nome_do_beneficiário",
	"document": "999.999.999-99",
	"available-value": 1000
}
```

**Corpo do retorno:**

```json
{
	"id": "XXXXX",
	"name": "nome_do_beneficiário", 
	"document": "documento_do_beneficiário",
	"available-value": {valor_inicial_da_conta}
}
```

### Fazer transferência

**Endpoint:** `POST {url-base}/transfers`  
**Descrição:** realizar uma transferência entre duas contas.  
**Corpo da requisição:**

```json
{ "sender-document": "documento_do_emissor", "receiver-document": "documento_do_receptor", "value": {valor_da_transacao_numerico} }
```

**Corpo do retorno:** confirmação de transferência.

```json
{
    "id": "XXXXX",
    "available-value": {valor_disponível_do_emissor_após_transação},
    "sender-document": "documento_do_emissor",  
    "receiver-document": "documento_do_receptor",
    "datetime": "data_iso"
}
```


### Histórico de Transações

**Endpoint:** `GET {url-base}/accounts/{accountId}/transaction-history`  
**descrição:** Buscar histórico de transações de uma conta específica.  
**Parâmetro de caminho:** `accountId` (id da conta).  
**Retorno:** Lista das trasações do cliente;

## Base de dados

A aplicação utiliza um banco de dados MySQL chamado `digitalAccountDb` para armazenar informações de contas e transações. As tabelas utilizadas são `accounts` e `transfers`.

## Estrutura do projeto

O projeto segue uma arquitetura em camadas:

- **Controllers:** Lidam com requisições HTTP, validam entradas e gerenciam respostas.
- **Services:** Implementam a lógica de negócios e realizam operações no repositório.
- **Repositories:** Fornecem acesso ao banco de dados por meio do Spring Data JPA.

</details>