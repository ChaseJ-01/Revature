import java.util.*;

public class HashSets {

	public static void main(String[] args) {
		HashSet<Integer> hs = new HashSet<Integer>();
		
		for(int i=0;i<20;i++) {
			hs.add(i%10);
		}
		
		System.out.println(hs);
	}
}
