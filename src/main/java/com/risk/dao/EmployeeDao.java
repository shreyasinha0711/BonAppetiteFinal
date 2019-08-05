package com.risk.dao;
import java.util.List;
import com.risk.model.Employee;

public interface EmployeeDao {
	
	void saveEmployee(Employee employee);

	public List<Employee> getActiveEmployee(String email); 
	
	public boolean checkLogin(String email, String password);

}
