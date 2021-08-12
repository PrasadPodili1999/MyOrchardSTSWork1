package com.mt.ems.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emp")
public class Employee implements Comparable<Employee>,Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "EMP_NAME")
	private String name;
	
	@Column(name = "EMP_SAL")
	private double salary;
	
	@Column(name = "EMP_DEPT")
	private String dept;


	@Override
	public int compareTo(Employee o) {
		
		int compare=name.compareTo(o.name);
		if(compare==0)
		{
			compare=Double.compare(salary, o.salary);
		}
		return compare;
	}
	
	
}
