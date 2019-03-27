package com.almundo.callcenter.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.callcenter.services.rest.CallCenterService;
import com.almundo.callcenter.util.CallCenterExcepcion;
import com.almundo.callcenter.util.CustomErrorType;

//TODO: Auto-generated Javadoc
/**
* The Class CallCenterRestApiController. This class exposes the REST services to consume 
* information and execute actions of the callCenter
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
* 
*/
@RestController
@RequestMapping("/api")
public class CallCenterRestApiController {
	
	public static final Logger logger = LoggerFactory.getLogger(CallCenterRestApiController.class);
	
	@Autowired
	CallCenterService callCenterService; 
	
	/**
	 * Start CallCenter, caganado to all employees and initiating all components
	 *
	 * @return HttpStatus.OK or HttpStatus.INTERNAL_SERVER_ERROR
	 */
	@PostMapping(value="/start")
	@ResponseBody
	public ResponseEntity<Object> startCallCenter() {
		Boolean isStartedCallCenter;
		try {
			isStartedCallCenter = callCenterService.startCallCenter();
			if (isStartedCallCenter)
				return new ResponseEntity<>("Active Call Center Status",HttpStatus.OK);
		} catch (CallCenterExcepcion e) {
			logger.error(e.getMessage());
			return new ResponseEntity(new CustomErrorType(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new CustomErrorType(new CallCenterExcepcion(1).getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Stop CallCenter
	 *
	 * @return HttpStatus.OK or HttpStatus.INTERNAL_SERVER_ERROR
	 */
	@PostMapping(value="/stop")
	@ResponseBody
	public ResponseEntity<Object> stopCallCenter() {
		Boolean isStopedCallCenter = callCenterService.stopCallCenter();
		if (isStopedCallCenter)
			return new ResponseEntity<>("Call Center Status Stopped",HttpStatus.OK);
		return new ResponseEntity<>(new CustomErrorType(new CallCenterExcepcion(1).getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Get s call 
	 *
	 * @return callsDTO List CallDTO 
	 */
	@GetMapping(value="/status")
	@ResponseBody
	public ResponseEntity<Object> statusCallCenter() {
		if (callCenterService.statusCallCenter())
			return new ResponseEntity("Active Call Center Status",HttpStatus.OK);
		return new ResponseEntity("Call Center Status Stopped",HttpStatus.OK);
	}
}
