package com.example.employee.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.employee.model.Employee;

@Repository
public class EmployeeData {
	
	@Autowired
	JdbcTemplate jdbcTemplate;     
	
	 
	
	public long getEmployeeCount() { 
		try 
		{
		String sql="select max(emp_id) from employee;";

		return jdbcTemplate.queryForObject(sql, long.class);
				
		} catch (Exception e) {
			e.printStackTrace();
			return 0; 
		} 
	}
 


	public Optional<Employee> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

 

	public void deleteById() {
		// TODO Auto-generated method stub
		
	}

}
