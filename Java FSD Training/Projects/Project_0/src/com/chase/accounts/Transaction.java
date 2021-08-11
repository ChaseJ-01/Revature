package com.chase.accounts;

import java.sql.SQLException;

import com.chase.database.BankDAOFactory;

/*
 * Now this class was a source of some trouble. It wasn't difficult to create or add to the database, but it has too many cases to be perfect.
 * If a source account or target account is NULL in the database, the corresponding field here is a negative number.
 * This leads to the need to create numerous cases when dealing with this type of object. 4 cases each, to be exact.
 * This instead could have been handled with a bit of inheritance.
 * Transaction would be the parent, and Transfer, Withdrawal, and Deposit would all extend Transaction.
 * Thus, no cases would be needed as I would be able to call overriden methods instead.
 */

public class Transaction {
	private int id;
	private double amount = 0.0;
	private int source = -1;
	private int target = -1;
	private String timestamp = null;
	private String status;
	
	public Transaction() {
		
	}
	
	public Transaction(int id, double amount, int source, int target, String timestamp, String status) {
		this.id = id;
		this.amount = amount;
		this.source = source;
		this.target = target;
		this.timestamp = timestamp;
		this.status = status;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void approveTransaction() {
		try {
			BankDAOFactory.getBankDAO().approveTransaction(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rejectTransaction() {
		try {
			BankDAOFactory.getBankDAO().rejectTransaction(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void printTransaction() {
		System.out.print("	" + this.id + ": " + timestamp + " : $" + amount);
		if(source > 0 && target > 0) {
			System.out.println(" transferred from acount " + source + " to account " + target);
		} else if (source > 0) {
			System.out.println(" withdrawn from account " + source);
		} else if (target > 0) {
			System.out.println(" deposited into account " + target);
		}
	}
	
	public void printTransaction(int account) throws Exception{
		if(account != source && account != target) {
			throw new Exception("Invalid Transaction");
		}
		System.out.print("	" + this.id + ": " + timestamp + " : ");
		if(account == source) {
			System.out.print("-");
		}
		System.out.print("$" + this.amount + " -- ");
		if(account == source && target <= 0) {
			System.out.print("Withdrawal\n");
			return;
		}
		if(account == target && source <= 0) {
			System.out.print("Deposit\n");
			return;
		}
		if(account == source && target > 0) {
			System.out.print("Transfer to account " + target);
		}
		if(account == target && source > 0) {
			System.out.print("Transfer from account " + source);
		}
		System.out.print(" [Status: " + this.status + "]\n");
	}
	
	public double getAmount() {
		return amount;
	}
}
