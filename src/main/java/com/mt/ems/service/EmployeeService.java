package com.mt.ems.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mt.ems.entity.Employee;
import com.mt.ems.exception.EmployeeNotFoundException;

@Service
public interface EmployeeService {

	public Employee addEmployee(Employee employee);

	public List<Employee> getAllEmployee();

	public Employee getEmployeeById(int id) throws EmployeeNotFoundException;

	public Employee updateEmployee(Employee emp);

	public void deleteEmployeeById(int id) throws EmployeeNotFoundException;

	public void deleteAllEmployee() throws EmployeeNotFoundException;
	
	public void generateExcelData() throws IOException;
	
	public Employee getEmployeeByName(String name) throws EmployeeNotFoundException;
}
