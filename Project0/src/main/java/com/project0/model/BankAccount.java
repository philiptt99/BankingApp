package com.project0.model;

import java.util.ArrayList;

public class BankAccount {
	
	//BankAccount object
	
	//instance attributes
	private int accountID;
	private double accountBalance;
	private String accountType;
	private String accountStatus;
	private ArrayList<User> userList;
	
	public BankAccount() {};
	//constructor
	public BankAccount(int accountID, double accountBalance, String accountStatus, String accountType) {
		//super();
		this.accountID = accountID;
		this.accountBalance = accountBalance;
		this.accountStatus = accountStatus;
		this.accountType = accountType;
		this.userList = new ArrayList<>();
	}


	
	//getters and setters
	
	
	
	
//	public int getAccountID() {
//		return accountID;
//	}


	public String getAccountType() {
		return accountType;
	}



	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}



//	public void setAccountID(int accountID) {
//		this.accountID = accountID;
//	}


	public double getAccountBalance() {
		return accountBalance;
	}


	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}


	public String getAccountStatus() {
		return accountStatus;
	}


	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}


	public ArrayList<User> getUserList() {
		return userList;
	}


	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}
	@Override
	public String toString() {
		return "Bank Account: accountID= " + accountID + ", accountBalance= $" + accountBalance + ", accountType= "
				+ accountType + ", accountStatus= " + accountStatus;
	}
	
	
	
	
	
	

}
