package com.example.employee.model;

public class Employee {

	private Long id;
	private String employeeName; 
	private Float salary;
	
	private long count;
	
	
	public Employee() {
		super(); 
		// TODO Auto-generated constructor stub
	}
	
				
	
	public Employee(Long id, String employeeName, Float salary) {
		super();
		this.id = id;
		this.employeeName = employeeName;  
		this.salary = salary; 
	}
	 
	
	public long getCount() {
		return count; 
	}

	public void setCount(long count) {
		this.count = count;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float result1) {
		this.salary = result1;
	}

}
