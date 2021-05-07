package com.project0.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankDbConnection {
	
	//url of the bankdb
	private final String URL = "jdbc:postgresql://rev-can-training.czbbfxriy83g.ca-central-1.rds.amazonaws.com:5432/bankdb";
	//username of bankdb
	private final String username = "bankuser";
	//password of bankdb
	private final String password ="Passw0rd";
	
	public Connection getDbConnection() throws SQLException{
		return DriverManager.getConnection(URL, username, password);
	}

}
