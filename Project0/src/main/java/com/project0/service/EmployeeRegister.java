package com.project0.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.project0.console.AdminScreen;
import com.project0.console.CustomerScreen;
import com.project0.console.EmployeeScreen;
import com.project0.console.Screen;
import com.project0.dao.BankDbConnection;
import com.project0.model.User;

public class EmployeeRegister implements RegisterUser{
	
	private BankDbConnection bankCon = new BankDbConnection();


	@Override
	public void register() {
		// TODO Auto-generated method stub
		System.out.println("Employee, welcome to Bank of ABC registration page");
		Scanner inputCus = new Scanner (System.in);
		
		
		System.out.println("Please enter your first name:");
		String firstName = inputCus.next();
		System.out.println("Please enter your last name:");
		String lastName = inputCus.next();
		System.out.println("Please enter a username:");
		String userName = inputCus.next();
		System.out.println("Please enter a password:");
		String password = inputCus.next();
		
		User newUser = new User (userName, password, firstName, lastName, 2);
		
		//Check to see if userName has already there:
		try(Connection con1 = bankCon.getDbConnection()){
			//sql statement
			String sql = "select 1 from bankUser where userName =?";
			PreparedStatement cs = con1.prepareStatement(sql);
			cs.setString(1, userName);
			ResultSet rs = cs.executeQuery();
			
			if (rs.next()) {
				System.out.println("Name already taken!");
				System.out.println("Please press any key to go back to previous menu to register again:");
				if (inputCus.next()!= null) {
				Screen adminScreen = new AdminScreen();
				adminScreen.render();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//SQL to add to user table!!!!
		
		
		try(Connection con = bankCon.getDbConnection()){
			
			//sql statement for function insert_user
			String sql = "insert into bankUser(userName, passKey, firstName, lastName, userRole) values(?,?,?,?,?)";
			PreparedStatement cs = con.prepareStatement(sql);
			
			//cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(1, userName);
			cs.setString(2, password);
			cs.setString(3, firstName);
			cs.setString(4, lastName);
			cs.setInt(5, 2);
			cs.execute();
			//System.out.println(cs.getString(1));
			
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("cant find the user");
		}	
		
		
		//System.out.println("Can you confirm your information");
		System.out.println("Your registration information: "+ newUser );
		System.out.println("Please press any key to go back to Main Menu to log in:");
		if (inputCus.next()!= null) {
		Screen empScreen = new EmployeeScreen();
		empScreen.render();
		}
		inputCus.close();
		
	}

}
