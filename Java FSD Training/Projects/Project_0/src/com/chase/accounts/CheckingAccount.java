package com.chase.accounts;

public class CheckingAccount extends Account{
	public CheckingAccount() {
		this.type = "Checking";
	}
	
	public CheckingAccount(int owner) {
		this.owner = owner;
		this.type = "Checking";
	}
}
