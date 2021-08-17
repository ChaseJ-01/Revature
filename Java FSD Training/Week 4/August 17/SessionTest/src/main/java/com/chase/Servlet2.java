package com.chase;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servlet2 extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		try(PrintWriter out = res.getWriter()){
			out.println("<!DOCTYPE html><HTML><Head><Title>Servlet Test</Title></Head><Body>");
			
			Cookie[] cookies = req.getCookies();
			boolean flag = false;
			String name = "";
			if(cookies == null) {
				out.println("<h1>You are a new user.</h1>");
			} else {
				for(Cookie c : cookies) {
					String cname = c.getName();
					if(cname.equals("c_user_name")) {
						flag = true;
						name = c.getValue();
					}
				}
			}
			if(flag) {
				out.println("<h1>Welcome back, " + name + "</h1>");
				out.println("</Body></HTML>");
			}
		}
	}
}
