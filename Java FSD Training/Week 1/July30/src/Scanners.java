import java.util.*;

public class Scanners {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String output = new String();
		
		System.out.println("Please input some text");
		output = scn.nextLine();
		System.out.println("You typed: " + output);
	}
}
