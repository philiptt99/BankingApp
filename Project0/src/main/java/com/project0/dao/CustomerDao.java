package com.project0.dao;

import java.sql.*;
import java.util.*;

import com.project0.model.BankAccount;
import com.project0.model.User;
import com.project0.service.BankAccountService;

public class CustomerDao {
	private BankDbConnection bankCon;
	private String userName;
	
	public CustomerDao(String userName) {
		this.bankCon = new BankDbConnection();
		this.userName = userName;
	}
	
	public int getUserID(String userName) {
		int userID = 0;
		Connection con;
		try {
			con = bankCon.getDbConnection();
			String sql ="select userID from bankUser where userName=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				userID=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			return userID;
	
	
	}
	
	//isValid: check if a customer exists in db: true is NOT exist
	public boolean isValid (String userName) {
		boolean valid = true;
		try(Connection con1 = bankCon.getDbConnection()){
			//sql statement
			String sql = "select 1 from bankUser where userName=?";
			PreparedStatement cs = con1.prepareStatement(sql);
			cs.setString(1, userName);
			
			ResultSet rs = cs.executeQuery();
			
			if (rs.next()) {
				
				valid = rs.next();
			}
		
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	
		}
		return valid;
	} 
	
	//getAll information from a userName
	public User getAll(String userName) {
		//User userInfo = new User();
		try(Connection con = bankCon.getDbConnection()){
			//sql statement
			
			User userInfo = new User();
			String sql = "select * from bankUser where userName=?";
			PreparedStatement cs = con.prepareStatement(sql);
			cs.setString(1, userName);
			
			ResultSet rs = cs.executeQuery();
			
			while(rs.next()){
				userInfo = new User(userName,rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
			}
			return userInfo;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	public int getAccountID(double balance) {
		int accountID = 0;
		Connection con;
		try {
			con = bankCon.getDbConnection();
			String sql ="select accountID from bankAccount where accountBalance=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, balance);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				accountID=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			return accountID;
	}
	public void openBankAccount(double balance, String type) {
		try (Connection con = bankCon.getDbConnection()){
			
			String sql = "insert into bankAccount(accountBalance, accountStatus, accountType) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, balance);
			ps.setString(2, "pending");
			ps.setString(3, type);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public List<BankAccount> getAllAccount (int userID){
		List<BankAccount> accList = new ArrayList<>();
		try (Connection con = bankCon.getDbConnection()){
			String sql = "select * from bankAccount ba left outer join userJunctionBankAccount j on ba.accountID = j.account_id inner join bankUser b on j.user_id = b.userID where b.userID =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, userID);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				accList.add(new BankAccount(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accList;
	}
	
	public void transferAccount (int userID, int transferingAccountID, int transferedAccountID, double amount) {
		BankAccountService transferringAccount = new BankAccountService(transferingAccountID);
		BankAccountService transferedAccount = new BankAccountService(transferedAccountID);
		
		transferringAccount.withdrawal(transferingAccountID, amount);
		transferedAccount.deposit(transferedAccountID, amount);
		
	}
	
	public void insertJunction(int userID, int accountID) {
		try (Connection con = bankCon.getDbConnection()){
			
			String sql = "insert into userJunctionBankAccount(user_id, account_id) values(?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setInt(2, accountID);
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	}
		
	
	

