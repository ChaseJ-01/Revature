
import java.util.*;

public class Maps {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("One", 1);
		map.put("Two", 2);
		map.put("Five", 5);
		
		System.out.println(map.get("Two"));
		
		map.remove("One");
		System.out.println(map);
		
		//maps can contain any type of key pointing to any type of object, even in the same map.
	}
}
