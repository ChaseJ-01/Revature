import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{
	private static Statement statement = null;
	private Connection connection = null;
	
	public EmployeeDAOImpl() {
		try {
			this.connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addEmployee(Employee employee) throws SQLException {
		String command = "INSERT INTO employees (name, email) VALUES (?,?)";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setString(1, employee.getName());
		ps.setString(2, employee.getEmail());
		if(ps.executeUpdate() > 0) {
			System.out.println("Employee saved successfully.");
		} else {
			System.out.println("A problem was encountered while trying to save employee.");
		}
	}

	@Override
	public void updateEmployee(Employee employee) throws SQLException {
		String command = "UPDATE employees SET name = ?, email = ? WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setString(1, employee.getName());
		ps.setString(2, employee.getEmail());
		ps.setInt(3, employee.getID());
		if(ps.executeUpdate() > 0) {
			System.out.println("Employee updated successfully.");
		} else {
			System.out.println("A problem was encountered while trying to update employee.");
		}
	}

	@Override
	public void deleteEmployee(int id) throws SQLException {
		String command = "DELETE FROM employees WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setInt(1, id);
		if(ps.executeUpdate() > 0) {
			System.out.println("Employee removed successfully.");
		} else {
			System.out.println("A problem was encountered while trying to remove employee.");
		}
	}

	@Override
	public List<Employee> getEmployees() throws SQLException {
		List<Employee> list = new ArrayList<Employee>();
		
		String command = "SELECT * FROM employees";
		ResultSet rs = statement.executeQuery(command);
		while(rs.next()) {
			list.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		return list;
	}

	@Override
	public Employee getEmployeeByID(int id) throws SQLException {
		String command = "SELECT * FROM employees WHERE id = ?";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3));
	}
}
