package com.chase;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<h1>Result</h1>");

		double n1 = Double.parseDouble(req.getParameter("first_number"));
		double n2 = Double.parseDouble(req.getParameter("second_number"));
		String operation = req.getParameter("operation");
		double result;
		
		if(operation.equals("add")) {
			result = n1 + n2;
		} else if(operation.equals("subtract")) {
			result = n1 - n2;
		} else if(operation.equals("multiply")) {
			result = n1 * n2;
		}else if(operation.equals("divide") && n2 != 0) {
			result = n1 / n2;
		}else {
			result = 0;
			out.print("<html><body><h1>No operation selected!</h1></body><html>");
		}
		
		out.print("<html><body><h1>Result: " + result + "</h1></body><html>");
	}
}
