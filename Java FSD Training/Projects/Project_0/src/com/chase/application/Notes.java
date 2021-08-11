package com.chase.application;

public class Notes {
	//Accounts
	//id - PRIMARY KEY
	//owner - REQUIRED, FOREIGN KEY
	//type
	//balance - REQUIRED
	//status
	//date_created
	//limit - Withdraw limit for normal accounts, credit limit for lines of credit
	
	//Users
	//id - PRIMARY KEY
	//type
	//username - REQUIRED, UNIQUE
	//name
	//email
	//password_hash - REQUIRED
	//date_created
	
	//Transactions
	//id - PRIMARY KEY
	//account - REQUIRED, FOREIGN KEY
	//type - REQUIRED
	//balance - REQUIRED
	//status
	//date_posted
	//time_posted
	
	//Login_History
	//id - PRIMARY KEY
	//user - FOREIGN KEY
	//type - REQUIRED : login/logout
	//date
	//time
	
	//Billing
	//id - PRIMARY KEY
	//account - REQUIRED
	//amount - amount owed
	//date_posted - 
	
	//PRESENTATION
	//Start with a customer who needs to approve a transaction
	//Deposit less than $5000
	//Withdraw more than total
	//Deposit more than $5000, show that it needs approval
	//Transfer funds to another account
	
	//PROCEDURES
	//sp_list_accounts - lists all bank accounts for a user
	
	//Logout, log back in as employee
	//Approve transaction
	//
	
	//TODO
	//Implement Log4j
}
