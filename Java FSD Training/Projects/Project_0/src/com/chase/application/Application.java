package com.chase.application;

import com.chase.accounts.*;
import com.chase.database.*;
import com.chase.users.User;

import java.sql.*;
import java.util.*;

import org.apache.log4j.*;

public class Application {
	
	//We did not get very much instruction on how to use log4j, so this is the bare minimum.
	public static Logger logger = LogManager.getLogger(Application.class);
	
	public static void main(String[] args) {
		ConsoleAppender ca = new ConsoleAppender();
		ca.setThreshold(Level.INFO);
		ca.setLayout(new PatternLayout("%d - %p [%c]: %m%n"));
		ca.activateOptions();
		LogManager.getRootLogger().addAppender(ca);
		logger.info("Logger initialized");
		
		boolean error = true;
		BankDAO dao = BankDAOFactory.getBankDAO();
		logger.info("DAO initialized");
		
		User user = null;
		InputManager inputManager = InputManager.getInputManager();
		logger.info("Input Manager initialized");
		
		while(error) {
			System.out.println("==========");
			System.out.println("Please enter a command:\n");
			System.out.println("	1. Login");
			System.out.println("	2. Create User Account");
			System.out.println("	3. Quit");
			
			switch(inputManager.getInt()) {
				case 1: {
					System.out.print("Username: ");
					String username = inputManager.getString();
					try {
						user = dao.userLogin(username);
						error = false;
					} catch (SQLException e) {
						e.printStackTrace();
						error = true;
					}
					if(user != null) {
						System.out.println("Login Successful!");
					} else {
						System.out.println("Incorrect Username/Password combination");
						error = true;
					}
					break;
				}
				case 2: createUser(); break;
				case 3: return;
				default: error = true;
			}
		}
		logger.info("User logged in");
		
		user.userMenu();
		
		logger.info("User logged out");
		
		return;
	}
	
	public static void createUser() {
		InputManager manager = InputManager.getInputManager();
		PasswordManager pManager = PasswordManager.getPasswordManager();
		boolean flag = true;
		String salt = pManager.newSalt();
		String password = null;
		
		System.out.print("Username: ");
		String username = manager.getString();
		
		while(flag) {
			System.out.print("Password: ");
			password = pManager.getHash(manager.getString(), salt);
			System.out.print("Confirm Password: ");
			if(!pManager.getHash(manager.getString(), salt).equals(password)) {
				System.out.println("Passwords do not match!");
				continue;
			}
			flag = false;
		}
		
		System.out.print("First Name: ");
		String fName = manager.getString();
		System.out.print("Last Name: ");
		String lName = manager.getString();
		System.out.print("Email: ");
		String email = manager.getString();
		
		try {
			BankDAOFactory.getBankDAO().userCreateUser(username, password, salt, fName, lName, email, "User");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


