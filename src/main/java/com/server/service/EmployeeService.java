package com.server.service;

import java.util.List;

import com.server.entity.Employee;
import com.server.exceptions.customExceptions.DatabaseException;

public interface EmployeeService {
	
	public Employee saveEmp(Employee emp) throws DatabaseException;
	
	public String deleteEmp(int id)throws DatabaseException;
	
	public Employee updateEmp(int id,float salary)throws DatabaseException;
	
	public Employee getEmpById(int id)throws DatabaseException;
	
	public List<Employee> getAllEmp()throws DatabaseException;
	
	public int getCount() throws DatabaseException;

}
