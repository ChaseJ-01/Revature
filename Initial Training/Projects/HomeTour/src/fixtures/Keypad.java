package fixtures;

import java.util.InputMismatchException;
import java.util.Scanner;
import game.Player;

public class Keypad extends Item {
	private int code;
	
	//I have no idea why this causes the main loop to loop twice.
	@Override
	public void activate(Player player, Scanner scanner) {
		boolean error = true;
		
		if(!this.getActive()) {
			System.out.println("You've already used this!");
			return;
		}
		
		System.out.println("Please enter a numeric code:");
		
		while(error == true) {
			try {
				error = false;
				if(scanner.nextInt() == code) {
					this.unlock();
					this.setActive(false);
					scanner.nextLine();
				} else {
					System.out.println("Incorrect code.");
				}
			} catch(InputMismatchException e) {
				scanner.next();
				error = true;
				System.out.println("Please input a numeric code:");
			}
		}
	}
	
	public void setLockedCode(int code) {
		this.code = code;
	}
}
