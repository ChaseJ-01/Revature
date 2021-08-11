package com.chase.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chase.accounts.Account;
import com.chase.accounts.Transaction;
import com.chase.application.InputManager;
import com.chase.users.User;

/*
 * I did not like the use of this class + interface at all. 
 * I wrote it this way because it was the architecture I was taught, but I believe I could have made it better.
 * Unfortunately, I was unsure if that would go against program guidelines, so I was stuck writing it all this way.
 */

public class BankDAOImpl implements BankDAO {
	private static Statement statement = null;
	private Connection connection = null;
	
	public BankDAOImpl() {
		try {
			this.connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Account> userBankAccounts(int owner) throws SQLException {
		String command = "CALL sp_list_accounts(?)";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setInt(1, owner);
		ResultSet rs = ps.executeQuery();
		List<Account> accounts = new ArrayList<>();
		
		while(rs.next()) {
			accounts.add(new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getDouble(7)));
		}
		return accounts;
	}
	
	@Override
	public List<Account> userActiveBankAccounts(int owner) throws SQLException {
		String command = "CALL sp_list_accounts(?)";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setInt(1, owner);
		ResultSet rs = ps.executeQuery();
		List<Account> accounts = new ArrayList<>();
		
		while(rs.next()) {
			accounts.add(new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getDouble(7)));
		}
		
		for(Account e : accounts) {
			if(!e.getStatus().equals("Active")) {
				accounts.remove(e);
			}
		}
		return accounts;
	}

	@Override
	public List<User> getUsers() throws SQLException {
		String command = "SELECT * FROM users";
		PreparedStatement ps = connection.prepareStatement(command);
		List<User> list = new ArrayList<User>();
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(8), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9)));
		}
		return list;
	}
	
	@Override
	public List<Account> getAccounts() throws SQLException {
		String command = "SELECT * FROM accounts";
		PreparedStatement ps = connection.prepareStatement(command);
		List<Account> list = new ArrayList<>();
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			list.add(new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getDouble(7)));
		}
		return list;
	}
	
	//Here is an unused function to show off joins. I never used it because it would require a lot of work to factor it in.
	public List<Account> getJoinedAccounts(String column, String criterion) throws SQLException {
		String lowerColumn = column.toLowerCase();
		String command = "SELECT accounts.id, users.first_name, users.last_name, accounts.type, accounts.balance, accounts.status, accounts.date_created FROM users, accounts WHERE accounts.owner = users.id";
		List<Account> list = new ArrayList<>();
		ResultSet rs = statement.executeQuery(command);
		while(rs.next()) {
			list.add(new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getDouble(7)));
		}
		return list;
	}
	
	@Override
	public List<Transaction> getTransactions() throws SQLException {
		String command = "SELECT * FROM transactions";
		PreparedStatement ps = connection.prepareStatement(command);
		List<Transaction> list = new ArrayList<>();
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			list.add(new Transaction(rs.getInt(1), rs.getDouble(4), rs.getInt(2), rs.getInt(3), rs.getString(5), rs.getString(6)));
		}
		return list;
	}

	@Override
	public User userLogin(String username) throws SQLException {
		String command = "SELECT * FROM users WHERE username = ?";
		PreparedStatement ps = connection.prepareStatement(command);
		User user = null;
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			user = new User(rs.getInt(1), rs.getString(2), rs.getString(8), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9));
		}
		
		if(user == null) {
			//This input does nothing except add security so that users cannot know if a username is used.
			System.out.print("Password: ");
			InputManager.getInputManager().getString();
			return null;
		} else if(user.login()){
			return user;
		} else return null;
	}

	@Override
	//This doesn't take a User object in order to keep password hashes secure - otherwise a getPassword function would need to be public.
	public void userCreateUser(String username, String password, String salt, String fName, String lName, String email, String type) throws SQLException {
		String command = "INSERT INTO users (username, pass_hash, pass_salt, first_name, last_name, email, type) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, salt);
		ps.setString(4, fName);
		ps.setString(5, lName);
		ps.setString(6, email);
		ps.setString(7, type);
		
		if(ps.executeUpdate() > 0) {
			System.out.println("User Account created successfully!");
		} else {
			System.out.println("A problem was encountered while trying to create account.");
		}
	}
	
	@Override
	public void userCreateAccount(Account account) throws SQLException {
		String command = "INSERT INTO accounts (owner, type, balance, status, fund_limit) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setInt(1, account.getOwner());
		ps.setString(2, account.getType());
		ps.setDouble(3, account.getBalance());
		ps.setString(4, account.getStatus());
		ps.setDouble(5, account.getLimit());
		
		if(ps.executeUpdate() > 0) {
			System.out.println("Applied for account successfully, please wait for employee approval.");
		} else {
			System.out.println("A problem was encountered while trying to apply for account.");
		}
	}
	
	@Override
	//This method posts transactions, use a source or target of -1 to denote deposits and withdrawals.
	//This method returns the ID of the posted transaction.
	public int postTransaction(double amount, int source, int target) throws SQLException {
		String command = null;
		PreparedStatement ps = null;
		
		if(source <= 0) {
			command = "INSERT INTO transactions (amount, target, status) VALUES (?, ?, ?)";
			ps = connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, amount);
			ps.setInt(2, target);
			ps.setString(3, "Pending");
		} else if (target <= 0 ) {
			command = "INSERT INTO transactions (amount, source, status) VALUES (?, ?, ?)";
			ps = connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, amount);
			ps.setInt(2, source);
			ps.setString(3, "Pending");
		} else {
			command = "INSERT INTO transactions (amount, source, target, status) VALUES (?, ?, ?, ?)";
			ps = connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, amount);
			ps.setInt(2, source);
			ps.setInt(3, target);
			ps.setString(4, "Pending");
		}
		
		statement.execute("SET autocommit = 0");
		statement.execute("START TRANSACTION");
		
		if(source > 0) {
			PreparedStatement ps2 = connection.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE id = ?");
			ps2.setDouble(1, amount);
			ps2.setInt(2, source);
			if(ps2.executeUpdate() <= 0) {
				System.out.println("Something went wrong while withdrawing funds.");
			}
		}
		
		if(ps.executeUpdate() > 0) {
			System.out.println("Transaction successfully posted.");
		} else {
			System.out.println("A problem was encountered while trying to post this transaction.");
		}
		
		statement.execute("COMMIT");
		statement.execute("SET autocommit = 1");
		
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			return rs.getInt(1); 
		}
		return -1;
	}
	
	@Override
	//This method commits changes described by posted transactions.
	public void approveTransaction(int id) throws SQLException{
		String command = "SELECT * FROM transactions WHERE id = ?";
		PreparedStatement ps1 = connection.prepareStatement(command);
		ps1.setInt(1, id);
		Account source = null;
		Account target = null;
		double amount = 0;
		
		ResultSet rs = ps1.executeQuery();
		while(rs.next()) {
			source = getAccountByID(rs.getInt(2));
			target = getAccountByID(rs.getInt(3));
			amount = rs.getDouble(4);
		}
		
		statement.execute("SET autocommit = 0");
		statement.execute("START TRANSACTION");
		//This is commented out because the code now withdraws from source on posting a transaction.
		/*if(source != null) {
			PreparedStatement ps2 = connection.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE id = ?");
			ps2.setDouble(1, amount);
			ps2.setInt(2, source.getId());
			ps2.execute();
		}*/
		
		if(target != null) {
			PreparedStatement ps2 = connection.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE id = ?");
			ps2.setDouble(1, amount);
			ps2.setInt(2, target.getId());
			ps2.execute();
		}
		
		PreparedStatement ps3 = connection.prepareStatement("UPDATE transactions SET status = ? WHERE id = ?");
		ps3.setString(1, "Approved");
		ps3.setInt(2, id);
		ps3.execute();
		
		statement.execute("COMMIT");
		statement.execute("SET autocommit = 1");
		
		System.out.println("Transaction approved.");
	}
	
	@Override
	public void rejectTransaction(int id) throws SQLException {
		String command = "SELECT * FROM transactions WHERE id = ?";
		PreparedStatement ps1 = connection.prepareStatement(command);
		ps1.setInt(1, id);
		ResultSet rs = ps1.executeQuery();
		Account source = null;
		double amount = 0;
		
		statement.execute("SET autocommit = 0");
		statement.execute("START TRANSACTION");
		if(rs.next()) {
			source = getAccountByID(rs.getInt(2));
			amount = rs.getDouble(4);
		}
		
		if(source != null) {
			PreparedStatement ps2 = connection.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE id = ?");
			ps2.setDouble(1, amount);
			ps2.setInt(2, source.getId());
			ps2.execute();
		}
		
		PreparedStatement ps3 = connection.prepareStatement("UPDATE transactions SET status = ? WHERE id = ?");
		ps3.setString(1, "Rejected");
		ps3.setInt(2, id);
		ps3.execute();
		
		statement.execute("COMMIT");
		statement.execute("SET autocommit = 1");
		
		System.out.println("Transaction rejected.");
	}
	
	@Override
	public Account getAccountByID(int id) throws SQLException{
		String command = "SELECT * FROM accounts WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Account account = null;
		while(rs.next()) {
			account = new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getDouble(7));
		}
		return account;
	}
	
	@Override
	public List<Transaction> getPendingTransfers(int account) throws SQLException{
		String command = "SELECT * FROM transactions WHERE target = ? AND status = ?";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setInt(1, account);
		ps.setString(2, "Pending");
		ResultSet rs = ps.executeQuery();
		List<Transaction> transactions = new ArrayList<>();
		while(rs.next()) {
			transactions.add(new Transaction(rs.getInt(1), rs.getDouble(4), rs.getInt(2), rs.getInt(3), rs.getString(5), rs.getString(6)));
		}
		return transactions;
	}
	
	@Override
	public List<Transaction> getTransactionHistory(int account) throws SQLException{
		String command = "SELECT * FROM transactions WHERE source = ? OR target = ?";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setInt(1, account);
		ps.setInt(2, account);
		ResultSet rs = ps.executeQuery();
		List<Transaction> transactions = new ArrayList<>();
		while(rs.next()) {
			transactions.add(new Transaction(rs.getInt(1), rs.getDouble(4), rs.getInt(2), rs.getInt(3), rs.getString(5), rs.getString(6)));
		}
		return transactions;
	}
	
	@Override
	public List<Account> getPendingAccounts() throws SQLException{
		String command = "SELECT * FROM accounts WHERE status = ?";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setString(1, "Pending Approval");
		ResultSet rs = ps.executeQuery();
		List<Account> accounts = new ArrayList<>();
		while(rs.next()) {
			accounts.add(new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getDouble(7)));
		}
		return accounts;
	}
	
	@Override
	public void updateAccountStatus(int id, String status) throws SQLException{
		String command = "UPDATE accounts SET status = ? WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setString(1, status);
		ps.setInt(2, id);
		
		if(ps.executeUpdate() > 0) {
			System.out.println("Account successfully updated.");
		} else {
			System.out.println("A problem was encountered while trying to update account.");
		}
	}
	
	@Override
	public void updateUserType(int id, String type) throws SQLException{
		String command = "UPDATE users SET type = ? WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setString(1, type);
		ps.setInt(2, id);
		
		if(ps.executeUpdate() > 0) {
			System.out.println("User successfully updated.");
		} else {
			System.out.println("A problem was encountered while trying to update user.");
		}
	}
}
