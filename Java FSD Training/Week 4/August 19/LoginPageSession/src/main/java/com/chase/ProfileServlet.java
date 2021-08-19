package com.chase;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ProfileServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		try(PrintWriter out = res.getWriter()){
			
			Cookie[] cookies = req.getCookies();
			
			req.getRequestDispatcher("/index.html").include(req, res);
			
			HttpSession ses = req.getSession(false);
			String username = null;
			
			if(ses != null) {
				username = (String) ses.getAttribute("user_name");
			}
			
			if(username != null && !username.equals("")) {
				out.print("Welcome, " + username);
			} else {
				out.println("Please log in to view profile.");
			}
		}
	}
}