package com.project0.dao;

import java.sql.*;

import com.project0.console.CustomerScreen;
import com.project0.console.Screen;

public class BankDao {
	private BankDbConnection bankCon;
	private int accountID;

	public BankDao(int accountID) {
		this.bankCon = new BankDbConnection();
		this.accountID = accountID;
	}

	public boolean isValid(int accountID) {
		boolean valid = true;
		try (Connection con1 = bankCon.getDbConnection()) {
			// sql statement
			String sql = "select 1 from bankAccount where accountID=?";
			PreparedStatement cs = con1.prepareStatement(sql);
			cs.setInt(1, accountID);

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

	public String accountStatus(int accountID) {
		String stat = null;
		try (Connection con = bankCon.getDbConnection()) {

			String sql = "select accountStatus from bankAccount where accountID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, accountID);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				stat = rs.getString(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stat;
	}

	public String accountType(int accountID) {
		String type = null;
		try (Connection con = bankCon.getDbConnection()) {

			String sql = "select accountType from bankAccount where accountID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, accountID);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				type = rs.getString(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return type;
	}

	public double currentBalance(int accountID) {

		double balance = 0;
		try (Connection con = bankCon.getDbConnection()) {

			String sql = "select accountBalance from bankAccount where accountID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, accountID);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				balance = rs.getDouble(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return balance;
	}

	public void withdraw(int accountID, double withdrawal) {
		double current = currentBalance(accountID);

		double newBalance = current - withdrawal;
		if (newBalance < 0) {
			System.out.println("Withdrawal exceeds current balance.");

		} else {
			try (Connection con = bankCon.getDbConnection()) {

				String sql = "update bankAccount set accountBalance=? where accountId=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setDouble(1, newBalance);
				ps.setInt(2, accountID);
				ps.execute();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void deposit(int accountID, double deposit) {
		double current = currentBalance(accountID);

		double newBalance = current + deposit;

		try (Connection con = bankCon.getDbConnection()) {

			String sql = "update bankAccount set accountBalance=? where accountId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, newBalance);
			ps.setInt(2, accountID);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void editAccountStatus(int accountID, String newStatus) {
		try (Connection con = bankCon.getDbConnection()) {

			String sql = "update bankAccount set accountStatus=? where accountId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, newStatus);
			ps.setInt(2, accountID);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteAccount(int accountID) {
		try (Connection con = bankCon.getDbConnection()) {

			String sql = "delete from bankAccount where accountId=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, accountID);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
