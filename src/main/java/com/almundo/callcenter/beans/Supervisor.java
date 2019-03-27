/*
 * 
 */
package com.almundo.callcenter.beans;

import org.apache.commons.lang3.Validate;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new supervisor.
 * 
 * @author Marcelo Daniel Martini
 * @version 1.0
 * @since 23/03/2019
 * 
 */
public class Supervisor extends Employee{

	/**
	 * Instantiates a new supervisor.
	 *
	 * @param idEmployee long
	 * @param name String
	 * @param surname String
	 * @param age Integer
	 * @param married Boolean
	 * @param salary Double
	 * 
	 */
	public Supervisor(long idEmployee, String name, String surname, int age, boolean married,
			double salary) {
		super(idEmployee, name, surname, age, married, salary);
		Validate.notNull(idEmployee);
		Validate.notNull(name);
		Validate.notNull(surname);
		Validate.notNull(age);
		Validate.notNull(married);
		Validate.notNull(salary);
		this.setPrioridad(2);
	}
	
	/**
	 * Get Empleoyee Type Supervisor
	 *
	 * @return EmployeeType.SUPERVISOR
	 * 
	 */
	public EmployeeType getEmployeeType(){
		return EmployeeType.SUPERVISOR;
	}

	/**
	 * Order By Priotiy 2 = Supervisor
	 *
	 * @return int 
	 * 
	 */
	@Override
	public int compareTo(Employee employee) {
		return this.getPrioridad().compareTo(employee.getPrioridad());
	}
}
