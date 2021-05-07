package com.project0.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.project0.console.CustomerScreen;
import com.project0.console.MainMenu;
import com.project0.console.Screen;
import com.project0.dao.BankDbConnection;

public class CustomerLogIn implements LogInUser {
	private BankDbConnection bankCon = new BankDbConnection();
	public final Logger log2 = Logger.getLogger(CustomerRegister.class);

	@Override
	public void logIn() {
		// TODO Auto-generated method stub
		System.out.println("Customer, welcome to Bank of ABC Log In page");
		Scanner inputCus = new Scanner (System.in);
		
		System.out.println("Please enter a username:");
		String userName = inputCus.next();
		System.out.println("Please enter a password:");
		String password = inputCus.next();
		
		//SQL Select!!!
		//Check to see if userName has already there:
				try(Connection con1 = bankCon.getDbConnection()){
					//sql statement
					String sql = "select 1 from bankUser where userName =? and passKey=?";
					PreparedStatement cs = con1.prepareStatement(sql);
					cs.setString(1, userName);
					cs.setString(2, password);
					ResultSet rs = cs.executeQuery();
					log2.info("username has been checked.");
					if (!rs.next()) {
						System.out.println("Log in error!");
						System.out.println("Please press any key to go back to previous menu to log in again:");
						if (inputCus.next()!= null) {
						Screen custScreen = new CustomerScreen();
						custScreen.render();
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			
				}
				System.out.println("You are logged in!");
				//get the userID from customer
				CustomerService cs = new CustomerService(userName);
				int userIdentity = cs.getUserID(userName);
				System.out.println("Do you already have an account with us?");
				
				if (inputCus.next().equalsIgnoreCase("y")) { //Banking Services 
					
					System.out.println("On to Banking Services! Please have your account number handy. If you forget, you can call us at 888-888-888 to receive a new one");
					System.out.println("Please enter your account number:");
					
					int accountID = inputCus.nextInt();
					BankAccountService account = new BankAccountService(accountID);
					
					if(account.isValid(accountID)) {
						System.out.println("Your account ID you entered is invalid. Please reapply it again.\nRedirecting back to Customer Menu");
						Screen custScreen = new CustomerScreen();
						custScreen.render();
					}
					
					System.out.println("Hello " + userName+ ", please choose from the options below:");
					System.out.println("1. Check account status\n" +
							 "2. Withdrawal\n"
							 + "3. Deposit\n"
							 + "4. Transfer\n"
							 + "5. Back to previous menu\n");
					
					//switch statement
					while(true) {
						char option = inputCus.next().charAt(0); //take the input of user
						switch(option) {
						
						case '1':
							//account status
							
							System.out.println("Account Status");
							
							//go to register Screen
							String status = account.accountStatus(accountID);
							System.out.println("Your account number is " + accountID + "\nYour account balance is $" + account.currentBalance(accountID) 
												+ "\nYour account type is "+ account.accountType(accountID) +"\nYour account status is "+account.accountStatus(accountID));
							
							System.out.println("Thank you, please select the options again or press 5 to go back to previous menu:");

							
							break;
						case '2':
							//withdrawal
							System.out.println("Withdrawal");
							if (!account.accountStatus(accountID).equalsIgnoreCase("approved")) {
								System.out.println("Your account status is "+ account.accountStatus(accountID));
								System.out.println("At the moment, you cannot perform any services until we approve your account. Please press any key to go back to previous menu.");
								if (inputCus.next()!= null) {
									Screen custScreen = new CustomerScreen();
									custScreen.render();
								}
								
								log2.info("Withdrawl method works.");
								break;
							}
							
							
							
							System.out.println("Please enter a withdrawl amount");
							double withdrawal = inputCus.nextDouble();
							
							if(withdrawal>=0){
							
								account.withdrawal(accountID, withdrawal);
								System.out.println("Your current balance is $" + account.currentBalance(accountID));
							}
							else {
								System.out.println("You have entered an invalid withdrawal amount.");
							}
							
							System.out.println("Thank you, please select the options again or press 5 to go back to previous menu:");

							
							break;
						case '3':
							
							//deposit
							
							System.out.println("Deposit");
							if (!account.accountStatus(accountID).equalsIgnoreCase("approved")) {
								System.out.println("Your account status is "+ account.accountStatus(accountID));
								System.out.println("At the moment, you cannot perform any services until we approve your account. Please press any key to go back to previous menu.");
								if (inputCus.next()!= null) {
									Screen custScreen = new CustomerScreen();
									custScreen.render();
								}
								break;
							}
							
							System.out.println("Please enter a deposit amount");
							double deposit = inputCus.nextDouble();
							
							if(deposit>=0) {
								account.deposit(accountID, deposit);
							
								System.out.println("Your current balance is $" + account.currentBalance(accountID));
							}
							else {
								System.out.println("You have entered an invalid deposit amount.");
							}
							System.out.println("Thank you, please select the options again or press 5 to go back to previous menu:");	
							log2.info("Deposit"
									+ " method works.");
							break;
							
						case '4':
							//transfer
							
							System.out.println("Transfer");
							
							if (!account.accountStatus(accountID).equalsIgnoreCase("approved")) {
								System.out.println("Your account status is "+ account.accountStatus(accountID));
								System.out.println("At the moment, you cannot perform any services until we approve your account. Please press any key to go back to previous menu.");
								if (inputCus.next()!= null) {
									Screen custScreen = new CustomerScreen();
									custScreen.render();
								}
								break;
							}
							System.out.println("Please enter an account you want to transfer money to");
							int transferringAccount = inputCus.nextInt();
							BankAccountService transferring = new BankAccountService(transferringAccount);
							
							//validate the transferring Account ID
							if(transferring.isValid(transferringAccount)) {
								System.out.println("Your account ID you entered is incorrect. Please reselect other options again or press 3 to go back to previous menu");
								break;
							}
							else {
							
							System.out.println("Please enter an amount you want to transfer");
							double transferedAmount = inputCus.nextDouble();
							
							if (transferedAmount >=0) {
							
							cs.transferAccount(userIdentity, accountID, transferringAccount, transferedAmount);
							System.out.println("Your account number " + accountID + " has a balance of $"+ account.currentBalance(accountID));
							System.out.println("Your account number " + transferringAccount +" has a balance of $"+ transferring.currentBalance(transferringAccount));
							}
							else {
								System.out.println("You have entered an invalid transfer amount");
							}
							
							}
							log2.info("Transfer method works.");
							System.out.println("Thank you, please select the options again or press 5 to go back to previous menu:");
							break;
							
						case '5':
							// back to previous menu
							//if (inputCus.next()!= null) {
								Screen custScreen = new CustomerScreen();
								custScreen.render();
								break;
							//	}
							
						default:
							System.out.println("This is not a valid option. Please press from 1 to 5 to continue.");
							break;
						
						}
					}
					
				}
				else {//open new account
					
					System.out.println("Hello " + userName+ ", please choose from the options below:");
					System.out.println("1. Open new account\n" +
							 "2. Apply for a joint account with existing account\n"
							 + "3. Exit, back to main menu\n"
							 );
					
					
					while(true) {
						char opt = inputCus.next().charAt(0);
						switch(opt) {
						
						case '1':  // no account at all
							System.out.println("Please create a bank account with us");
							System.out.println("Enter an account type chequing or saving:");
							
							String accountType = inputCus.next();
							
							System.out.println("Enter an account balance:");
							Double accountBalance = inputCus.nextDouble();
							cs.openBankAccount(accountBalance, accountType);
							int accountID = cs.getAccountID(accountBalance);
							int userID = cs.getUserID(userName);
							cs.insertJunction(userID, accountID);
							log2.info("New account has been added to db successfully.");
							
							
							System.out.println("Your accountID is "+accountID+". Please wait at most 24 hours for account approval email from us. Then you can start your BABC banking services");
							System.out.println("Please press any key to go back to previous menu:");
							
							//redirect customer back to Customer Screen
							if (inputCus.next()!= null) {
							Screen custScreen = new CustomerScreen();
							custScreen.render();
							}
							break;
						
						case '2': //joint account
							System.out.println("We will need the account ID that you want to join to. Please provide one, if you do not know it, contact us for more details");
							int accountJointID = inputCus.nextInt();
							BankAccountService accountJoint = new BankAccountService(accountJointID);
							
							//validate the acountNumber
							
							if(accountJoint.isValid(accountJointID)) {
								System.out.println("Your account ID you entered is incorrect. Please reselect other options again or press 3 to go back to previous menu");
								break;
							}
							else {
							
							cs.insertJunction(userIdentity, accountJointID);
							log2.info("New JOINT account has been added to db successfully.");
							
							System.out.println("You have been added to account number "+ accountJointID +"\nPlease wait at most 24 hours for the approval email");
							System.out.println("Please press 3 to go back to previous menu:");
							
							//redirect customer back to Customer Screen
							if (inputCus.next()!= null) {
							Screen custScreen = new CustomerScreen();
							custScreen.render();
							}
							}
							break;
							
						case '3':  //back to previous menu
							Screen custScreen = new CustomerScreen();
							custScreen.render();
							break;
							
						default:
							System.out.println("This is not a valid option. Please press from 1 to 3 to continue.");
							break;
							
							}
						
						}
					}
					
//					System.out.println("Please create a bank account with us");
//					System.out.println("Enter an account type chequing or saving:");
//					
//					String accountType = inputCus.next();
//					
//					System.out.println("Enter an account balance:");
//					Double accountBalance = inputCus.nextDouble();
//					cs.openBankAccount(accountBalance, accountType);
//					int accountID = cs.getAccountID(accountBalance);
//					int userID = cs.getUserID(userName);
//					cs.insertJunction(userID, accountID);
//					
//					System.out.println("Your accountID is "+accountID+". Please wait at most 24 hours for account approval email from us. Then you can start your BABC banking services");
//					System.out.println("Please press any key to go back to previous menu:");
//					
//					//redirect customer back to Customer Screen
//					if (inputCus.next()!= null) {
//					Screen custScreen = new CustomerScreen();
//					custScreen.render();
//					}
					
				
				
		

	}
	
}
