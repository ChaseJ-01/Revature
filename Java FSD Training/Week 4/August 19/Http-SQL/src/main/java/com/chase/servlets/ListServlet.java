package com.chase.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.chase.database.Employee;
import com.chase.database.EmployeeDAO;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ListServlet extends HttpServlet{
	public List<Employee> employee;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		try(PrintWriter out = res.getWriter()){
			List<Employee> employees = EmployeeDAO.getDAO().getAllEmployees();
			HttpSession ses = req.getSession();
			
			out.print("<!DOCTYPE html><html><head><meta charset=\"ISO-8859-1\"><title>Employees</title></head><body>");
			
			out.print("<a href='form.html'>Add Employee</a><table>");
			out.print("<tr><td>ID</td><td>Name</td><td>Email</td><td>Gender</td><td>Country</td></tr>");
			if(employees != null) {
				int i=1;
				for(Employee e : employees) {
					out.print("<tr><td>" + e.getID() + "</td><td>" + e.getName() + "</td><td>" + e.getEmail() + "</td><td>" + e.getGender() + "</td><td>" + e.getCountry() + "</td>");
					out.print("<td><a href='edit?id=" + i + "'>Edit<a></td>");
					out.print("<td><a href='delete?id=" + i + "'>Delete<a></td>");
					out.print("</tr>");
					i++;
				}
			}
			
			out.print("</table></body></html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
