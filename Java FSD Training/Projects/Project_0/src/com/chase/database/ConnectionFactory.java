package com.chase.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
	private static Connection connection = null;
	
	private ConnectionFactory() {
		
	}
	
	public static Connection getConnection() throws SQLException {
		if(connection == null) {
			ResourceBundle config = ResourceBundle.getBundle("com/chase/database/dbconfig");
			String url = config.getString("url");
			String username = config.getString("username");
			String password = config.getString("password");
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}
}
