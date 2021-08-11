package com.chase.application;

import com.chase.accounts.*;
import com.chase.database.*;
import com.chase.users.User;

import java.sql.*;
import java.util.*;

import org.apache.log4j.*;

/*
 * This is my main class, it contains the code used to log in and create a user account. 
 * Once a user is logged in, this class connects the user to their specific user object, so the application always knows which user is logged in.
 * Once connected, all further functionality is handled by the User object itself, stupidly enough.
 * Handling menus in the user object was by far my worst mistake on this project - consider this lesson learned.
 */

public class Application {
	
	//We did not get very much instruction on how to use log4j, so this is the bare minimum.
	//It outputs to log.out, and logs only things that happen in this class.
	//I'll have to play with it more to really understand what I can do with it in the future.
	public static Logger logger = LogManager.getLogger(Application.class);
	
	public static void main(String[] args) {
		FileAppender fa = new FileAppender();
		fa.setFile("log.out");
		fa.setThreshold(Level.INFO);
		fa.setLayout(new PatternLayout("%d - %p [%c]: %m%n"));
		fa.activateOptions();
		LogManager.getRootLogger().addAppender(fa);
		logger.info("Logger initialized");
		
		BankDAO dao = BankDAOFactory.getBankDAO();
		logger.info("DAO initialized");
		
		User user = null;
		InputManager inputManager = InputManager.getInputManager();
		logger.info("Input Manager initialized");
		
		while(true) {
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
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(user != null) {
						System.out.println("Login Successful!");
						logger.info("User logged in");
						user.userMenu();
						logger.info("User logged out");
					} else {
						System.out.println("Incorrect Username/Password combination");
					}
					break;
				}
				case 2: createUser(); break;
				case 3: return;
				default: System.out.println("Unknown command"); break;
			}
		}
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


