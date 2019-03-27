package com.almundo.callcenter.services.rest;

import com.almundo.callcenter.util.CallCenterExcepcion;

//TODO: Auto-generated Javadoc
/**
* Interface CallCenterService
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
* 
*/
public interface CallCenterService {
	
	/**
	 * startCallCenter
	 * 
	 * @return Boolean
	 * @throws CallCenterExcepcion business exception
	 */
	Boolean startCallCenter() throws CallCenterExcepcion; 
	
	/**
	 * stopCallCenter
	 * 
	 * @return Boolean
	 */
	Boolean stopCallCenter();

	/**
	 * statusCallCenter
	 * 
	 * @return Boolean
	 */
	Boolean statusCallCenter();

}
