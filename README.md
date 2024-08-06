# Laxmi Chit Fund

## Overview

**Laxmi Chit Fund** is a banking application that allows users to create accounts and perform various banking operations. The application supports functionalities such as creating new users, depositing money, withdrawing funds, and transferring money between accounts. The application is built using Java Servlets and JDBC, providing a simple and effective way to manage banking operations.

## Features

- **User Registration**: Create new user accounts.
- **Deposit Funds**: Deposit money into an account.
- **Withdraw Funds**: Withdraw money from an account.
- **Transfer Funds**: Transfer money between accounts.
- **Transaction History**: View transaction history for an account.

## Technologies Used
- **Java**: Programming language used for the backend logic.
- **Servlets**: Handle HTTP requests and responses.
- **JDBC**: Java Database Connectivity for interacting with the database.
- **MySQL**: Database for storing user and transaction data.

## Installation

### Prerequisites
- JDK 21 
- Apache Tomcat 
- MySQL or compatible database server

### Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/laxmi-chit-fund.git
   cd laxmi-chit-fund

2. **Database Setup**
- Create a new database in MySQL.
- Configure the database connection  in connection/DataBaseConnection.java.

3. **Deploy the Application**
- Ensure that the servlet container is running.
  
4. **Access the Application**
- Open your web browser and navigate to http://localhost:8080/LaxmiChitFund/
