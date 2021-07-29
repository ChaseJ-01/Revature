import java.util.*;

public class Queues {

	public static void main(String[] args) {
		Deque<Integer> dq = new ArrayDeque<Integer>();
		
		for(int i=0;i<10;i++) {
			dq.add(i);
		}
		System.out.println(dq);
		dq.push(12);
		System.out.println(dq.peek());
		dq.pop();
		System.out.println(dq.peek());
		System.out.println(dq.peekLast());
		System.out.println(dq);
		dq.poll();
		System.out.println(dq);
		dq.removeLast();
		System.out.println(dq);
		
	}
}
