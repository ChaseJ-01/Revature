
public class test implements interfaces {

	@Override
	public void printFingers(int hands) {
		System.out.println(hands * fingers);
	}
	
	public static void main(String[] args) {
		test test = new test();
		
		test.printFingers(4);
	}
	
}
