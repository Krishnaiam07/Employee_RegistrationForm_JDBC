package com.example.employee.dao;

import java.util.List;

import com.example.employee.model.Employee;

public interface EmployeeDao {
		

	
	
	public List<Employee> getAllEmployeesIds();  
	
	Employee getEmployeeById(Long id);
   
	int saveEmployee(Employee employee);
    

	public int updateEmployeeDetailsById(Employee emp); 

	public int deleteEmployeeById(long id);

	List<Employee> getAllEmployees();

	
}




	


