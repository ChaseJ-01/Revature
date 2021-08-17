package com.chase;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<h1>Registration Form Servlet</h1>");
		
		String name = req.getParameter("user_name");
		String email = req.getParameter("user_email");
		String pass = req.getParameter("user_pass");
		String gender = req.getParameter("user_gender");
		String country = req.getParameter("user_country");
		String agreement = req.getParameter("agree");
		if(agreement.equals("checked")) {
			out.println("<h1>Name: " + name + " </h1>");
			out.println("<h1>Email: " + email + " </h1>");
			out.println("<h1>Password: " + pass + " </h1>");
			out.println("<h1>Gender: " + gender + " </h1>");
			out.println("<h1>Country: " + country + " </h1>");
		} else {
			out.println("<h1>Must accept terms and conditions!</h1>");
		}
	}
}
