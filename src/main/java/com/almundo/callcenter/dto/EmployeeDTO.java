package com.almundo.callcenter.dto;

import com.almundo.callcenter.beans.EmployeeStatus;

/**
* The EmployeeDTO.
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
* 
*/
public class EmployeeDTO {
	
	/** The id of Employee. */
	private long idEmployee;
	
	/** The name. */
	private String name;
	
	/** The surname. */
	private String surname;
	
	/** The age. */
	private int age;
	
	/** The married. */
	private boolean married;
	
	/** The employee status. */
	private EmployeeStatus employeeStatus;
	
	/** The prioridad. */
	private Integer prioridad;
	
	/** The salary. */
	private double salary;

	/** number of calls answered by each employee */
	private Integer amountAttendedCalls = 0;

	public long getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(long idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}

	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Integer getAmountAttendedCalls() {
		return amountAttendedCalls;
	}

	public void setAmountAttendedCalls(Integer amountAttendedCalls) {
		this.amountAttendedCalls = amountAttendedCalls;
	}

}
