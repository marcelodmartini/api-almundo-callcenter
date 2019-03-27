package com.almundo.callcenter.services.rest;

import java.util.List;

import com.almundo.callcenter.dto.CallDTO;
import com.almundo.callcenter.util.CallCenterExcepcion;

//TODO: Auto-generated Javadoc
/**
* Interface CallService
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
* 
*/
public interface CallService {
	
	/**
	 * registerCall
	 * 
	 * @param callDTO CallDTO
	 * @return CallDTO
	 * @throws CallCenterExcepcion business exception
	 */
	CallDTO registerCall(CallDTO callDTO) throws CallCenterExcepcion;

	/**
	 * finById
	 * 
	 * @param id long
	 * @return CallDTO
	 * @throws CallCenterExcepcion business exception
	 */
	CallDTO finById(long id) throws CallCenterExcepcion;

	/**
	 * findAll
	 * 
	 * @return List CallDTO
	 * @throws CallCenterExcepcion business exception
	 */
	List<CallDTO> findAll() throws CallCenterExcepcion;

}
