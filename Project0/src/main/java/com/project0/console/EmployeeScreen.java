package com.project0.console;

import java.util.Scanner;

import com.project0.service.EmployeeLogIn;
import com.project0.service.EmployeeRegister;

public class EmployeeScreen implements Screen{

	@Override
	public void render() {
		// TODO Auto-generated method stub
		System.out.println("Welcome back Employee, please choose from options below:");
		System.out.println("1. Register new account\n" +
						 "2. Log in to the system\n"
						 + "3. Back to main menu\n");
		
		Scanner newInput = new Scanner(System.in);
		while(true) {
			char option = newInput.next().charAt(0); //take the input of user
			switch(option) {
			
			case '1':
				//register
				
				System.out.println("Registering");
				
				EmployeeRegister emReg = new EmployeeRegister();
				emReg.register();
				break;
			case '2':
				//login
				System.out.println("Log in to your account");
				
				EmployeeLogIn emLog = new EmployeeLogIn();
				emLog.logIn();
				break;
			case '3':
				//back to main
				
				System.out.println("back to Main Menu");
				Screen mainScreen = new MainMenu();
				mainScreen.render();
				break;
			default:
				System.out.println("This is not a valid option Employee. Please re-enter with 1, 2, or 3 only.");
				break;
			
			}
			//newInput.close();
		
		}
		
	}

}
