import java.util.InputMismatchException;
import java.util.Scanner;

public class Converter {
	
	public static void main(String[] args) {
		int menuSelection = 0;
		double unitInput = 0.0;
		double unitOutput = 0.0;
		Scanner scanner = new Scanner(System.in);
		
		while(menuSelection != 6) {
			
			System.out.println("-----");
			
			System.out.println("Please select a conversion option:");
			System.out.println("     1: Miles to Kilometers");
			System.out.println("     2: Gallons to Liters");
			System.out.println("     3: Fahrenheit to Celsius");
			System.out.println("     4: Pounds to Newtons");
			System.out.println("     5: PSI to Pascals");
			System.out.println("     6: Quit");
			
			menuSelection = scanner.nextInt();
			
			switch(menuSelection) {
				case 1: {
					System.out.println("Please input amount of miles.");
					break;
				}
				case 2: {
					System.out.println("Please input amount of gallons.");
					break;
				}
				case 3: {
					System.out.println("Please input amount of degrees Fahrenheit.");
					break;
				}
				case 4: {
					System.out.println("Please input amount of pounds.");
					break;
				}
				case 5: {
					System.out.println("Please input amount of PSI.");
					break;
				}
				default: {
					System.out.println("Command not recognized, please type the number of the option you would like.");
				}
				case 6: {
					continue;
				}
			}
			
			unitInput = scanner.nextDouble();
			
			switch(menuSelection) {
				case 1: {
					unitOutput = unitInput * 1.60934;
					System.out.println(unitInput + " miles is " + unitOutput + " kilometers.");
					break;
				}
				case 2: {
					unitOutput = unitInput * 3.78541;
					System.out.println(unitInput + " gallons is " + unitOutput + " liters.");
					break;
				}
				case 3: {
					unitOutput = (unitInput - 32) * (5.0/9.0);
					System.out.println(unitInput + " degrees Fahrenheit is " + unitOutput + " degrees Celcius.");
					break;
				}
				case 4: {
					unitOutput = unitInput * 4.44822;
					System.out.println(unitInput + " pounds is " + unitOutput + " Newtons.");
					break;
				}
				case 5: {
					unitOutput = unitInput * 6894.76;
					System.out.println(unitInput + " PSI is " + unitOutput + " Pascals.");
					break;
				}
				default: {
					System.out.println("Not a valid number. Please enter a real number.");
				}
				case 6: {
					continue;
				}
			}
		}
	}
}
