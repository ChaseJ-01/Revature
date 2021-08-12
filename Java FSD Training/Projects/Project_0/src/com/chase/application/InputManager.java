package com.chase.application;

import java.util.*;

/*
 * This class handles all user inputs. I quite like this because it could be modified to automatically parse input as well.
 * Overall, I think this might be something I would use in future projects, as it makes input collection very clean and reliable.
 * Unfortunately, I did not modify String inputs to be friendly with the database. My poor users table is vulnerable to SQL injections.
 */

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
			} catch (InputMismatchException e) {
				error = true;
				System.out.print("\nInvalid input, please try again: ");
				scanner.nextLine();
				continue;
			}
			error = false;
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
			} catch (InputMismatchException e) {
				error = true;
				System.out.print("\nInvalid input, please try again: ");
				scanner.nextLine();
				continue;
			}
			error = false;
		}
		
		return output;
	}
	
	public String getString() {
		return scanner.nextLine();
	}
}
