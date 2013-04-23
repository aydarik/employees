package ru.aydarik.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.aydarik.shared.EmployeeBean;

import java.util.List;

@RemoteServiceRelativePath("employee")
public interface EmployeeService extends RemoteService {

	void addEmployee(EmployeeBean employee) throws Exception;

	EmployeeBean getEmployee(Long id) throws Exception;

	List<EmployeeBean> getEmployees(int start, int size) throws Exception;

	Number getSize() throws Exception;

	void removeEmployee(Long id) throws Exception;
}
