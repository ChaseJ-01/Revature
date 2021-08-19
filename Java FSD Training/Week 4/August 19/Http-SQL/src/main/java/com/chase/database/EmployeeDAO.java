package com.chase.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	private Connection connection = null;
	private static EmployeeDAO dao = null;
	
	private EmployeeDAO() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static EmployeeDAO getDAO() {
		if(dao==null) {
			dao = new EmployeeDAO();
		}
		return dao;
	}
	
	public List<Employee> getAllEmployees() throws SQLException{
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM employees");
		ResultSet rs = ps.executeQuery();
		List<Employee> employees = new ArrayList<Employee>();
		
		while(rs.next()) {
			employees.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
		}
		
		return employees;
	}
	
	public void addEmployee(Employee e) throws SQLException{
		PreparedStatement ps = connection.prepareStatement("INSERT INTO employees (name, email, gender, country) VALUES (?, ?, ?, ?)");
		ps.setString(1, e.getName());
		ps.setString(2, e.getEmail());
		ps.setString(3, e.getGender());
		ps.setString(4, e.getCountry());
		
		if(ps.executeUpdate() > 0) {
			return;
		} else {
			throw new SQLException("Failed to add employee.");
		}
	}
	
	public void removeEmployee(int id) throws SQLException{
		PreparedStatement ps = connection.prepareStatement("DELETE FROM employees WHERE id = ?");
		ps.setInt(1, id);
		
		if(ps.executeUpdate() > 0) {
			return;
		} else {
			throw new SQLException("Failed to remove employee.");
		}
	}
	
	public void editEmployee(Employee e) throws SQLException{
		PreparedStatement ps = connection.prepareStatement("UPDATE employees SET name=?, email=?, gender=?, country=? WHERE id=?");
		ps.setString(1, e.getName());
		ps.setString(2, e.getEmail());
		ps.setString(3, e.getGender());
		ps.setString(4, e.getCountry());
		ps.setInt(5, e.getID());
		
		if(ps.executeUpdate() > 0) {
			return;
		} else {
			throw new SQLException("Failed to edit employee.");
		}
	}
}
