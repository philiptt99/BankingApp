# BankingApp

## Project Description
A simple banking application that simulates simple banking transactions 

## Technologies Used

* Java SE 1.8
* JDBC
* JUnit
* Log4J
* Maven
* PostgreSQL

## Features

List of features ready and TODOs for future development:

* The bank should allow 3 types of user accounts to be created: customer, employee, and admin. 
* All interaction with the user should be done through the console using the Scanner class 
* All information should be persisted using JDBC and an PostgreSQL DB. Your database should include/utilize at least 1 stored Function. 

CUSTOMER ACCOUNTS:

* Customers of the bank should be able to register a new USER account with a username and password. 
* Customers should then be able to apply for a BANK account(s). The account should be in a “pending” state, awaiting an employee or admin to approve the opening of the new bank account. 
* Customers should ALSO be able to apply for joint bank accounts. 
* Once the account is open (approved by an employee or admin), customers should be able to withdraw, deposit, and transfer funds between accounts (customers may have multiple accounts, to be clear). 
* All basic validation should be done, such as trying to input negative amounts, overdrawing from accounts etc. 

EMPLOYEE ACCOUNTS:

* Employees of the bank should be able to view all of the customers’ information 
* Employees should be able to approve/deny open applications for accounts 

ADMIN ACCOUNTS:

* Bank admins should be able to view and edit all accounts 
* Approving/denying accounts 
* Withdrawing, depositing, transferring from all accounts 
* Canceling accounts 


## Getting Started
   
git clone https://github.com/philiptt99/BankingApp.git

## Usage
Run the ConsoleMain.java inside the com.project0.console package to start the application
