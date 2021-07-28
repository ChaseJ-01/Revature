package july28;

import java.io.*;

public class Exceptions{
	
	public static void main(String[] args) {
		int n = -1;
		
		//Custom Exception
		try {
			System.out.println(checkInt(n));
		} catch (NegativeNumberException e) {
			e.printStackTrace();
		} finally {
			System.out.println("This is the finally block.");
		}
		
		System.out.println("This is after the try-catch-finally block");
		
		//Array Out of Bounds
		int[] arr = new int[3];
		try {
			arr[3] = 3;
		} catch (ArrayIndexOutOfBoundsException e1) {
			e1.printStackTrace();
		} finally {
			System.out.println("Array Out of Bounds Finally Block");
		}
		
		//File not Found
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("data.txt"));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
			System.out.println("This try-catch does not have a finally block.");
		}
		
		//Try with Resources
		try (InputStream is = new FileInputStream("./doesnotexist.txt")){
			int s = is.read();
		} catch(IOException e) {}
		
	}
	
	public static int checkInt(int n) throws NegativeNumberException{
		if(n < 0) {
			throw new NegativeNumberException();
		}
		
		return n;
	}
}