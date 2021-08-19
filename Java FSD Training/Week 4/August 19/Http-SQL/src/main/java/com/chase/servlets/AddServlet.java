package com.chase.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.chase.database.Employee;
import com.chase.database.EmployeeDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		try(PrintWriter out = res.getWriter()){
			String name = req.getParameter("employeeName");
			String email = req.getParameter("employeeEmail");
			String gender = req.getParameter("employeeGender");
			String country = req.getParameter("employeeCountry");
			HttpSession ses = req.getSession(false);
			int id = -1;
			
			if(ses.getAttribute("id") != null) {
				id = Integer.parseInt(ses.getAttribute("id").toString());
			}
			
			if(id>0) {
				EmployeeDAO.getDAO().editEmployee(new Employee(id, name, email, gender, country));
			} else {
				EmployeeDAO.getDAO().addEmployee(new Employee(-1, name, email, gender, country));
			}
			
			ses.setAttribute("id", -1);
			
			RequestDispatcher rd = req.getRequestDispatcher("employees");
			rd.forward(req, res);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
