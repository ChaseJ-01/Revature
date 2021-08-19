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

public class EditServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		try(PrintWriter out = res.getWriter()){
			int id = Integer.parseInt(req.getParameter("id"));
			HttpSession ses = req.getSession(false);
			ses.setAttribute("id", id);
			
			RequestDispatcher rd = req.getRequestDispatcher("form.html");
			rd.forward(req, res);
		}
	}
}
