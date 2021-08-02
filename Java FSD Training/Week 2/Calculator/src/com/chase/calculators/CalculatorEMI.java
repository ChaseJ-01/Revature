package com.chase.calculators;

public class CalculatorEMI {
	public static double EMI(double amount, double roi, int tenure) {
		return (amount + (amount*roi*(double)tenure))/(tenure*12); 
	}
}
