package com.mt.ems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mt.ems.entity.Employee;
import com.mt.ems.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class EmployeeRepositoryTest {

	@MockBean
	EmployeeRepository repo;
	
	@Test
	public void testAddEmployee()
	{
		Employee emp=new Employee();
		emp.setId(1);
		when(repo.save(emp)).thenReturn(emp);
		Employee emp1=repo.save(emp);
		assertEquals(1, emp1.getId());
	}
	
	@Test
	public void testGetAllEmployee()
	{
		Employee emp1=new Employee(1,"smith", 9898, "ui");
		Employee emp2=new Employee(2, "fizo", 8798, "lpx");
		List<Employee> empList=new ArrayList<Employee>();
		empList.add(emp1);
		empList.add(emp2);
		when(repo.findAll()).thenReturn(empList);
		List<Employee> result=(List<Employee>)repo.findAll();
		assertEquals(2, result.size());
	}
	
	@Test
	public void testGetEmployeeById()
	{
		Optional<Employee> emp=Optional.of(new Employee());
		emp.get().setId(1);
		when(repo.findById(1)).thenReturn(emp);
		Employee employee=repo.findById(1).get();
		verify(repo).findById(1);
		assertEquals(1, employee.getId());
	}
	
	@Test
	public void testUpdateEmployee()
	{
		Employee emp=new Employee(1,"smith", 9898, "ui");
		when(repo.save(emp)).thenReturn(emp);
		Employee emp1=repo.save(emp);
		assertEquals(1, emp1.getId());
	}
	
	@Test
	public void testDeleteEmployeeById()
	{
		Employee emp=new Employee(1,"smith", 9898, "ui");
		repo.deleteById(emp.getId());
		verify(repo).deleteById(emp.getId());
	}
	
	@Test
	public void testDeleteAllEmployee()
	{
		Employee emp1=new Employee(1,"smith", 9898, "ui");
		Employee emp2=new Employee(2, "fizo", 8798, "lpx");
		List<Employee> empList=new ArrayList<Employee>();
		empList.add(emp1);
		empList.add(emp2);
		repo.deleteAll();
		verify(repo).deleteAll();
		
	}
}
