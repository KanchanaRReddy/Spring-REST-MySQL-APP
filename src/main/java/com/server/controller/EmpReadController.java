package com.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.entity.Employee;
import com.server.exceptions.customExceptions.DatabaseException;
import com.server.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Read Employee",description = "Provides API's to read Employees")
@RestController
@RequestMapping(value= {"/read"})
public class EmpReadController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping(value= {"/get/{id}"},produces= {"application/json","application/xml"})
	@ApiOperation(value = "Find Employee by Id", notes = "Provide an id to look up a specific record from emploees",
	response = Employee.class)
	public ResponseEntity<Employee> getEmp(@PathVariable int id) throws DatabaseException {
		Employee obj=service.getEmpById(id);
		return new ResponseEntity<Employee>(obj,HttpStatus.OK);
	}
	
	@GetMapping(value= {"/getAll"},produces= {"application/json","application/xml"})
	@ApiOperation(value = "Find All the Employee", notes = "Provides all the employee records")
	public ResponseEntity<List<Employee>> getAllEmp() throws DatabaseException  {
		List<Employee> list=service.getAllEmp();
		return new ResponseEntity<List<Employee>>(list,HttpStatus.OK);
	}
	
	@GetMapping(value= {"/count"})
	@ApiOperation(value = "Find the number of Employees")
	public ResponseEntity<String> getCount() throws DatabaseException {
		int count=service.getCount();
		String msg="Employees Count :- "+count;
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
}
