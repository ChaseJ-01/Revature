package com.chase;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class MyServlet implements Servlet{
	ServletConfig config = null;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Servlet Destroyed");
	}

	@Override
	public ServletConfig getServletConfig() {
		return this.config;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		this.config = config;
		
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("Service called...");
		arg1.setContentType("text/html");
		
		PrintWriter out = arg1.getWriter();
		out.print("<html><body>");
		out.print("<h1>Hello World!</h1>");
		out.print("</body></html>");
	}

}
