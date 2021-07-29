package practiceProblems;

import java.util.*;

public class HashMapPractice {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		//1. Write a Java program to associate a value with a key in a HashMap.
		map.put(0, "Zero");
		map.put(1, "One");
		map.put(2, "Two");
		System.out.println(map);
		
		//2. Write a Java program to count the number of key-value mappings in a map.
		System.out.println("Size = " + map.size());
		
		//3. Write a Java program to copy all mappings from a specified map to another map.
		Map<Integer, String> map2 = new HashMap<Integer, String>();
		map2.put(3, "Three");
		map2.put(4, "Four");
		map.putAll(map2);
		System.out.println(map);
		
	}
}
