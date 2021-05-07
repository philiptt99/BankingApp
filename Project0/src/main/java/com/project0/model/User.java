package com.project0.model;

import java.util.ArrayList;

public class User {
	//User Object
	
	//instance attributes
//	private int userID;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	//private String dob;
	private int userRole;
	private boolean isLoggedIn;
	private ArrayList<BankAccount> bankAccount;
	
	
	public User() {};
	
	
	public User( String userName, String password, String firstName, String lastName, int userRole) {
		super();
		//this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userRole = userRole;
		this.bankAccount = new ArrayList<>();
	}
	//getters and setters
//	public int getUserID() {
//		return userID;
//	}
//	public void setUserID(int userID) {
//		this.userID = userID;
//	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	
	@Override
	public String toString() {
		return "userName= " + userName + ", firstName= " + firstName + ", lastName= "
				+ lastName + ", userRole= "+userRole;
	}
	
	//get the bankAccount
//	public bankAccount ArrayList<BankAccount> getBankAccount(int accountNum) {
//		BankAccount newAcc = new BankAccount();
	
//		for (BankAccount b: bankAccount){
//			if (b.getAccountNum == accountNum){ newAcc = b;};
//		return newAcc;
//	}
	
	
	
	

}
