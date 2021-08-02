package com.chase.main;

import java.util.*;
import com.chase.calculators.*;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please select which calculator you wish to use.");
		System.out.println("\n	1. Basic Calculator\n	2. EMI Calculator");
		
		switch((int) collectInput(scanner)) {
			
			//Basic Calculator
			case 1:{
				System.out.println("Please input first number:");
				double n1 = collectInput(scanner);
				System.out.println("Please input second number:");
				double n2 = collectInput(scanner);
				
				System.out.println("Please select functionality:");
				System.out.println("\n	1. Add\n	2. Subtract\n	3. Multiply\n	4.Divide");
				switch((int) collectInput(scanner)) {
					case 1:{
						System.out.println("Result: " + CalculatorBasic.add(n1, n2));
						break;
					}
					case 2:{
						System.out.println("Result: " + CalculatorBasic.subtract(n1, n2));
						break;
					}
					case 3:{
						System.out.println("Result: " + CalculatorBasic.multiply(n1, n2));
						break;
					}
					case 4:{
						System.out.println("Result: " + CalculatorBasic.divide(n1, n2));
						break;
					}
					default:{
						System.out.println("Invalid selection! Please restart program because I am lazy.");
					}
				}
				return;
			}
			
			//EMI Calculator
			case 2:{
				System.out.println("Please input loan amount:");
				double amount = collectInput(scanner);
				System.out.println("Please input rate of interest (percent):");
				double roi = collectInput(scanner) / 100;
				System.out.println("Please input tenure (years):");
				int tenure = (int) collectInput(scanner);
				System.out.println("Result: " + CalculatorEMI.EMI(amount, roi, tenure));
				return;
			}
			
			//Default
			default:{
				System.out.println("Invalid selection! Please restart program because I am lazy.");
				return;
			}
		}
	}
	
	//Collects user input and returns a usable number
	private static double collectInput(Scanner scanner) {
		boolean error = true;
		
		//Ensure no input mismatch exceptions
		while(error) {
			try {
				error = false;
				return scanner.nextDouble();
			} catch (InputMismatchException e){
				e.printStackTrace();
			}
		}
		return 0;
	}
}
