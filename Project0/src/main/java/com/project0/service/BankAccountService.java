package com.project0.service;

import com.project0.dao.BankDao;

public class BankAccountService {
	private BankDao bankDao;
	private int accountID;

	public BankAccountService(int accountID) {
		this.bankDao = new BankDao(accountID);
		this.accountID = accountID;
	}
	
	public boolean isValid(int accountID) {
		return bankDao.isValid(accountID);
	}
	
	public String accountStatus(int accountID) {
		return bankDao.accountStatus(accountID);
	}
	
	public String accountType(int accountID) {
		return bankDao.accountType(accountID);
	}
	public double currentBalance(int accountID) {
		return bankDao.currentBalance(accountID);
	}
	
	public void withdrawal(int accountID, double withdrawal) {
		bankDao.withdraw(accountID, withdrawal);
	}
	
	public void deposit(int accountID, double deposit) {
		bankDao.deposit(accountID, deposit);
	}
	
	public void editAccountStatus(int accountID, String newStatus) {
		bankDao.editAccountStatus(accountID, newStatus);
	}
	
	public void deleteAccount(int accountID) {
		bankDao.deleteAccount(accountID);
	}
}
