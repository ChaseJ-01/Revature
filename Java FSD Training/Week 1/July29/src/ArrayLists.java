
import java.util.*;

public class ArrayLists {

	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		//Add elements
		for(int i=0;i<10;i++) {
			al.add(i);
		}
		System.out.println(al.toString());
		
		//Create Iterator
		Iterator<Integer> it = al.iterator();
		
		//Print using iterator
		int j = 0;
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}

		al.set(6, 24);
		System.out.println("\nIndex 6: " + al.get(6));
		System.out.println(al.toString());
		
		//Sort
		Collections.sort(al);
		System.out.println(al.toString());
		
		//Size
		System.out.println(al.size());
		
		//ArrayLists can also take objects as types
		
		//forEach
		for(int i : al) {
			System.out.println(i);
		}
		
		ArrayList<Integer> al2 = new ArrayList<Integer>();
		for(int i=0;i<10;i++) {
			al2.add(i+10);
		}
		
		al.addAll(5, al2);
		System.out.println(al.toString());
	}
}
