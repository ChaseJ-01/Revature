
public class EmployeeDAOFactory {
	private static EmployeeDAO dao = null;
	
	private EmployeeDAOFactory() {
		
	}
	
	public static EmployeeDAO getEmployeeDAO() {
		if(dao==null) {
			dao = new EmployeeDAOImpl();
		}
		return dao;
	}
}
