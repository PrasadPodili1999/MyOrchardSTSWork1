package com.mt.ems.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mt.ems.entity.Employee;
import com.mt.ems.exception.EmployeeNotFoundException;
import com.mt.ems.service.EmployeeService;
//import com.sun.tools.sjavac.Log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ems")
@EnableCaching
@CacheConfig(cacheNames = "employees")
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee emp = empService.addEmployee(employee);
		return ResponseEntity.status(HttpStatus.OK).body(emp);
	}

	@Cacheable( value = "employees",key = "#id")
	@RequestMapping(value = "/getEmployee/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(empService.getEmployeeById(id));
		} catch (RuntimeException e) {
			log.error("Employee Not available" + e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@RequestMapping(value = "/getEmployee", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> empList = empService.getAllEmployee();
		return ResponseEntity.status(HttpStatus.OK).body(empList);
	}

	@CachePut( value = "employees",key = "#emp.id")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp) {
		Employee employee = empService.updateEmployee(emp);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	@CacheEvict( value = "employees",allEntries = true)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEmployeeById(@PathVariable("id") int id) throws EmployeeNotFoundException {
		try {
			empService.deleteEmployeeById(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (RuntimeException e) {
			log.error(e + ",there is no such employee with id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}

	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAllEmployee() throws EmployeeNotFoundException {
		try {
			empService.deleteAllEmployee();
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (RuntimeException e) {
			log.error(e + ",there is no employees present in ur database");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	public ResponseEntity<Void> generateExcelData() {
		try {
			empService.generateExcelData();
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (IOException e) {
			log.error("something wrong happened");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Cacheable(value = "employees", key = "#name")
	@RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmpByName(@PathVariable("name") String name) {
		Employee emp = empService.getEmployeeByName(name);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}
}
