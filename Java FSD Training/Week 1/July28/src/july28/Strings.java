package july28;

public class Strings {

	public static void main(String[] args) {
		char[] str1 = {'c', 'h', 'a', 'r', ' ', 'a', 'r', 'r', 'a', 'y'};
		String str2 = "String literal";
		String str3 = new String(str1);
		String str4 = new String("New String object");
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3 + " converted to String");
		System.out.println(str4);
		
		System.out.println("");
		
		//String methods
		System.out.println("str1.equals(str3) returns: " + str1.equals(str3));
		System.out.println("str2.concat(str4) returns: " + str2.concat(str4));
		System.out.println("str4.contains(\"String\") returns: " + str4.contains("String"));
		System.out.println("str2.isEmpty() returns: " + str2.isEmpty());
		System.out.println("str4.substring(4) returns: " + str4.substring(4));
		System.out.println("str4.split(\" \")[1] returns: " + str4.split(" ")[1]);
		
		System.out.println("");
		
		//String comparison
		String str5 = "HelloWorld";
		String str6 = "Hello";
		String str7 = "World";
		System.out.println("str5 == str6+str7 returns: " + (str5 == (str6+str7)));
		System.out.println("str5.equals(str6+str7) returns: " + str5.equals(str6+str7));
		System.out.println("str5.compareTo(str6+str7) returns: " + str5.compareTo(str6+str7));
		
		System.out.println("");
		
		//String Buffer
		StringBuffer buf = new StringBuffer("This is a String Buffer");
		System.out.println(buf);
		buf.append(", it can be appended to.");
		System.out.println(buf);
		buf.insert(10, "(just gonna slide in here) ");
		System.out.println(buf);
		buf.replace(10, 36, "[removed]");
		System.out.println(buf);
		buf.reverse();
		System.out.println(buf);
		buf.delete(0, 12);
		System.out.println(buf);
		System.out.println("Capacity: " + buf.capacity());
		
		System.out.println("");
		
		//String Builder
		StringBuilder bld = new StringBuilder("This is a String Builder");
		System.out.println(bld);
		bld.append(", it can also be appended to.");
		System.out.println(bld);
		bld.insert(9, "n inserted string into a");
		System.out.println(bld);
		bld.replace(9, 34, " replaced string in a ");
		System.out.println(bld);
		bld.reverse();
		System.out.println(bld);
		bld.delete(10, 20);
		System.out.println(bld);
		System.out.println("Capacity: " + bld.capacity());
	}
}
