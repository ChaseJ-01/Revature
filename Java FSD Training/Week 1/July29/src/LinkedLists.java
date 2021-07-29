import java.util.*;

public class LinkedLists {

	public static void main(String[] args) {
		LinkedList<Integer> ll = new LinkedList<Integer>();
		
		for(int i=0;i<10;i++) {
			ll.add(i);
		}
		System.out.println(ll);
		
		LinkedList<Integer> ll2 = new LinkedList<Integer>();
		for(int i=0;i<10;i++) {
			ll2.add(i+10);
		}
		System.out.println(ll2);
		
		ll.addAll(ll2);
		System.out.println(ll);
		
		ll.addFirst(100);
		System.out.println(ll);
		
		ll.removeLast();
		System.out.println(ll);
		
		ll.removeFirstOccurrence(6);
		System.out.println(ll);
		
		ll.add(6);
		ll.add(7);
		ll.add(6);
		ll.removeLastOccurrence(6);
		System.out.println(ll);
		
	}
}
