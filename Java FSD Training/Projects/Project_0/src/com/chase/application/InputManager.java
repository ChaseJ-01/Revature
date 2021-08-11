package com.chase.application;

import java.util.*;

public class InputManager {
	//Singleton class - there only needs to be one manager.
	Scanner scanner = new Scanner(System.in);
	private static InputManager manager = null;
	
	private InputManager() {
		return;
	}
	
	public static InputManager getInputManager() {
		if(manager == null) {
			manager = new InputManager();
		}
		return manager;
	}
	
	public void flush() {
		while(scanner.hasNext()) {
			scanner.next();
		}
	}
	
	public void destroy() {
		scanner.close();
	}
	
	public int getInt() {
		boolean error = true;
		int output = 0;
		
		while(error) {
			try {
				output = scanner.nextInt();
				scanner.nextLine();
				error = false;
			} catch (InputMismatchException e) {
				error = true;
			}
		}
		
		return output;
	}
	
	public double getDouble() {
		boolean error = true;
		double output = 0;
		
		while(error) {
			try {
				output = scanner.nextDouble();
				scanner.nextLine();
				error = false;
			} catch (InputMismatchException e) {
				error = true;
			}
		}
		
		return output;
	}
	
	public String getString() {
		return scanner.nextLine();
	}
}
