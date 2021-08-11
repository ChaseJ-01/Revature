package com.chase.database;

import java.sql.SQLException;
import java.util.List;

import com.chase.accounts.Account;
import com.chase.accounts.Transaction;
import com.chase.users.User;

public interface BankDAO {
	//This interface doesn't need to exist, but I made it just in case that's what you wanted to see.
	public List<Account> userBankAccounts(int owner) throws SQLException;
	public User userLogin(String username) throws SQLException;
	public List<User> getUsers() throws SQLException;
	public void userCreateUser(String username, String password, String salt, String fName, String lName, String email,
			String type) throws SQLException;
	public void userCreateAccount(Account account) throws SQLException;
	public int postTransaction(double amount, int source, int target) throws SQLException;
	public void approveTransaction(int id) throws SQLException;
	public Account getAccountByID(int id) throws SQLException;
	public void rejectTransaction(int id) throws SQLException;
	public List<Transaction> getTransactionHistory(int account) throws SQLException;
	public List<Transaction> getPendingTransfers(int account) throws SQLException;
	public List<Account> userActiveBankAccounts(int owner) throws SQLException;
	public List<Account> getAccounts() throws SQLException;
	public List<Transaction> getTransactions() throws SQLException;
	public List<Account> getPendingAccounts() throws SQLException;
	public void updateAccountStatus(int id, String status) throws SQLException;
	public void updateUserType(int id, String type) throws SQLException;
}
