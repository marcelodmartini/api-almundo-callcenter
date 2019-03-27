package com.almundo.callcenter.dto;

import java.time.LocalDateTime;

import com.almundo.callcenter.beans.CallStatus;
import com.almundo.callcenter.beans.Client;

//TODO: Auto-generated Javadoc
/**
* The CallDTO.
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
* 
*/
public class CallDTO {

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
	private EmployeeDTO employeeDTO;
	
	/** The call status. */
	private CallStatus callStatus;
	
	/** The duration in seconds. */
	private Integer durationInSeconds;

	public Long getIdCall() {
		return idCall;
	}

	public void setIdCall(Long idCall) {
		this.idCall = idCall;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getCreationDateConversation() {
		return creationDateConversation;
	}

	public void setCreationDateConversation(LocalDateTime creationDateConversation) {
		this.creationDateConversation = creationDateConversation;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public EmployeeDTO getEmployeeDTO() {
		return employeeDTO;
	}

	public void setEmployeeDTO(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
	}

	public CallStatus getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(CallStatus callStatus) {
		this.callStatus = callStatus;
	}

	public Integer getDurationInSeconds() {
		return durationInSeconds;
	}

	public void setDurationInSeconds(Integer durationInSeconds) {
		this.durationInSeconds = durationInSeconds;
	}
	
    /** Validate Client of Call
	 *
	 * @param  call CallDTO
	 * @return Boolean 
	 * 
	 */
    public static Boolean validate(CallDTO call){
    	
    	if ((!call.getClient().getName().isEmpty()) && 
    		(!call.getClient().getSurname().isEmpty()) &&
    		(Integer.valueOf(call.getClient().getDni()) != null) &&
    		(call.getClient().getAge() != null))
    		return Boolean.TRUE;
    	return Boolean.FALSE;
    }
	
	
}
