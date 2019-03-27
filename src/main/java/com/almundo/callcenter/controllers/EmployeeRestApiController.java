package com.almundo.callcenter.controllers;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.callcenter.beans.Call;
import com.almundo.callcenter.beans.Employee;
import com.almundo.callcenter.dto.EmployeeDTO;
import com.almundo.callcenter.services.rest.CallService;
import com.almundo.callcenter.services.rest.EmployeeService;
import com.almundo.callcenter.util.CallCenterExcepcion;
import com.almundo.callcenter.util.CustomErrorType;

//TODO: Auto-generated Javadoc
/**
* The Class EmployeeRestApiController. This class exposes the REST services to consume 
* information and execute actions of the employees
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
*/
@SuppressWarnings("unused")
@RestController
@RequestMapping("/api")
public class EmployeeRestApiController {
	
	public static final Logger logger = LoggerFactory.getLogger(EmployeeRestApiController.class);

	@Autowired
	EmployeeService employeeService; 
	
	/**
	 * Get employee by idEmployee 
	 *
	 * @param id long           
	 * @return EmployeeDTO employeeDTO
	 */
	
	@GetMapping(value="/employee/{id}")
	@ResponseBody
	public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("id") long id) {
		
		logger.info("Fetching  Employee ID: {}", id);
		
		EmployeeDTO employee = null;
		try {
			employee = employeeService.findById(id);
			if (employee == null) {
	            logger.error("Employee with id {} not found.", id);
	            return new ResponseEntity(new CustomErrorType("Employee with id " + id 
	                    + " not found"), HttpStatus.NOT_FOUND);
	        }
		} catch (CallCenterExcepcion e) {
			logger.error(e.getMessage());
			return new ResponseEntity(new CustomErrorType(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<EmployeeDTO>(employee, HttpStatus.OK);
        
	}
	
	/**
	 * Get ALL employees 
	 *           
	 * @return List EmployeeDTO
	 */
	@GetMapping(value="/employees")
	@ResponseBody
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
		
		logger.info("Fetching All Employees");
		
		List<EmployeeDTO> employeesDTO = null;
		try {	
			employeesDTO = employeeService.findAll();
	        if (employeesDTO == null) {
	            logger.error("Employees not found.");
	            return new ResponseEntity(new CustomErrorType("Employees " 
	                    + " not found"), HttpStatus.NOT_FOUND);
	        }
		} catch (CallCenterExcepcion e) {
			logger.error(e.getMessage());
			return new ResponseEntity(new CustomErrorType(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<EmployeeDTO>>(employeesDTO, HttpStatus.OK);
        
	}

}
