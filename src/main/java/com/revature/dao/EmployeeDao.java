package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.bankProject.employee.Employee;



public interface EmployeeDao {
	
	public void createEmployee(Employee flashCard);
	
	public Employee retrieveEmployeeById(String id);
	
	public ArrayList<Employee> retrieveAllEmployee();
	
	public void updateEmployee(Employee flashCard);
	
	
	
	public void createEmployeePreparedStmt(Employee flashCard);

}
