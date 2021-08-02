package com.chase.calculators;

public class CalculatorBasic {
	public static double add(double n1, double n2) {
		return n1+n2;
	}
	
	public static double subtract(double n1, double n2) {
		return n1-n2;
	}
	
	public static double multiply(double n1, double n2) {
		return n1*n2;
	}
	
	public static double divide(double n1, double n2) throws ArithmeticException {
		if(n1 != 0) {
			return n1 / n2;
		} else {
			throw new ArithmeticException("Cannot divide by zero!");
		}
	}
}
