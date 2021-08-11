package com.chase.accounts;

import java.sql.SQLException;
import java.util.List;

import com.chase.database.BankDAOFactory;

/*
 * Originally, I had planned on creating various types of accounts that all had special features like interest or withdrawal limits.
 * Instead due to time constraints, there exists only one type of account. Still, not bad. This could easily be refactored to allow
 * multiple different account types.
 */

public class Account {
	protected int id;
	protected int owner;
	protected String type = "BaseAccount";
	protected double balance = 0.00;
	protected String status = "Pending Approval";
	protected String date_created;
	protected double limit = 5000.00;
	
	public Account() {
		
	}
	
	public Account(int owner) {
		this.owner = owner;
	}
	
	public Account(int id, int owner, String type, double balance, String status, String date_created, double limit) {
		this.id = id;
		this.owner = owner;
		this.type = type;
		this.balance = balance;
		this.status = status;
		this.date_created = date_created;
		this.limit = limit;
	}
	
	public int getId() {
		return id;
	}

	public int getOwner() {
		return owner;
	}

	public String getType() {
		return type;
	}

	public double getBalance() {
		return balance;
	}

	public String getStatus() {
		return status;
	}

	public String getDate_created() {
		return date_created;
	}

	public double getLimit() {
		return limit;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

	public boolean withdraw(double amount) {
		if(amount < 0) {
			System.out.println("Amount must be positive!");
			return false;
		}
		if(amount > balance) {
			System.out.println("Insufficient funds.");
			return false;
		} else {
			try {
				int transactionid = BankDAOFactory.getBankDAO().postTransaction(amount, -1, id);
				BankDAOFactory.getBankDAO().approveTransaction(transactionid);
				balance -= amount;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}
	
	public boolean deposit(double amount) {
		if(amount < 0) {
			System.out.println("Amount must be positive!");
			return false;
		}
		try {
			int transactionid = BankDAOFactory.getBankDAO().postTransaction(amount, -1, id);
			BankDAOFactory.getBankDAO().approveTransaction(transactionid);
			balance += amount;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public void transfer(double amount, int target) {
		if(amount < 0) {
			System.out.println("Amount must be positive!");
			return;
		}
		try {
			BankDAOFactory.getBankDAO().postTransaction(amount, id, target);
			balance -= amount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Transaction> listTransactions(){
		return null;
	}
	
	public void printAccount() {
		System.out.println("	" + id + " " + "(Owner ID: " + owner + "): " + type + ": $" + balance + " [" + status + "]" );
	}
	
	public void approveAccount() {
		try {
			BankDAOFactory.getBankDAO().updateAccountStatus(id, "Active");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rejectAccount() {
		try {
			BankDAOFactory.getBankDAO().updateAccountStatus(id, "Rejected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deactivateAccount() {
		try {
			BankDAOFactory.getBankDAO().updateAccountStatus(id, "Inactive");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
