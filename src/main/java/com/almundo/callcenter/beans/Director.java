/*
 * 
 */
package com.almundo.callcenter.beans;

import org.apache.commons.lang3.Validate;

// TODO: Auto-generated Javadoc
/**
 * The Class Director.
 * 
 * @author Marcelo Daniel Martini
 * @version 1.0
 * @since 23/03/2019
 * 
 */
public class Director extends Employee{

	/**
	 * Instantiates a new director.
	 *
	 * @param idEmployee long
	 * @param name String
	 * @param surname String
	 * @param age Integer
	 * @param married Boolean
	 * @param salary Double
	 * 
	 */
	public Director(long idEmployee, String name, String surname, int age, boolean married,
			double salary) {
		super(idEmployee, name, surname, age, married, salary);
		Validate.notNull(idEmployee);
		Validate.notNull(name);
		Validate.notNull(surname);
		Validate.notNull(age);
		Validate.notNull(married);
		Validate.notNull(salary);
		this.setPrioridad(3);
	}
	
	/**
	 * Get Empleoyee Type Director
	 *
	 * @return EmployeeType
	 * 
	 */
	public EmployeeType getEmployeeType(){
		return EmployeeType.DIRECTOR;
	}

	/**
	 * Order By Priotiy 3 = Director
	 *
	 * @return int 
	 * 
	 */
	@Override
	public int compareTo(Employee employee) {
		return this.getPrioridad().compareTo(employee.getPrioridad());
	}

}
