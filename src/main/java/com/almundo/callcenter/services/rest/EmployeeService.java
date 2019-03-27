package com.almundo.callcenter.services.rest;

import java.util.List;

import com.almundo.callcenter.dto.EmployeeDTO;
import com.almundo.callcenter.util.CallCenterExcepcion;

//TODO: Auto-generated Javadoc
/**
* Interface EmployeeService
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
* 
*/
public interface EmployeeService {
	
	/**
	 * findById
	 * 
	 * @param id long
	 * @return EmployeeDTO
	 * @throws CallCenterExcepcion business exception
	 */
	EmployeeDTO findById(long id) throws CallCenterExcepcion;
	
	/**
	 * findAll
	 * 
	 * @return List EmployeeDTO 
	 * @throws CallCenterExcepcion business exception
	 */
	List<EmployeeDTO> findAll() throws CallCenterExcepcion;

}
