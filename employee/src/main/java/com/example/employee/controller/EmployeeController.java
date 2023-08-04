package com.example.employee.controller;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.employee.dao.EmployeeDao;
import com.example.employee.model.Employee;
import com.google.gson.Gson;


@Controller
public class EmployeeController { 
	 
	@Autowired
	private EmployeeDao employeeDao;
	private String result1; 
	
	@PostMapping("/saveData")    
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		
		      String str="result1";
		float salary = employee.getSalary();
		String formattedSalary = String.format("%.2f", salary);
		System.out.println(formattedSalary);
		System.out.println(result1);
		
		
		int check = employeeDao.saveEmployee(employee);   
		
		 
		 
		if(check == 1)  
		{
			System.out.println("Data is Succesufully Inserted"); 
			result1 = "redirect:/";  
		} 
		else { 
			result1= "dataNotFound";  
			System.out.println("Data Insertion is Failed.");  
		}
		
		return result1;
		
	
}	 
	
	

	@GetMapping("/") 
	public String viewHomePage(Model model) { 
		return "index"; 
	}
	
	
	@GetMapping("/employeeRegistrationForm") 
	public String employeeRegistrationForm(Model model) {
		Employee employee=new Employee(); 
		model.addAttribute("employee",employee); 
		return "register_employee"; 
		
	}
	@GetMapping("/listOfRegisteredEmployees")
	public String listOfRegisteredEmployees(Model model) {
		
		List<Employee> listOfEmployees = employeeDao.getAllEmployees(); 
		
		model.addAttribute("listEmployees",listOfEmployees);  
		
		
		return "employees_list"; 
		
	
	}@GetMapping("/backToHome")  
	public String backToHome(Model model) {
		List<Employee> listOfEmployees = employeeDao.getAllEmployeesIds();
		model.addAttribute("listEmployees",listOfEmployees);  
		return "index";
	
		
	   
	}@GetMapping("/addEmployee") 
	public String addEmployee(Model model) {
		List<Employee> listOfEmployees = employeeDao.getAllEmployeesIds(); 
		model.addAttribute("listEmployees",listOfEmployees);  
		return "register_employee";
	
	} 
	
	
	
	@GetMapping("/updateEmployee") 
	public String updateEmployeeId( Model model) {
		//Getting List of Employee Id's
		List<Employee> listOfEmployees = employeeDao.getAllEmployeesIds(); 
		 model.addAttribute("listOfEmployees", listOfEmployees); 
		    return "update_employee"; 	  
	}	

	@PostMapping("/update")
    public String processUpdateForm(@ModelAttribute("employee") Employee updatedEmployee) {
       
    	
    	try {
    		int employee = employeeDao.updateEmployeeDetailsById(updatedEmployee); 
    		
    		if(employee == 1)  
    		{
    			return "redirect:/listOfRegisteredEmployees"; 
    		}
    		else {
    			
    			return "dataNotFound";
    			
    		}
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
       return null;
    
	}
	
	
	

	
	 @ResponseBody
	 @GetMapping("/getEmployeeDataUsingAjax")
	 public String getEmployeeDetailsAjax(@RequestParam Long empId)
	 {
		 Employee employee = employeeDao.getEmployeeById(empId);
		 
		 return new Gson().toJson(employee);
	 }
	 
	 @GetMapping("/deleteEmployee")  
		public String deleteEmployee(Model model) {
			List<Employee> listOfEmployees = employeeDao.getAllEmployeesIds(); 
			model.addAttribute("deleteEmployee",listOfEmployees);   
			return "delete_employee"; 
		}
	 
	 
	 	@ResponseBody
	 	@PostMapping("/deleteEmployeeById")
		public String deleteEmployee(@RequestParam Long empId) {
		  
		 
			int deleteEmployee = employeeDao.deleteEmployeeById(empId); 
			
			
			return new Gson().toJson(deleteEmployee); 
		}

	 
		
	 
		
}

