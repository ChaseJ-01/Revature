package com.chase;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SessionServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		try(PrintWriter out = res.getWriter()){
			out.print("<!DOCTYPE html><html><head><meta charset=\"ISO-8859-1\"><title>Servlet Test</title></head><body><h1>");
			String name = req.getParameter("user_name");
			out.print("Name: " + name);
			out.print("</h1><h1><a href='servlet2'>Go to servlet 2</a></h1>");
			out.print("</body></html>");
			Cookie c = new Cookie("c_user_name", name);
			res.addCookie(c);
		}
	}
}