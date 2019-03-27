/*
 * 
 */
package com.almundo.callcenter.beans;

import org.apache.commons.lang3.Validate;

// TODO: Auto-generated Javadoc
/**
 * The Class Client.
 * 
 * @author Marcelo Daniel Martini
 * @version 1.0
 * @since 23/03/2019
 * 
 */
public class Client {
	
	/** The name. */
	private String name;
	
	/** The surname. */
	private String surname;

	/** The age. */
	private int age;
	
	/** The dni. */
	private int dni;
	
	/**
	 * Instantiates a new client.
	 *
	 * @param name String
	 * @param surname String 
	 * @param age Integer 
	 * @param dni Integer 
	 * 
	 */
	public Client(String name, String surname, Integer age, Integer dni) {
		Validate.notNull(name);
		Validate.notNull(surname);
		Validate.notNull(age);
		Validate.notNull(dni);
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.dni = dni;
	}

	/**
	 * Gets the name.
	 *
	 * @return String 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name String
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the surname.
	 *
	 * @return String
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname String 
	 * 
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the age.
	 *
	 * @return age Integer
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 *
	 * @param age Integer
	 * 
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets the dni.
	 *
	 * @return dni Integer
	 */
	public int getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni Integer
	 */
	public void setDni(int dni) {
		this.dni = dni;
	}

}
