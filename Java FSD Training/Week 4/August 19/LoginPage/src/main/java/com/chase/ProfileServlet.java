package com.chase;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProfileServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/html");
		try(PrintWriter out = res.getWriter()){
			
			Cookie[] cookies = req.getCookies();
			
			req.getRequestDispatcher("/index.html").include(req, res);
			
			if(cookies == null) {
				out.println("Please log in to view profile.");
			} else {
				String username = null;
				
				for(Cookie c : cookies) {
					if(c.getName().equals("user_name")) {
						username = c.getValue();
						break;
					}
				}
				
				if(username != null && !username.equals("")) {
					out.print("Welcome, " + username);
				} else {
					out.println("Please log in to view profile.");
				}
			}
		}
	}
}
