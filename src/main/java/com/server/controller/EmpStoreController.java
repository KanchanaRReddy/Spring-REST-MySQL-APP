package com.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.server.entity.Employee;
import com.server.exceptions.customExceptions.DatabaseException;
import com.server.service.EmployeeService;

import io.swagger.annotations.Api;

@Api(tags = "Store Employee",description = "Provides API's to store Employees")
@RestController
@RequestMapping("/store")
public class EmpStoreController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping(value={"/","/home"})
	@ResponseBody
	public String getHome() {
		return "<h2><center>Welcome to the Homepage..</center></h2>";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST,consumes="application/json",
			produces= {"application/json","application/xml"})
	public ResponseEntity<Employee> saveEmp(@Valid @RequestBody Employee emp) throws DatabaseException{
		Employee savedEmp=service.saveEmp(emp);
		return new ResponseEntity<Employee>(savedEmp,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmp(@PathVariable int id) throws DatabaseException{
		String msg=service.deleteEmp(id);
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.PUT,consumes="application/json",
			produces={"application/json","application/xml"})
	public ResponseEntity<Employee> updateEmp(@RequestBody ObjectNode obj) throws DatabaseException{
		int id=obj.get("id").intValue();
		float salary=obj.get("salary").floatValue();
		Employee updatedEmp=service.updateEmp(id, salary);
		return new ResponseEntity<Employee>(updatedEmp,HttpStatus.ACCEPTED);
	}
}
