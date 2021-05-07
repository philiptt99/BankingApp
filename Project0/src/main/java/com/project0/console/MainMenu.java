package com.project0.console;

import java.util.Scanner;

public class MainMenu implements Screen{

	@Override
	public void render() {
		// TODO Auto-generated method stub
		//Greeting!
				System.out.println("Welcome to Bank of ABC, how can we help you today?");
				System.out.println("Please choose from the option below:");
				System.out.println("Select from below your role :\n"
						+ "1) Admin\n"
						+ "2) Employee\n"
						+ "3) Customer\n"
						);
		Scanner inputScan = new Scanner(System.in);
		
		
		while(true) {
			char option = inputScan.next().charAt(0); //take the input of user
			switch(option) {
			
			case '1':
				//render AdminScreen
				System.out.println("Admin Menu");
				Screen admin = new AdminScreen();
				admin.render();
				break;
			case '2':
				//render EmployeeScreen
				System.out.println("Employee Menu");
				Screen emp = new EmployeeScreen();
				emp.render();
				break;
			case '3':
				//render CustomerScreen
				
				System.out.println("Customer Menu");
				Screen cus = new CustomerScreen();
				cus.render();
				break;
			default:
				System.out.println("Invalid option to choose from. Please re-enter with 1, 2, or 3 only.");
				break;
			
			}
			//inputScan.close();
		}
		
	}

}
