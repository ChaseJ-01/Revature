package com.chase.database;

public class BankDAOFactory {
	//This factory doesn't need to exist (due to the redundancy of the interface), but I made it
	//because I figured you wanted to see an interface and factory setup.
	private static BankDAO dao = null;
	
	private BankDAOFactory() {
		
	}
	
	public static BankDAO getBankDAO() {
		if(dao == null) {
			dao = new BankDAOImpl();
		}
		return dao;
	}
}
