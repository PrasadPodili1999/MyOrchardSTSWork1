package com.mt.ems;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mt.ems.entity.Employee;
import com.mt.ems.repository.EmployeeRepository;
import com.mt.ems.service.Impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class EmployeeServiceTest {

	@InjectMocks
	EmployeeServiceImpl service;
	
	@MockBean
	EmployeeRepository rep;

	
	@Test
	public void getAllEmpTest()
	{
		Employee emp1=new Employee(1,"smith", 9898, "ui");
		Employee emp2=new Employee(2, "fizo", 8798, "lpx");
		List<Employee> empList=new ArrayList<Employee>();
		empList.add(emp1);
		empList.add(emp2);

		when(rep.findAll()).thenReturn(empList);
		assertEquals(2, service.getAllEmployee().size());
	}
	@Test
	public void testGetEmpById()
	{
		Optional<Employee> emp=Optional.of(new Employee());
		emp.get().setId(1);
		when(rep.findById(1)).thenReturn(emp);
		
		Employee employee=service.getEmployeeById(1);
		
		verify(rep).findById(1);
		assertEquals(1,service.getEmployeeById(1).getId());	
	}

	@Test
	public void testAddEmployee()
	{
		Employee emp=new Employee(1,"smith", 9898, "ui");
		when(rep.save(emp)).thenReturn(emp);
		assertEquals(emp, service.addEmployee(emp));
	}
	
	@Test
	public void testUpdateEmployee()
	{
		Employee emp=new Employee(1,"smith", 9898, "ui");
		when(rep.save(emp)).thenReturn(emp);
		assertEquals(emp, service.updateEmployee(emp));
	}
	
	@Test
	public void testDeleteEmployeeById()
	{
		Optional<Employee> emp=Optional.of(new Employee());
		emp.get().setId(1);
		when(rep.findById(1)).thenReturn(emp);
		Employee employee=service.getEmployeeById(1);
		service.deleteEmployeeById(employee.getId());
		verify(rep).deleteById(employee.getId());
	}
	
	@Test
	public void testDeleteAllEmployee()
	{
		Employee emp1=new Employee(1,"smith", 9898, "ui");
		Employee emp2=new Employee(2, "fizo", 8798, "lpx");
		List<Employee> empList=new ArrayList<Employee>();
		empList.add(emp1);
		empList.add(emp2);

		when(rep.findAll()).thenReturn(empList);

		service.deleteAllEmployee();
		verify(rep).deleteAll();
	}
}
