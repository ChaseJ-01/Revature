
public class Single {
	public static int count = 0;
	private static Single singleton = null;
	
	private Single(){
		System.out.println("Object created. Total count: " + ++count);
	}
	
	public static Single getSingleton() {
		if(singleton == null) {
			singleton = new Single();
		}
		return singleton;
	}
}
