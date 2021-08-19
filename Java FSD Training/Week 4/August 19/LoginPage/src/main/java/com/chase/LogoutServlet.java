package com.chase;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		try(PrintWriter out = res.getWriter()){
			Cookie[] cookies = req.getCookies();
			req.getRequestDispatcher("/index.html").include(req, res);
			
			if(cookies == null) {
				out.println("You are already logged out.");
			} else {
				res.addCookie(new Cookie("user_name", ""));
				out.println("You have been logged out.");
			}
		}
	}
}
