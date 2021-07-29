package practiceProblems;
import java.util.*;

public class ArrayListPractice {

	public static void main(String[] args) {
		
		//1. Write a java program to create a new array list, add some colors, and print out the collection.
		List<String> colors = new ArrayList<String>();
		
		colors.add("Red");
		colors.add("Blue");
		colors.add("Yellow");
		
		System.out.println(colors + "\n");
		
		//2. Write a Java program to iterate through all elements in an array list.
		for(String s : colors) {
			System.out.println(s);
		}
		
		//3. [Method call]
		addFirst(colors, "Orange");
		System.out.println("\n" + colors);
		
		//4. Write a Java program to retrieve an element at a specified index from a given array list
		System.out.println(colors.get(2));
		
		//5. Write a Java program to update a specific array element by a given element (assuming Array List)
		colors.set(1, "Maroon");
		System.out.println(colors);
		
		//6. Write a Java program to remove the third element from an array list.
		colors.remove(2);
		System.out.println(colors);
		
		//7. Write a Java program to search an element in an array list.
		System.out.println(colors.indexOf("Yellow"));
		
		//8. Write a Java program to sort a given array list.
		Collections.sort(colors);
		System.out.println(colors);
		
		//9. Write a Java program to copy one array list into another.
		List<String> colors2 = new ArrayList<String>();
		colors2.add("Green");
		colors2.add("Purple");
		colors.addAll(colors2);
		System.out.println(colors);
		
		//10. [Method call]
		shuffle(colors);
		System.out.println(colors);
		
		//11. Write a Java program to reverse elements in an array list
		for(int i=0; i<(colors.size()/2);i++) {
			swap(colors, i, colors.size() - i - 1);
		}
		System.out.println(colors);
		
		//12. [Method call]
		List<String> colors3 = new ArrayList<String>();
		colors3 = extract(colors, 1, 3);
		System.out.println(colors3);
		
		//13. [Method call]
		swap(colors, 1, 2);
		System.out.println(colors);
	}
	
	//3. Write a Java program to insert an element into the array list at the first position.
	public static void addFirst(List<String> arrList, String s) {
		//Add last element to the array again
		arrList.add(arrList.get(arrList.size()-1));
		//Move all elements forward by one
		for(int i=arrList.size()-2;i>0;i--) {
			arrList.set(i, arrList.get(i-1));
		}
		//Add string s to first position
		arrList.set(0, s);
	}
	
	//10. Write a Java program to shuffle elements in an array list.
	public static void shuffle(List<String> arrList) {
		int index = 0;
		//Swap a random element with the first one, then a random element with the second, and so on...
		while (arrList.size()-index > 0) {
			swap(arrList, index, (int) (Math.random()*(arrList.size()-index) + index));
			index++;
		}
	}
	
	//12. Write a Java program to extract a portion of an array list
	public static List<String> extract(List<String> arrList, int index1, int index2){
		List<String> arr = new ArrayList<String>();
		
		for(int i = index1; i <= index2; i++) {
			arr.add(arrList.get(i));
		}
		return arr;
	}
	
	//13. Write a Java program to swap two elements in an array list.
	public static void swap(List<String> arrList, int index1, int index2) {
		String temp = arrList.get(index1);
		arrList.set(index1, arrList.get(index2));
		arrList.set(index2, temp);
	}
}
