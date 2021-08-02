
public class Casting {
	public static void main(String[] args) {
		double d = 1021928732.357173912;
		byte b = 1;
		short s = b;
		int i = s;
		long l = i;
		float f = l;
		double lf = f;
		
		System.out.println("Byte: " + b);
		System.out.println("Short: " + s);
		System.out.println("Integer: " + i);
		System.out.println("Long: " + l);
		System.out.println("Float: " + f);
		System.out.println("Double: " + lf);
		
		System.out.println("\nDouble: " + d);
		System.out.println("Float: " + (float) d);
		System.out.println("Long: " + (long) d);
		System.out.println("Integer: " + (int) d);
		System.out.println("Short: " + (short) d);
		System.out.println("Byte: " + (byte) d);
		
		Integer integer = new Integer(12);
		System.out.println(integer + " was constructed as an object");
	}
}
