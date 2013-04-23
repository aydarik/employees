package ru.aydarik.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.aydarik.client.EmployeeService;
import ru.aydarik.shared.EmployeeBean;

import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("serial")
public class EmployeeServiceImpl extends RemoteServiceServlet implements EmployeeService {

	public void addEmployee(EmployeeBean employee) throws SQLException {
		if (employee.getId() == null) {
			EmployeeDAO.saveEmployee(employee);
		} else {
			EmployeeDAO.updateEmployee(employee);
		}
	}

	public EmployeeBean getEmployee(Long id) throws SQLException {
		if (id == null) {
			return null;
		}

		return EmployeeDAO.getEmployee(id);
	}

	public List<EmployeeBean> getEmployees(int start, int size) throws SQLException {
		return EmployeeDAO.getEmployees(start, size);
	}

	public Number getSize() throws SQLException {
		return EmployeeDAO.getSize();
	}

	public void removeEmployee(Long id) throws SQLException {
		EmployeeDAO.deleteEmployee(id);
	}
}
