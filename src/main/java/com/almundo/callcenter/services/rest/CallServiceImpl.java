package com.almundo.callcenter.services.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.almundo.callcenter.beans.Call;
import com.almundo.callcenter.dto.CallDTO;
import com.almundo.callcenter.util.CallCenterExcepcion;

//TODO: Auto-generated Javadoc
/**
* The Class CallServiceImpl. This class exposes the REST services to consume 
* information and execute actions of the calls
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
*/
@Service("callService")
public class CallServiceImpl implements CallService{
	
	
	public CallDTO registerCall(CallDTO callDTO) throws CallCenterExcepcion{
		
		if (CallCenterServiceImpl.dispatcher != null){
			Call call = new Call(CallCenterServiceImpl.dispatcher.getCallId(),null,null,callDTO.getClient(),null,null,null);
			//The call sees the dispatcher because she is in charge of calling it through the dispatchCall method, 
			//simulating the call by phone that the customer dials the call center number and presses the call 
			//button on the telephone
			call.setDispatcher(CallCenterServiceImpl.dispatcher);
			//Calls will be invoked through the ThreadPoolExecutor available to the Dispatcher, to control 
			//the number of calls (threads) that can be handled by the Dispatcher and to handle in a queue 
			//the number of concurrent calls
			CallCenterServiceImpl.dispatcher.getExecutorServiceCall().execute(call);
			callDTO.setCallStatus(call.getCallStatus());
			callDTO.setCreationDate(call.getCreationDate());
			callDTO.setIdCall(call.getIdCall());
			return callDTO;
		}else{
			throw new CallCenterExcepcion(1);
		}
	}

	@Override
	public CallDTO finById(long id) throws CallCenterExcepcion{
		if (CallCenterServiceImpl.dispatcher != null){
			Optional<CallDTO> matchingCallQuery = CallCenterServiceImpl.dispatcher.getQueryCallsDTO().stream().
				    filter(c -> c.getIdCall() == id).findFirst();
			return ((!matchingCallQuery.isPresent()) ? null : matchingCallQuery.get());
		}else{
			throw new CallCenterExcepcion(1);
		}
	}

	@Override
	public List<CallDTO> findAll() throws CallCenterExcepcion{
		if (CallCenterServiceImpl.dispatcher != null){
			return CallCenterServiceImpl.dispatcher.getQueryCallsDTO();
		}else{
			throw new CallCenterExcepcion(1);
		}
	}
	
}
