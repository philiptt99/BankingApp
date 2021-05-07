package com.project0.service;

import java.util.List;

import com.project0.dao.CustomerDao;
import com.project0.model.BankAccount;
import com.project0.model.User;

public class CustomerService {
	private CustomerDao customerDao;
	private String userName;
	
	public CustomerService(String userName) {
		this.customerDao = new CustomerDao(userName);
		this.userName = userName;
	}
	
	public boolean isValid (String userName) {
		  return customerDao.isValid(userName);
	}
	public int getUserID(String userName) {
		return customerDao.getUserID(userName);
	}
	
	public int getAccountID (double balance) {
		return customerDao.getAccountID(balance);
	}
	
	public void openBankAccount(double balance, String type) {
		customerDao.openBankAccount(balance, type);
	}
	
	public List<BankAccount> getAllAccount(int userID){
		return customerDao.getAllAccount(userID);
	}
	
	public void transferAccount(int userID, int transferingAccountID, int transferedAccountID, double amount) {
		customerDao.transferAccount(userID, transferingAccountID, transferedAccountID, amount);
	}
	
	public void insertJunction(int userID, int accountID) {
		customerDao.insertJunction(userID, accountID);
	}
	
	public User getAll(String userName) {
		return customerDao.getAll(userName);
	}
}
