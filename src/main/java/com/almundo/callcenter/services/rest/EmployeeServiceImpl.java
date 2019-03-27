package com.almundo.callcenter.services.rest;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.almundo.callcenter.beans.Employee;
import com.almundo.callcenter.dto.CallDTO;
import com.almundo.callcenter.dto.EmployeeDTO;
import com.almundo.callcenter.util.CallCenterExcepcion;

//TODO: Auto-generated Javadoc
/**
* The Class EmployeeServiceImpl. This class exposes the REST services to consume 
* information and execute actions of the employees
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
*/
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public EmployeeDTO findById(long id) throws CallCenterExcepcion{
		
		//The iterator of PriorityBlockingQueue is weakly consistent. Meaning, it will not guarantee to reflect 
		//any modifications after it is constructed, while iterating. Although, it guarantees the elements retrieval, 
		//that remained during construction. In that regards, it is safe to iterate. The returned iterator is weakly consistent.
		if (CallCenterServiceImpl.dispatcher != null){
			Optional<EmployeeDTO> matchingEmployeeQuery = CallCenterServiceImpl.dispatcher.getQueryEmployeesDTO().stream().
				    filter(e -> e.getIdEmployee() == id).findFirst();
			return ((!matchingEmployeeQuery.isPresent()) ? null : matchingEmployeeQuery.get());
		}else{
			throw new CallCenterExcepcion(1);
		}
	}

	@Override
	public List<EmployeeDTO> findAll() throws CallCenterExcepcion{
			//The iterator of PriorityBlockingQueue is weakly consistent. Meaning, it will not guarantee to reflect 
			//any modifications after it is constructed, while iterating. Although, it guarantees the elements retrieval, 
			//that remained during construction. In that regards, it is safe to iterate. The returned iterator is weakly consistent.
			if (CallCenterServiceImpl.dispatcher != null){
				return CallCenterServiceImpl.dispatcher.getQueryEmployeesDTO();
			}else{
				throw new CallCenterExcepcion(1);
			}
	}
}
