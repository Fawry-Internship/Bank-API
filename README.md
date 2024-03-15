# Overview 
This project is a simple banking system API that allows users to authenticate, register, deposit money into their accounts, withdraw money from their accounts, and view their account balance and transaction history. It is built using Java Spring Boot and MySQL database.
# Test the API here:
- [API DOC](http://localhost:8081/v3/api-docs)
- [Swagger UI](http://localhost:8081/swagger-ui/index.html)

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://god.gw.postman.com/run-collection/28660393-0b54f843-6c14-4419-a7a7-d74754d1da5c?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D28660393-0b54f843-6c14-4419-a7a7-d74754d1da5c%26entityType%3Dcollection%26workspaceId%3Dcf048289-7983-422a-953e-c3f1ddb75b7a)

# Requirements
- Java Development Kit (JDK) 17 or above
- MySQL Database (You can either use a local MySQL instance or connect to a remote one)

## How to Run
1- Clone the project repository from Git (if it's not already cloned).

2- Import the project into your favorite Java IDE (e.g., IntelliJ, Eclipse, etc.).

3- Build the project to resolve dependencies.

## Features


| No. | Feature  | Description                            | Endpoint                               |
|----:|----------|----------------------------------------|----------------------------------------|
|  1. | Authenticate | Authenticate user credentials         | `POST /bank/auth/authenticate`        |
|  2. | Register     | Register a new user                   | `POST /bank/auth/register`            |
|  3. | Deposit  | Deposit money into an account          | `POST /bank/transaction/deposit`      |
|  4. | Withdraw | Withdraw money from an account         | `POST /bank/transaction/withdraw`     |
|  5. | View Account Balance     | View balance of an account           | `GET /bank/account/balance/{accountId}` |
|  6. | View Account Transactions| View transactions of an account      | `GET /bank/account/transactions/{accountId}` |
