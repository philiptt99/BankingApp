package com.project0.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

import com.project0.console.CustomerScreen;
import com.project0.console.EmployeeScreen;
import com.project0.console.Screen;
import com.project0.dao.BankDbConnection;
import com.project0.model.BankAccount;
import com.project0.model.User;

public class EmployeeLogIn implements LogInUser {
	private BankDbConnection bankCon = new BankDbConnection();

	@Override
	public void logIn() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println("Employee, welcome to Bank of ABC Log In page");
		Scanner inputEmp = new Scanner(System.in);

		System.out.println("Please enter a username:");
		String userName = inputEmp.next();
		System.out.println("Please enter a password:");
		String password = inputEmp.next();

		// SQL Select!!!

		try (Connection con1 = bankCon.getDbConnection()) {
			// sql statement
			String sql = "select 1 from bankUser where userName =? and passKey=?";
			PreparedStatement cs = con1.prepareStatement(sql);
			cs.setString(1, userName);
			cs.setString(2, password);
			ResultSet rs = cs.executeQuery();

			if (!rs.next()) {
				System.out.println("Log in error!");
				System.out.println("Please press any key to go back to previous menu to log in again:");
				if (inputEmp.next() != null) {
					Screen empScreen = new EmployeeScreen();
					empScreen.render();
				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
		System.out.println("You are logged in!");

		//
		System.out.println("Please enter a userName you want to provide services to: ");
		String customerUserName = inputEmp.next();
		CustomerService cus = new CustomerService(customerUserName);

		// validate the customerUserName
		if (cus.isValid(customerUserName)) {
			System.out.println(
					"The userName you entered was incorrect, please sign in again to start over. Press any key to go back to previous menu");
			Screen empScreen = new EmployeeScreen();
			empScreen.render();
		} else {// customerUserName is correct

			int customerID = cus.getUserID(customerUserName);
			System.out.println("Hello " + userName + ", please choose from the options below:");
			System.out.println("1. View customer personal information\n" + "2. View customer's account informationl\n"
					+ "3. Approve or deny an account\n"

					+ "4. Back to previous menu\n");

			// switch statement
			while (true) {
				char option = inputEmp.next().charAt(0); // take the input of user
				switch (option) {

				case '1':
					// user info

					System.out.println("View customer personal information");
					// print from bankUser table
					User customerInfo = new User();
					customerInfo = cus.getAll(customerUserName);
					System.out.println(customerInfo);
					System.out.println(
							"Thank you, please select the options again or press 4 to go back to previous menu:");

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
							"Thank you, please select the options again or press 4 to go back to previous menu:");

					break;
				case '3':

					// approve or deny an account

					System.out.println("Approve or deny an account");
					System.out.println(customerUserName + " has the following account(s):");
					List<BankAccount> accList2 = new ArrayList<>();
					accList2 = cus.getAllAccount(customerID);
					for (BankAccount b : accList2) {
						System.out.println(b);
					}

					System.out.println("Which account do you want to perform this service?");
					int accountStatusID = inputEmp.nextInt();
					System.out.println("Please enter your decision with approved or denied keyword");
					String accountStatusDec = inputEmp.next();

					// create a BankAccountService for this accountID
					BankAccountService accStatus = new BankAccountService(accountStatusID);
					accStatus.editAccountStatus(accountStatusID, accountStatusDec);

					System.out.println(accountStatusID + " now has a(n) " + accountStatusDec + " status");
					System.out.println(
							"Thank you, please select the options again or press 4 to go back to previous menu:");

					break;

				case '4':
					// transfer

					// back to previous menu
					// if (inputCus.next()!= null) {
					Screen empScreen = new EmployeeScreen();
					empScreen.render();
					break;
				// }

				default:
					System.out.println("This is not a valid option. Please press from 1 to 4 to continue.");
					break;

				}
			}

		}

	}

}
