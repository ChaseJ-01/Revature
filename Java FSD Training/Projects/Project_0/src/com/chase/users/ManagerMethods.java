package com.chase.users;

import java.sql.SQLException;
import java.util.List;

import com.chase.accounts.Account;
import com.chase.accounts.Transaction;
import com.chase.database.BankDAOFactory;

public class ManagerMethods {
	private static ManagerMethods manager = null;
	
	private ManagerMethods() {
		
	}
	
	public static ManagerMethods getManagerMethods() {
		if(manager == null) {
			manager = new ManagerMethods();
		}
		return manager;
	}
	
	public static List<User> searchUsers(String column, String criterion) {
		try {
			return BankDAOFactory.getBankDAO().getUsers();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Account> searchAccounts(String column, String criterion){
		try {
			return BankDAOFactory.getBankDAO().getAccounts();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Transaction> searchTransactions(String column, String criterion){
		try {
			return BankDAOFactory.getBankDAO().getTransactions();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
