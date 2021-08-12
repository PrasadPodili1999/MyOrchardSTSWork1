package com.mt.ems.service.Impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.ems.entity.Employee;
import com.mt.ems.exception.EmployeeNotFoundException;
import com.mt.ems.repository.EmployeeRepository;
import com.mt.ems.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository empRepo;

	@Override
	public Employee addEmployee(Employee employee) {
		return empRepo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> empList1 =(List<Employee>) empRepo.findAll();
		
		return empList1.stream().sorted()
				.map((emp)->emp).collect(Collectors.toList());
	}

	@Override
	public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
		log.info("getting record from DB");
		Optional<Employee> emp=empRepo.findById(id);
		if(emp.isEmpty())
		{
			throw new EmployeeNotFoundException("Employee with id :"+id+" Not Found");
		}
		
		return emp.get();
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		log.info("updating data in DB");
		return empRepo.save(emp);
	}

	@Override
	public void deleteEmployeeById(int id) throws EmployeeNotFoundException {
		log.info("deleting data in DB");
		if(getEmployeeById(id)!=null)
		{
			empRepo.deleteById(id);
		}
		else
		{
			throw new EmployeeNotFoundException("Employee with id :"+id+" Not Found");
		}
	}

	@Override
	public void deleteAllEmployee() throws EmployeeNotFoundException {
		//if(!getAllEmployee().isEmpty())
		if(getAllEmployee().size()>0)
		{
			empRepo.deleteAll();
		}
		else
		{
			throw new EmployeeNotFoundException("Employees are not available");
		}
	}
	
	public void generateExcelData() throws IOException
	{
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet("emp info");
		List<Employee> empdata=getAllEmployee();
		
		XSSFRow headerRow=sheet.createRow(0);
		XSSFCell cell=headerRow.createCell(0);
		cell.setCellValue("EmpId");
	    cell=headerRow.createCell(1);
		cell.setCellValue("Edept");
		cell=headerRow.createCell(2);
		cell.setCellValue("EName");
		cell=headerRow.createCell(3);
		cell.setCellValue("salary");
		int rowCount=1;
		for(Employee emp:empdata)
		{
			headerRow=sheet.createRow(rowCount++);
			int colCount=0;
			 	cell=headerRow.createCell(colCount++);
				cell.setCellValue((Integer)emp.getId());
			    cell=headerRow.createCell(colCount++);
				cell.setCellValue((String)emp.getDept());
				cell=headerRow.createCell(colCount++);
				cell.setCellValue((String)emp.getName());
				cell=headerRow.createCell(colCount++);
				cell.setCellValue((Double)emp.getSalary());
		}
		String filePath=".\\datafiles\\employee.xlsx";
		FileOutputStream fos=new FileOutputStream(filePath);
		workBook.write(fos);
		fos.close();
		log.info("excel data generated");
	}
	
	public Employee getEmployeeByName(String name) throws EmployeeNotFoundException
	{
		log.info("getting data from DB");
		Employee emp=empRepo.findByName(name);
		if(emp==null)
		{
			throw new EmployeeNotFoundException();
		}
		else
		{
			return emp;
		}
	}
}

