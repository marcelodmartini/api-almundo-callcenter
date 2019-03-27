/*
 * 
 */
package com.almundo.callcenter.server.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.almundo.callcenter.beans.Call;
import com.almundo.callcenter.beans.Constants;
import com.almundo.callcenter.beans.Employee;
import com.almundo.callcenter.beans.EmployeeStatus;
import com.almundo.callcenter.controllers.Dispatcher;


// TODO: Auto-generated Javadoc
/**
* The Class DispatcherTest. This kind of test tests the attention of concurrent calls with the constants defined in the Constants class
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
*
*/
public class DispatcherTest {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(DispatcherTest.class);

	/**
	* Object responsible for launching processes at the time.
	*/
	private final CountDownLatch callConcurrent = new CountDownLatch(1);
	
	private Boolean isTerminateAllCall = Boolean.TRUE;
	
     /** Test dispatch 10 calls concurrently to employees.
	 *
	 * @throws InterruptedException This method throws InterruptedException if interrupted while sleeping.
	 *             
	 */
    @Test
    public void testDispatchTenCallsToEmployees() throws InterruptedException {
    	
    	logger.info("Start testDispatchTenCallsToEmployees 10 Call concurrent concurrently");
    	
    	
    	//I build the employee queue (PriorityBlockingQueue) indicating the number of operators, supevisors and directors
    	PriorityBlockingQueue<Employee> employeeList = Employee.buildEmployeeList(Constants.AMOUNT_OPERATOR,Constants.AMOUNT_SUPERVISOR,Constants.AMOUNT_DIRECTOR);
    	
    	//I build the call list indicating the number of concurrent calls that the test will have, in addition when it lasts as 
    	//minimum and maximum one call, and indicating that all calls will occur concurrently at the same time
    	List<Call> calls = Call.buildListOfRandomCalls(Constants.CALL_AMOUNT,callConcurrent);
        
    	//Generated the Dispatcher (CallCenter) indicating the number of concurrent calls that the callCenter can attend at the same time (Constants.MAX_THREADS)
    	Dispatcher dispatcher = new Dispatcher();
    	//Charge to employees defined in the CallCenter
    	dispatcher.initializeDispatcher(employeeList);
        TimeUnit.SECONDS.sleep(1);
        
        //Run the CallCenter that is the Dispatcher thread
        ThreadPoolExecutor executorService =  (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        executorService.execute(dispatcher);
        TimeUnit.SECONDS.sleep(1);
        
        //Inoke all calls at the same time, called the callCenter, that is, each call thread will call the dispatchCall method of the Dispatcher
        executeCallsInSameMoment(calls,dispatcher);
        //Put the counter to zero because all calls are running concurrently
        callConcurrent.countDown(); 
        
        //This blocks the thread until all tasks complete their execution or the specified timeout is reached:dispatcher
        while(true){
        	this.isTerminateAllCall = Boolean.TRUE;
        	//Returns the approximate total number of tasks that have completed execution. Because the states of tasks and threads may change 
        	//dynamically during computation, the returned value is only an approximation, but one that does not ever decrease across successive calls.
	        if (Long.valueOf(dispatcher.getExecutorServiceCall().getCompletedTaskCount()).intValue() == Constants.CALL_AMOUNT){
	        	//I'm going to do the second validation to check that there are no busy employees
	        	dispatcher.getQueryEmployeesDTO().forEach(e -> {
	        		if(e.getEmployeeStatus().equals(EmployeeStatus.BUSY)){
	        			isTerminateAllCall = Boolean.FALSE;
	        		}
	        	});
	        	if (isTerminateAllCall){
	        		//If the number of concurrent calls is equal to the number of calls answered by each employee, the test ran correctly
	        		assertEquals(Constants.CALL_AMOUNT,employeeList.stream().mapToInt(employee -> employee.getAttendedCalls().size()).sum());
	        		logger.info("Finish testDispatchTenCallsToEmployees concurrent concurrently");
	        		return;
	        	}
	        }else{
	        	TimeUnit.SECONDS.sleep(1);
	        }
        }
    }
    
    
    
    
     /** Execute calls in same moment, through the ThreadPoolExecutor that the Dispatcher (Call Center) 
	 *
	 * @param calls Calls
	 * @param dispatcher Dispatcher
	 *           
	 **/
    public static void executeCallsInSameMoment(List<Call> calls, Dispatcher dispatcher){
		
    	(calls).forEach(call -> {
	    	 	try {
	    			//The call sees the dispatcher because she is in charge of calling it through the dispatchCall method, 
	    			//simulating the call by phone that the customer dials the call center number and presses the call 
	    			//button on the telephone
	    			call.setDispatcher(dispatcher);
	    			//Calls will be invoked through the ThreadPoolExecutor available to the Dispatcher, to control 
	    			//the number of calls (threads) that can be handled by the Dispatcher and to handle in a queue 
	    			//the number of concurrent calls
		    	 	dispatcher.getExecutorServiceCall().execute(call);
					TimeUnit.SECONDS.sleep(1);
				} catch (Exception e) {
					// Fail test 
					fail();
				}
	      });
    }
    
}
