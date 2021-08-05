
public class Employee {
	private int id;
	private String name;
	private String email;
	
	public Employee() {
		
	}
	
	public Employee(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public int getID() {
		return id;
	}
	public void setID(int id) {
		id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
