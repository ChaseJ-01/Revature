import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//Class.forName("jdbc:mysql://localhost:3306/revature");
		
		/*Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chase", "root", "root");
		
		Statement statement = conn.createStatement();
		
		ResultSet resultSet = statement.executeQuery("select * from foods");
		while(resultSet.next()) {
			System.out.println(resultSet.getString(2));
		}*/
		
		EmployeeDAO dao = EmployeeDAOFactory.getEmployeeDAO();
		Scanner scanner = new Scanner(System.in);
		List<Employee> eList;
		int input = 0;
		
		while(input != 6) {
			System.out.println("==========");
			System.out.println("Please enter a command:\n");
			System.out.println("	1. Add New Employee\n	2. Remove Employee\n	3. Modify Existing Employee\n	4. List All Employees\n	5. Search Employee by ID\n	6. Quit");
			
			input = scanner.nextInt();
			scanner.nextLine();
			
			switch(input) {
				case 1: {
					System.out.print("Name: ");
					String name = scanner.nextLine();
					System.out.print("Email: ");
					String email = scanner.nextLine();
					dao.addEmployee(new Employee(-1, name, email));
					break;
				}
				case 2:{
					System.out.print("Employee ID: ");
					int id = scanner.nextInt();
					scanner.nextLine();
					dao.deleteEmployee(id);
					break;
				}
				case 3:{
					System.out.print("Employee ID: ");
					int id = scanner.nextInt();
					scanner.nextLine();
					System.out.println("==========");
					printEmployee(dao.getEmployeeByID(id));
					System.out.print("Name: ");
					String name = scanner.nextLine();
					System.out.print("Email: ");
					String email = scanner.nextLine();
					dao.updateEmployee(new Employee(id, name, email));
					break;
				}
				case 4:{
					printList(dao.getEmployees());
					break;
				}
				case 5:{
					System.out.print("Employee ID: ");
					int id = scanner.nextInt();
					scanner.nextLine();
					System.out.println("==========");
					printEmployee(dao.getEmployeeByID(id));
					break;
				}
				case 6:{
					return;
				}
				default:{
					System.out.println("Command not recognized.");
					break;
				}
			}
		}
		
		
		//dao.addEmployee(new Employee(-1, "Derek", "derek@gmail.com"));
		//dao.addEmployee(new Employee(-1, "Greg", "greg@gmail.com"));
		eList = dao.getEmployees();
		printList(eList);
	}
	
	public static void printList(List<Employee> list) {
		System.out.println("==========");
		for(Employee e : list) {
			printEmployee(e);
		}
	}
	
	public static void printEmployee(Employee e) {
		System.out.println(e.getID() + " - Name: " + e.getName() + " - Email: " + e.getEmail());
	}
}
