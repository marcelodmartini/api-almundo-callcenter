package com.almundo.callcenter.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.almundo.callcenter.controllers.Dispatcher;
import com.almundo.callcenter.dto.CallDTO;

//TODO: Auto-generated Javadoc
/**
 * 
 * The Call class implements the Runnable interface and the Thread is created by the ThreadPoolExecutor class.
 * This Call class has all the behavior of a call. It is also responsible for calling the dispatchCall method, 
 * so that the Dispatch can assign an employee of the callCenter and attend said call
 * 
 * 
 * @author Marcelo Daniel Martini
 * @version 1.0
 * @since 23/03/2019
 * 
 * 
 * 
 */
public class Call implements Runnable {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(Call.class);
	
	/** The id call. */
	private Long idCall;
	
	/** The message. */
	private String message;
	
	/** The creation date. */
	private LocalDateTime creationDate;
	
	/** The creation date. */
	private LocalDateTime creationDateConversation;
	
	/** The client. */
	private Client client;
	
	/** The employee. */
	private Employee employee;
	
	/** The call status. */
	private CallStatus callStatus = CallStatus.NOT_STARTED;
	
	/** The duration in seconds. */
	private Integer durationInSeconds;
	
	/** The dispatcher. */
	private Dispatcher dispatcher;
	
	/**
	* Object responsible for launching processes at the time. to simulate the concurrency test of all calls at the same time
	*/
	private CountDownLatch callConcurrent;
	

	/**
	 * Instantiates a new call. The call is always generated with a NOT_STARTED status
	 *
	 * @param idCall Long
	 * @param message String
	 * @param creationDate LocalDateTime
	 * @param client ClientTest
	 * @param employee Employee
	 * @param durationInSeconds Integer
	 * @param callConcurrent CountDownLatch
	 * 
	 */
	public Call(Long idCall, String message, LocalDateTime creationDate, Client client,
			Employee employee, Integer durationInSeconds, CountDownLatch callConcurrent) {
		Validate.notNull(client);
		this.message = message;
		this.creationDate = creationDate;
		this.client = client;
		this.employee = employee;
		this.callStatus = CallStatus.NOT_STARTED;
		this.idCall = idCall;
		this.durationInSeconds = durationInSeconds;
		this.callConcurrent = callConcurrent;
	}
	
	/**
	 * Gets the message, returns the conversation of the call
	 *
	 * @return message String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message. the conversation of the call
	 *
	 * @param message String
	 * 
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the creation date. returns the date on which the call began since the call is generated by telephone, 
	 * is when the status call passes NOT_STARTED to WAITING
	 *
	 * @return creationDate LocalDateTime
	 */
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the creation date. set the date on which the call began since the call is generated by telephone, 
	 * is when the status call passes NOT_STARTED to WAITING
	 *
	 * @param creationDate LocalDateTime
	 */
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	
	/**
	 * Gets the creation date conversation. returns the start date of the conversation, 
	 * is when the state call WAITING passes to ACTIVE
	 *
	 * @return creationDateConversation LocalDateTime
	 */
	public LocalDateTime getCreationDateConversation() {
		return creationDateConversation;
	}

	/**
	 * Sets the creation date conversation. set the start date of the conversation, 
	 * is when the state call WAITING passes to ACTIVE
	 *
	 * @param creationDateConversation LocalDateTime
	 * 
	 */
	public void setCreationDateConversation(LocalDateTime creationDateConversation) {
		this.creationDateConversation = creationDateConversation;
	}

	/**
	 * Gets the client. returns the client that called the callCenter
	 *
	 * @return client Client
	 * 
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Sets the client. set the client that called the callCenter
	 *
	 * @param client ClientTest
	 * 
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Gets the employee. return the employee who answered the call returns
	 *
	 * @return employee Employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * Sets the employee. set the employee who answered the call returns
	 *
	 * @param employee Employee
	 * 
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * Gets the call status. returns the state of the call that can be:
     * The not started.
     * 		NOT_STARTED,
     * The waiting.
     *  	WAITING,
     * The active. 
     *		ACTIVE,
     * The disconnected. 
     *		DISCONNECTED;
	 *
	 * @return callStatus CallStatus
	 * 
	 */
	public CallStatus getCallStatus() {
		return callStatus;
	}

	/**
	 * Sets the call status. set the state of the call that can be:
     * The not started.
     * 		NOT_STARTED,
     * The waiting.
     *  	WAITING,
     * The active. 
     *		ACTIVE,
     * The disconnected. 
     *		DISCONNECTED;
	 *
	 * @param callStatus CallStatus
	 * 
	 */
	public void setCallStatus(CallStatus callStatus) {
		this.callStatus = callStatus;
	}

	/**
	 * Gets the id call. returns the id of the call that is the Ticket assigned by the callCenter 
	 * in our case the Dispatcher class, it must be unique
	 *
	 * @return idCall Long
	 * 
	 */
	public synchronized Long getIdCall() {
		return idCall;
	}

	/**
	 * Sets the id call. setthe id of the call that is the Ticket assigned by the callCenter 
	 * in our case the Dispatcher class, it must be unique
	 *
	 * @param idCall Long
	 * 
	 */
	public synchronized void setIdCall(Long idCall) {
		this.idCall = idCall;
	}
	
	/**
	 * Gets the duration in seconds. returns the duration of the call
	 *
	 * @return durationInSeconds Integer
	 * 
	 */
	public Integer getDurationInSeconds() {
		return durationInSeconds;
	}

	/**
	 * Sets the duration in seconds. set the duration of the call
	 *
	 * @param durationInSeconds Integer
	 * 
	 */
	public void setDurationInSeconds(Integer durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}
	
	/**
	 * Gets the dispatcher. returns the dispatcher that manages the calls
	 *
	 * @return dispatcher Dispatcher
	 * 
	 */
	public synchronized Dispatcher getDispatcher() {
		return dispatcher;
	}

	/**
	 * Sets the dispatcher. set the dispatcher that manages the calls
	 *
	 * @param dispatcher Dispatcher
	 * 
	 */
	public synchronized void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
	
     /** Builds the list of random calls. this method builds a list of call randoms with their clients assigned to each call
	 *
	 * @param listSizeCall Integer
	 * @param callConcurrent CountDownLatch
	 * 
	 * @return callList ListCall
	 */
    public static List<Call> buildListOfRandomCalls(Integer listSizeCall,CountDownLatch callConcurrent) {
        Validate.isTrue(listSizeCall >= 0);
        List<Call> callList = new ArrayList<>();
        for (Integer i = 0; i < listSizeCall; i++) {
        	//Generated the client associated with the call
        	Client client = new Client("Name_".concat(i.toString()), "Surname_".concat(i.toString()), ThreadLocalRandom.current().nextInt(20,99),ThreadLocalRandom.current().nextInt(1,99999999));
            callList.add(new Call(null,"",null,client,null,null,callConcurrent));
        }
        return callList;
    }
    
    
    /** Validate Client of Call
	 *
	 * @param  call Call
	 * @return Boolean true is validate Client
	 * 
	 */
    public static Boolean validate(Call call){
    	
    	if ((call.getClient().getName().isEmpty()) && 
    		(call.getClient().getSurname().isEmpty()) &&
    		(Integer.valueOf(call.getClient().getDni()) != null) &&
    		(call.getClient().getAge() != null))
    		return Boolean.TRUE;
    	return Boolean.FALSE;
    }
    
    /**
     * Convert the business bean call to a DTO to pass it to the REST service layer
     * 
     * @param call Call
     * @return callDTO CallDTO
     */
    public static CallDTO convertCallToCallDTO(Call call){
    	CallDTO callDTO = new CallDTO();
    		callDTO.setIdCall(call.getIdCall());
    		callDTO.setCallStatus(call.getCallStatus());
    		callDTO.setCreationDate(call.getCreationDate());
    		callDTO.setCreationDateConversation(call.getCreationDateConversation());
    		callDTO.setClient(call.getClient());
    		callDTO.setMessage(call.getMessage());
    		callDTO.setEmployeeDTO(Employee.convertEmployeeToEmployeeDTO(call.getEmployee()));
    		callDTO.setDurationInSeconds(call.getDurationInSeconds());
    	return callDTO;
    }


    /** This run method is executed when the call is generated, that is, when the customer dials the call center from their telephone.
     * This thread will start with the start method and then the run method will be called, in this way the call runs like a thread
	 * The call to the callcenter was initiated by the client, this means that he marked the number of 
	 * the call center and pressed the button of his telephone call
	 */
	@Override
	public void run() {
		try {
			//The call center (Dispatcher) assigns you a unique call number or ticket
			if (this.getIdCall() == null)
				this.setIdCall(dispatcher.getCallId());
			//I charge the call in the list for query
			dispatcher.getQueryCallsDTO().add(Call.convertCallToCallDTO(this));
			
			//Waiting for all calls to start at the same time if callConcurrent is enabled
			if(callConcurrent != null)
				callConcurrent.await();
			this.callStatus = CallStatus.WAITING;
			this.creationDate = LocalDateTime.now();
			
			//Salve Call Status into List query Call
			dispatcher.syncProductiveCallQueueWithQueryCall(this);
			
			logger.info("Call ID: " + this.getIdCall() + " Calling the Client: " + this.client.getName() + " Surname: " + this.client.getSurname() + 
					" Creation Date Call: " + this.creationDate + " Call status: " + this.callStatus);
			logger.info("----------------------------------------------------------------------------------------------------------------------------");
			this.getDispatcher().dispatchCall(this);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			logger.error("The call fail: DISCONNECTED");
			this.callStatus = CallStatus.DISCONNECTED;
			//Salve Call Status into List query Call
			dispatcher.syncProductiveCallQueueWithQueryCall(this);
			logger.info("----------------------------------------------------------------------------------------------------------------------------");
		}
	}

}
