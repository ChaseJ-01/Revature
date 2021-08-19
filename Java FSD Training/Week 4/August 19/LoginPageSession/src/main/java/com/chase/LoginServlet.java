package com.chase;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		try(PrintWriter out = res.getWriter()){
			String username = req.getParameter("username");
			String password = req.getParameter("userpass");
			
			if(!username.equals("") && !password.equals("")) {
				RequestDispatcher rd = req.getRequestDispatcher("/index.html");
				HttpSession ses = req.getSession();
				ses.setAttribute("user_name", username);
				rd.include(req, res);
				out.println("Login successful. You may now access your profile.");
			} else {
				out.println("Invalid username/password");
				RequestDispatcher rd = req.getRequestDispatcher("/login.html");
				rd.include(req, res);
			}
		}
	}
}