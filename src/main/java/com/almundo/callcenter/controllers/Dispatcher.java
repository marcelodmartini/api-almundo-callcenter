/*
 * 
 */
package com.almundo.callcenter.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.almundo.callcenter.beans.Call;
import com.almundo.callcenter.beans.CallStatus;
import com.almundo.callcenter.beans.Constants;
import com.almundo.callcenter.beans.Employee;
import com.almundo.callcenter.beans.EmployeeStatus;
import com.almundo.callcenter.dto.CallDTO;
import com.almundo.callcenter.dto.EmployeeDTO;


// TODO: Auto-generated Javadoc
/**
 * 
 * The Dispatcher class is the Call Center that is responsible for handling the call 
 * allocation to call center employees.This class is a thread which is uncovered by 
 * other threads that are called through the exposing method called dispatchCall
 * 
 * 
 * @author Marcelo Daniel Martini
 * @version 1.0
 * @since 23/03/2019
 * 
 * 
 * 
 */
public class Dispatcher implements Runnable {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(Dispatcher.class);

    /** The executor service call. 
     *  The Thread Pool pattern helps to save resources in a multithreaded application, 
     *  and also to contain the parallelism in certain predefined limits. When you use a thread pool, you write 
     *  your concurrent code in the form of parallel tasks and submit them for execution to an instance 
     *  of a thread pool. This instance controls several re-used threads for executing these tasks.
     *  The pattern allows you to control the number of threads the application is creating, their lifecycle, 
     *  as well as to schedule tasks’ execution and keep incoming tasks in a queue.
     *  In this way we can have 10 concurrent calls (threads) in our callCenter (Dispacht), waiting to be attended by 
     *  an employee. In case of having more than 10 concurrent calls, the surplus of calls comes to be queued in 
     *  the queue (BlockingQueue) that ThreadPoolExecutor handles, so that then the calls can be taken from that 
     *  queue and be able to be assigned and be attended by an employee
     * */
    private ThreadPoolExecutor executorServiceCall;

    /** The employees queue. 
     * Employees will be assigned to a PriorityBlockingQueue queue.
	 * This queue handles the priority of pasting, so that the first to leave will be the operators, then the supervisors 
	 * and, finally, if no one is available, the directors.It is a concurrent queue so that a call can wait for an employee 
	 * to be all busy, and also take concurrently for several calls at the same time, one employee at a time, managing 
	 * the concurrency and the block of it.
     * */
    private PriorityBlockingQueue<Employee> employeesQueue;
    
    
    /**
     * This queryCallsDTO list is used to query from the REST services, call information without affecting the running 
     * queue PriorityBlockingQueue <Call>, the queryCallsDTO list is kept up to date and synchronized with PriorityBlockingQueue 
     * <Call> that is running on threads
     */
    private List<CallDTO> queryCallsDTO =  new ArrayList<CallDTO>();
    
    /**
     * This queryEmployeesDTO list is used to query from the REST services, employee information without affecting the running 
     * queue PriorityBlockingQueue <Employee>, the queryEmployeesDTO list is kept up to date and synchronized with 
     * PriorityBlockingQueue <Employee> that is running on threads
     */
    private List<EmployeeDTO> queryEmployeesDTO = new ArrayList<EmployeeDTO>();
    
    /** The is active. Indicates if the CallCenter is active or not to answer calls*/
    private Boolean isActive;
    
    /** 
     * The Dispatcher (CallCenter) assigns a unique call ID to each call
     * */
    private AtomicLong callId = new AtomicLong(0);

     /** Instantiates a new dispatcher.
      * The callCenter is created, the queue is created for the calls with the maximum number of concurrent calls to attend 
      * and the queue of the employees that will be available to attend with their priority hierarchy
	 */
    public Dispatcher() {
        this.executorServiceCall = (ThreadPoolExecutor) Executors.newFixedThreadPool(Constants.MAX_THREADS);
        this.employeesQueue = new PriorityBlockingQueue<>();
    }
    
	/**
	 * Get callID
	 * 
	 * @return AtomicLong callId
	 */
	public synchronized long getCallId() {
		//increase the number of the call or ticket for the next call
		this.callId.getAndIncrement();
		return callId.longValue()-1;
	}
	
	public List<CallDTO> getQueryCallsDTO() {
		return queryCallsDTO;
	}

	public void setQueryCallsDTO(List<CallDTO> queryCallsDTO) {
		this.queryCallsDTO = queryCallsDTO;
	}

	public List<EmployeeDTO> getQueryEmployeesDTO() {
		return queryEmployeesDTO;
	}

	public void setQueryEmployeesDTO(List<EmployeeDTO> queryEmployeesDTO) {
		this.queryEmployeesDTO = queryEmployeesDTO;
	}

     /** Load employees supervisory operators and directors in the callCenter, that is, in the PriorityBlockingQueue queue
	 *
	 * @param employees Employees
	 *            
	 */
    public synchronized void initializeDispatcher(PriorityBlockingQueue<Employee> employees) {
        this.employeesQueue = employees;
        Iterator<Employee> iteratorEmployee = this.employeesQueue.iterator();
		while (iteratorEmployee.hasNext()) { 
			Employee employeeIterator = iteratorEmployee.next();
			this.queryEmployeesDTO.add(Employee.convertEmployeeToEmployeeDTO(employeeIterator));
		}
    }
    
     /** Stop the CallCenter. Initiates an orderly shutdown in which previously submitted calls were executed, 
      * but no new calls will be accepted. Invocation has no additional effect if already shut down..
	 */
    public synchronized void stopDispatcher() {
        this.isActive = false;
        this.executorServiceCall.shutdown();
        logger.info("Stop the CallCenter");
        logger.info("----------------------------------------------------------------------------------------------------------------------------");
    }

     /** Returns if the CallCenter is active
	 *
	 * @return Boolean isActive
	 */
    public Boolean getActive() {
        return isActive;
    }
    

	/** Return employees Queue
	 * 
	 * @return PriorityBlockingQueue Employee employeesQueue
	 */
	public PriorityBlockingQueue<Employee> getEmployeesQueue() {
		return employeesQueue;
	}

	/** Set employees Queue
	 * 
	 * @param employeesQueue PriorityBlockingQueue Employee
	 */
	public void setEmployeesQueue(PriorityBlockingQueue<Employee> employeesQueue) {
		this.employeesQueue = employeesQueue;
	}
    
	/**
	 *  Returns the queue of calls found in the ThreadPoolExecutor.
	 *
	 * @return ThreadPoolExecutor executorServiceCall
	 */
	public synchronized ThreadPoolExecutor getExecutorServiceCall() {
		return executorServiceCall;
	}

	/**
	 * Sets the queue of calls found in the ThreadPoolExecutor.
	 *
	 * @param executorServiceCall ThreadPoolExecutor
	 */
	public synchronized void setExecutorServiceCall(ThreadPoolExecutor executorServiceCall) {
		this.executorServiceCall = executorServiceCall;
	}

	 /**
     * It starts running the Dispatcher (CallCenter)
     */
    @Override
    public void run() {
    	this.isActive = true;
    	logger.info("The Dispatcher (CallCenter) is running");
    	logger.info("----------------------------------------------------------------------------------------------------------------------------");
    }
    
    
    /**
     * syncProductiveEmpoyeeQueueWithQueryQueue:
     * 
     * This queryEmployeesDTO list is used to query from the REST services, employee information without affecting the running 
     * queue PriorityBlockingQueue Employee, the queryEmployeesDTO list is kept up to date and synchronized with 
     * PriorityBlockingQueue Employee that is running on threads
     * 
     * @param employee Employee
     */
    public void syncProductiveEmpoyeeQueueWithQueryQueue(Employee employee){
    	//I synchronize the productive employee queue with the query queue
		Optional<EmployeeDTO> matchingEmployeeQuery = queryEmployeesDTO.stream().
	    filter(e -> e.getIdEmployee() == employee.getIdEmployee()).findFirst();
		if (matchingEmployeeQuery.isPresent()){
			queryEmployeesDTO.remove(matchingEmployeeQuery.get());
			queryEmployeesDTO.add(Employee.convertEmployeeToEmployeeDTO(employee));
		}
    }
    
    /**
     * syncProductiveCallQueueWithQueryCall: 
     * This queryCallsDTO list is used to query from the REST services, call information without affecting the running 
     * queue PriorityBlockingQueue Call, the queryCallsDTO list is kept up to date and synchronized with PriorityBlockingQueue 
     * Call that is running on threads
     *
     * @param call Call
     */
    public synchronized void syncProductiveCallQueueWithQueryCall(Call call){
    	//I synchronize the productive employee queue with the query queue
		Optional<CallDTO> matchingCallQuery = queryCallsDTO.stream().
	    filter(c -> c.getIdCall().longValue() == call.getIdCall().longValue()).findFirst();
		if (matchingCallQuery.isPresent()){
			queryCallsDTO.remove(matchingCallQuery.get());
			queryCallsDTO.add(Call.convertCallToCallDTO(call));
		}
    }
    
     /** This method dispachtCall, looks for if there is a free employee to be assigned to said call (thread) that has incocado to this method.
	 * If there is an available employee, we will take from the employee queue PriorityBlockingQueue to a employee with the method take 
	 * (Retrieve and remove the head of this queue, waiting if necessary until an element becomes available.) This means in case there is no employee 
	 * available the call will wait (thread) to reappear an employee in the queue and be available.
	 *
	 * @param call Call 
	 *    
	 */
    public void dispatchCall(Call call) {
    	
			try {
				while (getActive()) {
					logger.info("The Call number: " + call.getIdCall() + " is waiting to be attended");
					logger.info("----------------------------------------------------------------------------------------------------------------------------");
			    	
					//Retrieves and removes the head of this queue, waiting if necessary until an Employee becomes available.
					Employee employee = this.employeesQueue.take();
					if (employee == null) {
			            continue;
			        }
					//Change Employee AVAILABLE TO BUZY
					employee.setEmployeeStatus(EmployeeStatus.BUSY);
					syncProductiveEmpoyeeQueueWithQueryQueue(employee);
					
					call.setCallStatus(CallStatus.ACTIVE);
					//Salve Call Status into List query Call
					syncProductiveCallQueueWithQueryCall(call);
					
					//Empoyee attend call of client
					employee.attend(call);
					
					//Salve Call Status into List query Call
					syncProductiveCallQueueWithQueryCall(call);
					
					//Change Employee BUZY TO AVAILABLE
					syncProductiveEmpoyeeQueueWithQueryQueue(employee);
					
					//Inserts the specified element into this priority queue.
					this.employeesQueue.add(employee);
					return;
				}
			} catch (InterruptedException e1) {
				Thread.currentThread().interrupt();
				logger.error("The call fail: DISCONNECTED");
				call.setCallStatus(CallStatus.DISCONNECTED);
				//Salve Call Status into List query Call
				syncProductiveCallQueueWithQueryCall(call);
				logger.info("----------------------------------------------------------------------------------------------------------------------------");
			}
    }
}
