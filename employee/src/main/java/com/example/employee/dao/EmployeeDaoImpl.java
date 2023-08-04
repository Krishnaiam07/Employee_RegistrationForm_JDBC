package com.example.employee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;  
 
import com.example.employee.model.Employee;
import com.example.employee.util.EmployeeData;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Autowired
	private EmployeeData employeeData;

	
	
	@Override
	public int saveEmployee(Employee employee) {
	int save=0;
	
	
	
	
	try 
	{
		String queryInsert =  "INSERT INTO employee(emp_id, emp_name, emp_sal, insert_date) " 
				
				+ " values (:emp_id, :emp_name,:emp_sal, now())";
		
		MapSqlParameterSource map = new MapSqlParameterSource();

		//map.addValue("emp_id", employee.getId());
		
		
		//Getting Max Employee ID from employee table
		Long count = employeeData.getEmployeeCount();
		
		//Increase the count to employee ID and Insert  ++count 
		map.addValue("emp_id", ++count);
		map.addValue("emp_name", employee.getEmployeeName());
		map.addValue("emp_sal", employee.getSalary()); 

		//After Successful insert data,,,Save will become 1
		save = namedParameterJdbcTemplate.update(queryInsert, map);
 
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				save = 0;  
			}
	

			return save;
	}

	
	
	@Override
public List<Employee> getAllEmployeesIds(){
	try {
		String sql = "SELECT emp_id FROM employee ORDER BY emp_id ASC;";
		List<Employee> eList= jdbcTemplate.query(sql, new org.springframework.jdbc.core.RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int rownumber) throws SQLException {
				
				
				Employee emp = new Employee();
				
				
				
				
				emp.setId(rs.getLong("emp_id"));
				return emp;
			}
			
		});
		return eList;  
	}catch (Exception e) {  
		e.printStackTrace();  
	}
	return null;
	}

    
	@Override
    	public List<Employee> getAllEmployees(){
    		try {
    			String sql = "SELECT * FROM employee ORDER BY emp_id ASC;";
    			List<Employee> list= jdbcTemplate.query(sql, new org.springframework.jdbc.core.RowMapper<Employee>() {
    				public Employee mapRow(ResultSet rs, int rownumber) throws SQLException {
    					Employee emp = new Employee();
    					emp.setId(rs.getLong("emp_id"));
    					emp.setEmployeeName(rs.getString("emp_name"));
    					emp.setSalary(rs.getFloat("emp_sal"));
    					return emp; 
    				}
    				
    			});
    			return list;  
    		}catch (Exception e) {  
    			e.printStackTrace(); 
    		} 
    		return null;
    		
    		}
	
	@SuppressWarnings("deprecation") 
	@Override
	public Employee getEmployeeById(Long empId) { 
		try {
			String sql = "select emp_id, emp_name, emp_sal from employee where emp_id=?";
			
			Employee emp = new Employee();
			return jdbcTemplate.query(sql, new Object[] {empId}, new ResultSetExtractor<Employee>() {
				public Employee extractData(ResultSet rs) throws SQLException, DataAccessException
				{
					if(rs.next())
					{
						emp.setId(Long.valueOf(rs.getString("emp_id")));
						emp.setEmployeeName(rs.getString("emp_name").trim());
						emp.setSalary(Float.valueOf(rs.getString("emp_sal")));
						 
						return emp;
					}
					return emp;
				}
			}); 
		}
		catch(Exception e) 
		{
			e.printStackTrace();   
		}
		return null;
	}
 
 
	
	 @Override
	 public int updateEmployeeDetailsById(Employee emp) { 
			 
		int update=0;
		try{
		        
			String sql = "UPDATE employee SET emp_name=:emp_name, "
		        		+ " emp_sal=:emp_sal, insert_date=now() where emp_id=:emp_id";
		    
	    MapSqlParameterSource map = new MapSqlParameterSource();

			  	map.addValue("emp_id", emp.getId());  
			  	map.addValue("emp_name", emp.getEmployeeName()); 
			  	map.addValue("emp_sal", emp.getSalary()); 
			  	
	            update = namedParameterJdbcTemplate.update(sql, map); //1
		       }
			catch (Exception e) {
				e.printStackTrace();
				update=0;
			}
			return update;
	
	 }

	 @Override
	    public int deleteEmployeeById(long empId) {
	        String sql = "DELETE FROM employee where emp_id=?";
	        int delete=0;
	        try {
	        	delete=jdbcTemplate.update(sql,new Object[] {empId});
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	        return delete;
	    }



	
	}