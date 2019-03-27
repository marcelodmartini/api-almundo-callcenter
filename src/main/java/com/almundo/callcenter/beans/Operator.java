/*
 * 
 */
package com.almundo.callcenter.beans;

import org.apache.commons.lang3.Validate;

// TODO: Auto-generated Javadoc
/**
 * The Class Operator.
 * 
 * @author Marcelo Daniel Martini
 * @version 1.0
 * @since 23/03/2019
 * 
 */
public class Operator extends Employee{
	
	/**
	 * Instantiates a new operator.
	 *
	 * @param idEmployee long
	 * @param name String
	 * @param surname String
	 * @param age Integer
	 * @param married Boolean
	 * @param salary Double
	 * 
	 */
	public Operator(long idEmployee, String name, String surname, Integer age, Boolean married,
			double salary) {
		super(idEmployee, name, surname, age, married, salary);
		Validate.notNull(idEmployee);
		Validate.notNull(name);
		Validate.notNull(surname);
		Validate.notNull(age);
		Validate.notNull(married);
		Validate.notNull(salary);
		this.setPrioridad(1);
	}

	/**
	 * Get Empleoyee Type Operator
	 *
	 * @return EmployeeType.OPERATOR
	 * 
	 */
	public EmployeeType getEmployeeType(){
		return EmployeeType.OPERATOR;
	}

	/**
	 * Order By Priotiy 1 = Operator
	 *
	 * @return int 
	 * 
	 */
	@Override
	public int compareTo(Employee employee) {
		return this.getPrioridad().compareTo(employee.getPrioridad());
	}
}
