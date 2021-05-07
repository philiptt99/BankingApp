package com.project0.dao;

public interface UserDao{
	boolean doesExist(String username);
	void register();
	void logIn();
}
