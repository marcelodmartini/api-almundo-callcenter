package com.almundo.callcenter.services.rest;

import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.beans.Constants;
import com.almundo.callcenter.beans.Employee;
import com.almundo.callcenter.controllers.Dispatcher;
import com.almundo.callcenter.util.CallCenterExcepcion;

//TODO: Auto-generated Javadoc
/**
* The Class CallCenterServiceImpl. This class exposes the REST services to consume 
* information and execute actions of the callCenter
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
* 
*/
@Service("callCenterService")
public class CallCenterServiceImpl implements CallCenterService{
	
	public static final Logger logger = LoggerFactory.getLogger(CallCenterServiceImpl.class);
	
	public static Dispatcher dispatcher;

	public Boolean startCallCenter() throws CallCenterExcepcion {
		
        try {
        	//I build the employee queue (PriorityBlockingQueue) indicating the number of operators, supevisors and directors
        	PriorityBlockingQueue<Employee> employeeList = Employee.buildEmployeeList(Constants.AMOUNT_OPERATOR,Constants.AMOUNT_SUPERVISOR,Constants.AMOUNT_DIRECTOR);
        	
        	//Generated the Dispatcher (CallCenter) indicating the number of concurrent calls that the callCenter can attend at the same time (Constants.MAX_THREADS)
        	CallCenterServiceImpl.dispatcher = new Dispatcher();
        	//Charge to employees defined in the CallCenter
        	dispatcher.initializeDispatcher(employeeList);
			TimeUnit.SECONDS.sleep(1);
			//Run the CallCenter that is the Dispatcher thread
	        ThreadPoolExecutor executorService =  (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
	        executorService.execute(dispatcher);
	        TimeUnit.SECONDS.sleep(1);
	        return Boolean.TRUE;
		} catch (InterruptedException e) {
			logger.error("The Call Center not started");
			throw new CallCenterExcepcion(2);
		}
	}
	
	public Boolean stopCallCenter() {
		if (CallCenterServiceImpl.dispatcher.getActive()){
			CallCenterServiceImpl.dispatcher.stopDispatcher();
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean statusCallCenter() {
		if (CallCenterServiceImpl.dispatcher == null)
			return Boolean.FALSE;
		if (CallCenterServiceImpl.dispatcher.getActive()){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
