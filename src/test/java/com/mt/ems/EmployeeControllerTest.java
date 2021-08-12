package com.mt.ems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mt.ems.controller.EmployeeController;
import com.mt.ems.entity.Employee;
import com.mt.ems.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController controller;
	
	@MockBean
	EmployeeService service;
	
	@Test
	public void testAddEmployee()
	{
	
		Employee emp=new Employee();
		emp.setId(1);
		when(service.addEmployee(emp)).thenReturn(emp);
		
		Employee empToAdd=new Employee(1, "jack", 890, "it");
		ResponseEntity<Employee> response=controller.addEmployee(empToAdd);
		
		assertEquals(200,response.getStatusCode().value());
	}
	
	@Test
	public void testGetAllEmployee()
	{
		Employee emp1=new Employee(1, "jack", 890, "it");
		Employee emp2=new Employee(2,"raj",900,"ai");
		List<Employee> list=new ArrayList<>();
		list.add(emp1);
		list.add(emp2);
		when(service.getAllEmployee()).thenReturn(list);
		ResponseEntity<List<Employee>> response=controller.getAllEmployee();
		
		assertEquals(200, response.getStatusCode().value());
		assertEquals(2, response.getBody().size());
	}
	
	@Test
	public void testGetEmployeeById()
	{
		Employee emp=new Employee(1, "jack", 890, "it");
		
		when(service.getEmployeeById(1)).thenReturn(emp);
		
		ResponseEntity<Employee> response=controller.getEmployeeById(1);
		assertEquals(200, response.getStatusCode().value());
		assertEquals("jack", response.getBody().getName());
		assertEquals(1, response.getBody().getId());
	}
	
	@Test
	public void testUpdateEmployee()
	{
		Employee emp=new Employee(1, "jack", 890, "it");
		
		when(service.updateEmployee(emp)).thenReturn(emp);
		ResponseEntity<Employee> response=controller.updateEmployee(emp);
		
		assertEquals(200, response.getStatusCode().value());
		assertEquals(890.0, response.getBody().getSalary());
	}
	
	@Test
	public void testDeleteEmployeeById()
	{
		Employee emp=new Employee();
		emp.setId(1);
		when(service.getEmployeeById(1)).thenReturn(emp);
		service.deleteEmployeeById(emp.getId());
		ResponseEntity<Void> response= controller.deleteEmployeeById(emp.getId());
		
		assertEquals(200, response.getStatusCode().value());
	}
	
	@Test
	public void testDeleteAllEmployee()
	{
		Employee emp1=new Employee(1, "jack", 890, "it");
		Employee emp2=new Employee(2,"raj",900,"ai");
		List<Employee> list=new ArrayList<>();
		list.add(emp1);
		list.add(emp2);
		when(service.getAllEmployee()).thenReturn(list);
		service.deleteAllEmployee();
		ResponseEntity<Void> response=controller.deleteAllEmployee();
		
		assertEquals(200, response.getStatusCode().value());
		
	}
}
