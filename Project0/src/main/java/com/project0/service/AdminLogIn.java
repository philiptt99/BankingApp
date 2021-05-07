package com.project0.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.project0.console.AdminScreen;
import com.project0.console.CustomerScreen;
import com.project0.console.EmployeeScreen;
import com.project0.console.Screen;
import com.project0.dao.BankDbConnection;
import com.project0.model.BankAccount;
import com.project0.model.User;

public class AdminLogIn implements LogInUser{
	private BankDbConnection bankCon = new BankDbConnection();

	@Override
	public void logIn() {
		// TODO Auto-generated method stub
		System.out.println("Admin, welcome to Bank of ABC Log In page");
		Scanner inputAdm = new Scanner (System.in);
		
		System.out.println("Please enter a username:");
		String userName = inputAdm.next();
		System.out.println("Please enter a password:");
		String password = inputAdm.next();
		
		//SQL Select!!!
		try(Connection con1 = bankCon.getDbConnection()){
			//sql statement
			String sql = "select 1 from bankUser where userName =? and passKey=?";
			PreparedStatement cs = con1.prepareStatement(sql);
			cs.setString(1, userName);
			cs.setString(2, password);
			ResultSet rs = cs.executeQuery();
			
			if (!rs.next()) {
				System.out.println("Log in error!");
				System.out.println("Please press any key to go back to previous menu to log in again:");
				if (inputAdm.next()!= null) {
				Screen adminScreen = new AdminScreen();
				adminScreen.render();
				}
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	
		}
		System.out.println("You are logged in!");
		
		//if matches ..... go to CustomerService
		//else
		
		System.out.println("Please enter a userName you want to provide services to: ");
		String customerUserName = inputAdm.next();
		CustomerService cus = new CustomerService(customerUserName);

		// validate the customerUserName
		if (cus.isValid(customerUserName)) {
			System.out.println(
					"The userName you entered was incorrect, please sign in again to start over. Press any key to go back to previous menu");
			Screen admScreen = new AdminScreen();
			admScreen.render();
		} else {// customerUserName is correct
			int customerID = cus.getUserID(customerUserName);
			
			System.out.println("Hello " + userName + ", please choose from the options below:");
			System.out.println("1. View customer personal information\n" 
					+ "2. View customer's account information\n"
					+ "3. Perform services on a bank account: withdrawal, deposit or transfer"
					+ "4. Approve or deny an account\n"
					+ "5. Cancel an account\n"
					+ "6. Back to previous menu\\n");

			// switch statement
			outerSwitch:while (true) {
				char option = inputAdm.next().charAt(0); // take the input of user
				switch (option) {

				case '1':
					// customer personal information

					System.out.println("View customer personal information");
					// print from bankUser table
					User customerInfo = new User();
					customerInfo = cus.getAll(customerUserName);
					System.out.println(customerInfo);
					System.out.println(
							"Thank you, please select the options again or press 6 to go back to previous menu:");

					
					break;
				case '2':
					// account info

					System.out.println("View customer's account information");

					List<BankAccount> accList = new ArrayList<>();
					accList = cus.getAllAccount(customerID);
					for (BankAccount b : accList) {
						System.out.println(b);
					}

					System.out.println(
							"Thank you, please select the options again or press 6 to go back to previous menu:");

					break;
					
				case '3':
					//Perform any services
					System.out.println("Please choose from below, the accountID of " + customerUserName +" you want to perform services.");
					List<BankAccount> accList3 = new ArrayList<>();
					accList = cus.getAllAccount(customerID);
					for (BankAccount b : accList3) {
						System.out.println(b);
					}
					
					int accountServiceID = inputAdm.nextInt();
				
					BankAccountService accServ = new BankAccountService(accountServiceID);
					
					System.out.println("Which services do you want? Press 1 for withdrawl, 2 for deposit, or 3 for transfer");
					while(true) {
						char newOpt = inputAdm.next().charAt(0);
						switch (newOpt) {
						case '1':
							//withdrawal
							System.out.println("Please enter a withdrawl amount");
							double withdrawal = inputAdm.nextDouble();
							
							if(withdrawal>=0){
							
								accServ.withdrawal(accountServiceID, withdrawal);
								System.out.println(accountServiceID +" has a current balance of $" + accServ.currentBalance(accountServiceID));
								
							}
							else {
								System.out.println("Invalid withdrawal amount. Press 1 to re-enter the amount again:");
								break;
							}
							
							System.out.println("Back to Admin Selection Menu");

							continue outerSwitch;
							//break;
							
						case '2':
							//deposit
							System.out.println("Please enter a deposit amount");
							double deposit = inputAdm.nextDouble();
							
							if(deposit>=0) {
								accServ.deposit(accountServiceID, deposit);
							
								System.out.println(accountServiceID +" has a current balance of $" + accServ.currentBalance(accountServiceID));
							}
							else {
								System.out.println("Invalid deposit amount.");
								break;
							}
							System.out.println("Back to Admin Selection Menu");	

							//break;
							continue outerSwitch;
							
						case '3':
							//transfer
							System.out.println("Please enter an account you want to transfer money to");
							int transferringAccount = inputAdm.nextInt();
							BankAccountService transferring = new BankAccountService(transferringAccount);
							
							//validate the transferring Account ID
							if(transferring.isValid(transferringAccount)) {
								System.out.println("Your account ID you entered is incorrect. Please reselect other options again or press 3 to go back to previous menu");
								break;
							}
							else {
							
							System.out.println("Please enter an amount you want to transfer");
							double transferedAmount = inputAdm.nextDouble();
							
							if (transferedAmount >=0) {
							
							cus.transferAccount(customerID, accountServiceID, transferringAccount, transferedAmount);
							System.out.println("Account number " + accountServiceID + " of " + customerUserName +" has a balance of $"+ accServ.currentBalance(accountServiceID));
							System.out.println("Account number " + transferringAccount + " of " + customerUserName + " has a balance of $"+ transferring.currentBalance(transferringAccount));
							}
							else {
								System.out.println("You have entered an invalid transfer amount");
							}
							
							}
							System.out.println("Back to Admin Selection Menu");
							//break;
							continue outerSwitch;
						case '4':
							System.out.println("Back to Admin Selection Menu");
							//break;
							continue outerSwitch;
						default:
							System.out.println("This is not a valid option. Please press from 1 to 3 to perform any banking services or 4 to back to previous menu");
							break;
						}
					}
					
					
				case '4':


					// approve or deny an account

					System.out.println("Approve or deny an account");
					System.out.println(customerUserName + " has the following account(s):");
					List<BankAccount> accList2 = new ArrayList<>();
					accList2 = cus.getAllAccount(customerID);
					for (BankAccount b : accList2) {
						System.out.println(b);
					}

					System.out.println("Which account do you want to perform this service?");
					int accountStatusID = inputAdm.nextInt();
					System.out.println("Please enter your decision with approved or denied keyword");
					String accountStatusDec = inputAdm.next();

					// create a BankAccountService for this accountID
					BankAccountService accStatus = new BankAccountService(accountStatusID);
					accStatus.editAccountStatus(accountStatusID, accountStatusDec);

					System.out.println(accountStatusID + " now has a(n) " + accountStatusDec + " status");
					System.out.println(
							"Thank you, please select the options again or press 6 to go back to previous menu:");

					break;
					
				case '5':
					
					//Cancel an account
					System.out.println("Warning: Successful cancelling an account will permanently remove it from bank of ABC database.");
					System.out.println("Do you want to continue? y/n");
					char cancelInput = inputAdm.next().charAt(0);
					
					if (cancelInput == 'y') {
						System.out.println(customerUserName + " has the following account(s):");
						List<BankAccount> accList4 = new ArrayList<>();
						accList2 = cus.getAllAccount(customerID);
						for (BankAccount b : accList4) {
							System.out.println(b);
						}

						System.out.println("Which account do you want to perform this service?");
						int accountDeleteID = inputAdm.nextInt();
						
						BankAccountService accDel = new BankAccountService(accountDeleteID);
						accDel.accountStatus(accountDeleteID);
						System.out.println(accountDeleteID +" has been cancelled from bank of ABC Database");
						System.out.println("Please select other options or press 6 to go back to previous menu");
					}
					else {
						System.out.println("Please select other options or press 6 to go back to previous menu");
						
					}
					break;

				case '6':

					// back to previous menu
					// if (inputCus.next()!= null) {
					Screen admScreen = new AdminScreen();
					admScreen.render();
					break;
				// }

				default:
					System.out.println("This is not a valid option. Please press from 1 to 6 to continue.");
					break;

				}
	}
		
		}}}

