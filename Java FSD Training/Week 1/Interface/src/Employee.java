
public class Employee {
	private int id;
	private String name;
	private double passMark;
	
	public void setID(int id) throws NegativeException {
		if(id < 0) {
			throw new NegativeException();
		} else {
			this.id = id;
		}
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setName(String name) {
		
	}
}
