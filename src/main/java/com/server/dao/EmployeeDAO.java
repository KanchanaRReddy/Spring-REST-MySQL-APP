package com.server.dao;

import org.springframework.data.repository.CrudRepository;

import com.server.entity.Employee;

public interface EmployeeDAO extends CrudRepository<Employee, Integer> {

}
