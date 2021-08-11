package com.chase.users;

import java.sql.SQLException;
import java.util.*;

import com.chase.accounts.Account;
import com.chase.accounts.CheckingAccount;
import com.chase.accounts.Transaction;
import com.chase.application.InputManager;
import com.chase.application.PasswordManager;
import com.chase.database.BankDAOFactory;

/*
 * This class is the real spaghetti code. If I were smarter, I would have made my menu options their own objects with a select() method.
 * That method would then implement the desired functionality, and better yet it would be adjustable. For example, you could dynamically add
 * choices to a menu object at runtime, thus allowing for a cleaner implementation of the Manager vs Employee menus below.
 * 
 * Originally, I had planned to make each type of user its own object and override methods to modify functionality.
 * I really do not know why I decided against that. 
 * I believe it might be because we learned SQL fairly late, long after I had implemented core functionality to the project.
 */

public class User {
	private int id;
	private String type = "User";
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String pass_hash;
	private String date_created;
	private String pass_salt;
	private PasswordManager pManager = PasswordManager.getPasswordManager();
	protected List<Account> accounts = new ArrayList<>();
	
	public User() {
		
	}
	
	public User(int id, String type, String username, String firstname, String lastname, String email, String pass_hash, String date_created, String pass_salt) {
		this.id = id;
		this.type = type;
		this.username = username;
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = email;
		this.pass_hash = pass_hash;
		this.date_created = date_created;
		this.pass_salt = pass_salt;
		try {
			this.accounts = BankDAOFactory.getBankDAO().userBankAccounts(this.id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User(String username, String firstname, String lastname, String email, String pass_hash, String pass_salt) {
		this.username = username;
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = email;
		this.pass_hash = pass_hash;
		this.pass_salt = pass_salt;
	}
	
	public boolean login() {
		String password;
		InputManager manager = InputManager.getInputManager();
		
		if(this.type.equals("Inactive")) {
			return false;
		}
		
		//Add code that hashes the password
		System.out.print("Password: ");
		password = pManager.getHash(manager.getString(), pass_salt);
		
		if (password.equals(this.pass_hash)){
			return true;
		}
		return false;
	}
	
	public String type() {
		return type;
	}
	
	public void printUser() {
		System.out.println("	" + id + ": " + firstName + " " + lastName + " (" + username + ", " + email +") - " + type + " Created " + date_created);
	}
	
	public void printAccounts() {
		if(!accounts.isEmpty()) {
			System.out.println("==========");;
			System.out.println("Your Accounts:\n");
			for(Account e : accounts) {
				if(!e.getStatus().equals("Active") && !e.getStatus().equals("Pending Approval")) {
					continue;
				}
				System.out.print("	" + e.getId() + " - " + e.getType() + ": $" + e.getBalance());
				if(e.getStatus().equals("Pending Approval")) {
					System.out.print(" [Pending Approval]");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}
	
	public void userMenu() {
		
		if(type.equals("Manager") || type.equals("Employee")) {
			employeeMenu();
			return;
		}
		
		printAccounts();
		
		while(true) {
			System.out.println("\n==========");
			System.out.println("Please enter a command:");
			System.out.println("	1. Apply for a new bank account");
			System.out.println("	2. Access an account");
			System.out.println("	3. Log out");
			
			switch(InputManager.getInputManager().getInt()) {
				case 1:{
					try {
						createAccount();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				}
				case 2: selectAccount(); break;
				case 3: return;
				default: break;
			}
		}
	}
	
	public Account createAccount() throws SQLException {
		Account account = null;
		boolean flag = true;
		
		while(flag) {
			System.out.println("What type of account are you applying for?");
			System.out.println("	1. Checking");
			System.out.println("	2. Quit");
			
			switch(InputManager.getInputManager().getInt()) {
				case 1: account = new CheckingAccount(this.id); flag = false; break;
				case 2: return null;
				default: break;
			}
		}
		
		this.type = "Customer";
		accounts.add(account);
		
		BankDAOFactory.getBankDAO().userCreateAccount(account);
		
		return account;
	}
	
	private void selectAccount() {
		Account account = null;
		
		if(accounts.isEmpty()) {
			System.out.println("You have no active accounts.");
			return;
		}
		
		printAccounts();
		
		System.out.print("Select Account to access: ");
		int input = InputManager.getInputManager().getInt();
		
		for(Account e : accounts) {
			if(e.getId() == input) {
				account = e;
				break;
			}
		}
		
		if(!account.getStatus().equals("Active")) {
			System.out.println("This account is not active, please contact an employee for help.");
			return;
		}
		
		while(true) {
			System.out.print("	" + account.getId() + " - " + account.getType() + ": $" + account.getBalance());
			System.out.println("\n\nPlease enter a command:");
			System.out.println("	1. Make a deposit");
			System.out.println("	2. Make a withdrawal");
			System.out.println("	3. Transfer funds");
			System.out.println("	4. Manage pending transfers");
			System.out.println("	5. Transaction history");
			System.out.println("	6. Close account");
			System.out.println("	7. Go back");
			
			
			switch(InputManager.getInputManager().getInt()) {
				case 1: {
					System.out.print("\nEnter amount to deposit: ");
					account.deposit(InputManager.getInputManager().getDouble());
					break;
				}
				case 2: {
					System.out.print("\nEnter amount to withdraw: ");
					account.withdraw(InputManager.getInputManager().getDouble());
					break;
				}
				case 3: {
					System.out.print("\nEnter amount to transfer: ");
					double transfer = InputManager.getInputManager().getDouble();
					if(transfer < 0) {
						System.out.println("Amount must be positive!");
						break;
					}
					if(transfer > account.getBalance()) {
						System.out.println("Insufficient funds.");
						break;
					}
					System.out.print("Enter ID of target account: ");
					int target = InputManager.getInputManager().getInt();
					Account targetAccount = null;
					try {
						targetAccount = BankDAOFactory.getBankDAO().getAccountByID(target);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(targetAccount == null) {
						System.out.println("Invalid ID!");
						break;
					}
					account.transfer(transfer, target);
				}
				case 4:{
					List<Transaction> transactions = null;
					Transaction transaction = null;
					try {
						transactions = BankDAOFactory.getBankDAO().getPendingTransfers(account.getId());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					if(transactions.isEmpty()) {
						System.out.println("No pending trasfers.");
						break;
					}
					
					for(Transaction e : transactions) {
						try {
							e.printTransaction(account.getId());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
					System.out.print("\nSelect transfer to manage: ");
					int input2 = InputManager.getInputManager().getInt();
					
					for(Transaction e : transactions) {
						if(input2 == e.getID()) {
							transaction = e;
						}
					}
					
					System.out.println("\nSelect option:");
					System.out.println("	1. Approve");
					System.out.println("	2. Reject");
					System.out.println("	3. Go back");
					
					switch(InputManager.getInputManager().getInt()) {
						case 1: transaction.approveTransaction(); account.setBalance(account.getBalance() + transaction.getAmount()); break;
						case 2: transaction.rejectTransaction(); break;
						case 3: break;
						default: break;
					}
					break;
				}
				case 5:{
					List<Transaction> transactions = null;
					try {
						transactions = BankDAOFactory.getBankDAO().getTransactionHistory(account.getId());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					for(Transaction e : transactions) {
						try {
							e.printTransaction(account.getId());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					break;
				}
				case 6:{
					System.out.println("Are you sure you want to close this account?");
					System.out.println("	1. No");
					System.out.println("	2. Yes");
					
					switch(InputManager.getInputManager().getInt()) {
						case 1: break;
						case 2: 
						default: break;
					}
					return;
				}
				case 7:{
					return;
				}
				default: {
					System.out.println("Command not recognized!");
					break;
				}
			}
		}
	}
	
	private void employeeMenu() {
		if(!this.type.equals("Employee") && !this.type.equals("Manager")) {
			System.out.println("Error: Non-employee accessing employee options.");
			return;
		}
		//List accounts
		//List users
		//Search user by username
		//List pending accounts
		//approve pending accounts
		//view all transaction history
		
		while(true) {
			System.out.println("==========");
			System.out.println("Please enter a command: ");
			System.out.println("	1. List users");
			System.out.println("	2. List accounts");
			System.out.println("	3. Manage pending accounts");
			System.out.println("	4. View transaction history");
			System.out.println("	5. View specific user's accounts");
			if(this.type.equals("Manager")) {
				System.out.println("	6. Manage accounts");
				System.out.println("	7. Manage users");
				System.out.println("	8. Quit");
			} else {
				System.out.println("	6. Quit");
			}
			
			switch(InputManager.getInputManager().getInt()) {
				//Search Users
				case 1: {
					List<User> list = null;
					try {
						list = BankDAOFactory.getBankDAO().getUsers();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println("==========");
					for(User e : list) e.printUser();
					System.out.print("\n");
					break;
				}
				
				//Search Accounts
				case 2: {
					List<Account> list = null;
					try {
						list = BankDAOFactory.getBankDAO().getAccounts();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					System.out.println("==========");
					for(Account e : list) e.printAccount();
					System.out.print("\n");
					break;
				}
				
				//Manage Pending Accounts
				case 3:{
					List<Account> list = null;
					Account account = null;
					try {
						list = BankDAOFactory.getBankDAO().getPendingAccounts();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					System.out.println("==========");
					for(Account e : list) e.printAccount();
					System.out.print("\n");
					
					System.out.print("Select account to manage: ");
					int input2 = InputManager.getInputManager().getInt();
					
					for(Account e : list) {
						if(e.getId() == input2) {
							account = e;
							break;
						}
					}
					
					System.out.println("==========");;
					System.out.println("Please enter a command:");
					System.out.println("	1. Approve");
					System.out.println("	2. Reject");
					System.out.println("	3. Go back");
					
					switch(InputManager.getInputManager().getInt()) {
						case 1: account.approveAccount(); break;
						case 2: account.rejectAccount(); break;
						case 3: break;
						default: System.out.println("Command not recognized."); break;
					}
					
					break;
				}
				
				//View Transaction History
				case 4: {
					List<Transaction> list = null;
					try {
						list = BankDAOFactory.getBankDAO().getTransactions();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					System.out.println("==========");
					for(Transaction e : list) e.printTransaction();
					System.out.print("\n");
					break;
				}
				
				case 5: {
					List<User> list = null;
					User user = null;
					try {
						list = BankDAOFactory.getBankDAO().getUsers();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					System.out.println("==========");
					for(User e : list) e.printUser();
					System.out.print("\n");
					
					System.out.print("Select user to manage: ");
					int input2 = InputManager.getInputManager().getInt();
					
					for(User e : list) {
						if(e.getID() == input2) {
							user = e;
							break;
						}
					}
					
					for(Account e : user.accounts) e.printAccount();
					
					break;
				}
				
				//--- Manager Only ---
				//Manage Accounts
				case 6: {
					
					if(!this.type.equals("Manager")) {
						return;
					}
					
					List<Account> list = null;
					Account account = null;
					try {
						list = BankDAOFactory.getBankDAO().getAccounts();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					System.out.println("==========");
					for(Account e : list) e.printAccount();
					System.out.print("\n");
					
					
					System.out.print("Select account to manage: ");
					int input2 = InputManager.getInputManager().getInt();
					
					for(Account e : list) {
						if(e.getId() == input2) {
							account = e;
							break;
						}
					}
					
					System.out.println("==========");;
					System.out.println("Please enter a command:");
					System.out.println("	1. Activate");
					System.out.println("	2. Deactivate");
					System.out.println("	3. Go back");
					
					switch(InputManager.getInputManager().getInt()) {
						case 1: account.approveAccount(); break;
						case 2: account.deactivateAccount(); break;
						case 3: break;
						default: System.out.println("Command not recognized."); break;
					}
					
					break;
				}
				
				//Manage Users
				case 7: {
					if(!this.type.equals("Manager")) {
						System.out.println("Command not recognized.");
						break;
					}
					
					List<User> list = null;
					User user = null;
					try {
						list = BankDAOFactory.getBankDAO().getUsers();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					System.out.println("==========");
					for(User e : list) e.printUser();
					System.out.print("\n");
					
					
					System.out.print("Select user to manage: ");
					int input2 = InputManager.getInputManager().getInt();
					
					for(User e : list) {
						if(e.getID() == input2) {
							user = e;
							break;
						}
					}
					
					System.out.println("==========");;
					System.out.println("Please enter a command:");
					System.out.println("	1. Set user to employee");
					System.out.println("	2. Set user to manager");
					System.out.println("	3. Deactivate user");
					System.out.println("	4. Activate user");
					System.out.println("	5. Go back");
					
					
					switch(InputManager.getInputManager().getInt()) {
						case 1: user.userToEmployee();; break;
						case 2: user.userToManager(); break;
						case 3: user.deactivateUser(); break;
						case 4: user.activateUser(); break;
						case 5: break;
						default: System.out.println("Command not recognized."); break;
					}
					
					break;
				}
				
				//Quit
				case 8: {
					if(!this.type.equals("Manager")) {
						System.out.println("Command not recognized.");
						break;
					}
					return;
				}
				default:{
					System.out.println("Command not recognized.");
					break;
				}
			}
		}
	}
	
	public void userToEmployee() {
		try {
			BankDAOFactory.getBankDAO().updateUserType(id, "Employee");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void userToManager() {
		try {
			BankDAOFactory.getBankDAO().updateUserType(id, "Manager");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deactivateUser() {
		try {
			BankDAOFactory.getBankDAO().updateUserType(id, "Inactive");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void activateUser() {
		try {
			BankDAOFactory.getBankDAO().updateUserType(id, "User");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getID() {
		return this.id;
	}
}
