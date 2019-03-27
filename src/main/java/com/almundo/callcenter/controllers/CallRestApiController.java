/*
 * 
 */
package com.almundo.callcenter.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.callcenter.dto.CallDTO;
import com.almundo.callcenter.services.rest.CallService;
import com.almundo.callcenter.util.CallCenterExcepcion;
import com.almundo.callcenter.util.CustomErrorType;


// TODO: Auto-generated Javadoc
/**
 * The Class CallRestApiController. This class exposes the REST services to consume 
 * information and execute actions of the calls
 * 
 * @author Marcelo Daniel Martini
 * @version 1.0
 * @since 23/03/2019
 */
@RestController
@RequestMapping("/api")
public class CallRestApiController {
	
	public static final Logger logger = LoggerFactory.getLogger(CallRestApiController.class);

	@Autowired
	CallService callService; 
	
	/**
	 * Register call.
	 *
	 * @param callDTO CallDTO           
	 * @return callDTO
	 * 
	 * Example JSON for Register Call
	 * 
	 * {
	 *		"client": {
	 *			"name": "Marcelo",
	 *			"surname": "Martini",
	 *			"age": 32,
	 *			"dni": 2324234
	 *		}
	 *	}
	 * 
	 */
	@PostMapping(value="/call")
	@ResponseBody
	public ResponseEntity<CallDTO> registerCall(@RequestBody CallDTO callDTO) {
		
		logger.info("Register Call : {}", callDTO);
		if (!CallDTO.validate(callDTO)){
			logger.error("Unable to register Call. Invalid format field");
			return new ResponseEntity(new CustomErrorType("Unable to register Call. Invalid format field"),HttpStatus.BAD_REQUEST);
		}
		try {
			callDTO = callService.registerCall(callDTO);
			if (callDTO == null){
				logger.error("Call not Register");
				return new ResponseEntity(new CustomErrorType("Call not Register"),HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (CallCenterExcepcion e) {
			logger.error(e.getMessage());
			return new ResponseEntity(new CustomErrorType(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CallDTO>(callDTO, HttpStatus.CREATED);
        
	}
	
	/**
	 * Get call by IdCall
	 *
	 * @param  id long
	 * @return CallDTO callDTO
	 */
	@GetMapping(value="/call/{id}")
	@ResponseBody
	public ResponseEntity<CallDTO> getCall(@PathVariable("id") long id) {
		
		logger.info("Fetching Call ID: {}", id);
		CallDTO callDTO = null;
		try {
			callDTO = callService.finById(id);
			if (callDTO == null) {
	            logger.error("Call with id {} not found.", id);
	            return new ResponseEntity(new CustomErrorType("Call with id " + id 
	                    + " not found"), HttpStatus.NOT_FOUND);
	        }
		} catch (CallCenterExcepcion e) {
			logger.error(e.getMessage());
			return new ResponseEntity(new CustomErrorType(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<CallDTO>(callDTO, HttpStatus.OK);
	}
	
	/**
	 * Get all call 
	 *
	 * @return callsDTO List CallDTO 
	 */
	@GetMapping(value="/calls")
	@ResponseBody
	public ResponseEntity<List<CallDTO>> getAllCall() {
		
		logger.info("Fetching All Calls");
		
		List<CallDTO> callsDTO;
		try {
			callsDTO = callService.findAll();
	        if (callsDTO == null) {
	            logger.error("Calls not found.");
	            return new ResponseEntity(new CustomErrorType("Calls " 
	                    + " not found"), HttpStatus.NOT_FOUND);
	        }
		} catch (CallCenterExcepcion e) {
			logger.error(e.getMessage());
			return new ResponseEntity(new CustomErrorType(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<List<CallDTO>>(callsDTO, HttpStatus.OK);
        
	}


}