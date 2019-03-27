package com.almundo.callcenter.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.almundo.callcenter.dto.CallDTO;
import com.almundo.callcenter.dto.EmployeeDTO;



// TODO: Auto-generated Javadoc
/**
 * The Class Employee.
 * 
 * @author Marcelo Daniel Martini
 * @version 1.0
 * @since 23/03/2019
 * 
 */
public abstract class Employee implements Comparable<Employee>{
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(Employee.class);
	
	/** The id of Employee. */
	private long idEmployee;
	
	/** The name. */
	private String name;
	
	/** The surname. */
	private String surname;
	
	/** The age. */
	private int age;
	
	/** The married. */
	private boolean married;
	
	/** The employee status. */
	private EmployeeStatus employeeStatus;
	
	/** The prioridad. */
	private Integer prioridad;
	
	/** The salary. */
	private double salary;

	/** number of calls answered by each employee */
	private Integer amountAttendedCalls = 0; 
	
	/** The calls answered by the employee */
	private ConcurrentLinkedDeque<CallDTO> attendedCalls;
	
	
	/**
	 * Gets the amount Answered Calls.
	 *
	 * @return Integer 
	 */
	public Integer getAmountAnsweredCalls() {
		return amountAttendedCalls;
	}

	/**
	 * Sets the amount Answered Calls.
	 *
	 * @param amountAttendedCalls Integer 
	 */
	public void setAmountAttendedCalls(Integer amountAttendedCalls) {
		this.amountAttendedCalls = amountAttendedCalls;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return String 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name String
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the surname.
	 *
	 * @return surname String
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname String
	 * 
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the age.
	 *
	 * @return Integer age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 *
	 * @param age Integer
	 * 
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Checks if is married.
	 *
	 * @return true, if is married
	 */
	public Boolean isMarried() {
		return married;
	}

	/**
	 * Sets the married.
	 *
	 * @param married Boolean
	 * 
	 */
	public void setMarried(boolean married) {
		this.married = married;
	}

	/**
	 * Gets the salary.
	 *
	 * @return double salary
	 * 
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * Sets the salary.
	 *
	 * @param salary double
	 * 
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	/**
	 * Gets the prioridad.
	 *
	 * @return Integer prioridad
	 * 
	 */
	public Integer getPrioridad() {
		return prioridad;
	}

	/**
	 * Sets the prioridad.
	 *
	 * @param prioridad Integer
	 * 
	 */
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}
	
	/**
	 * Get id Employee
	 * 
	 * @return long idEmployee
	 */
	public long getIdEmployee() {
		return idEmployee;
	}

	/**
	 * Set id Employee
	 * 
	 * @param idEmployee long
	 */
	public void setIdEmployee(long idEmployee) {
		this.idEmployee = idEmployee;
	}
	
	/**
	 * Gets the employee status.
	 *
	 * @return EmployeeStatus employeeStatus
	 * 
	 */
	public synchronized EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	/**
	 * Sets the employee status.
	 *
	 * @param employeeStatus EmployeeStatus 
	 * 
	 */
	public synchronized void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}
	
	/**
	 * Gets the get Attended Calls by the employee.
	 *
	 * @return List Call
	 * 
	 */
    public synchronized List<CallDTO> getAttendedCalls() {
        return new ArrayList<>(attendedCalls);
    }
	
	/**
	 * Gets the employee type.
	 * 
     * OPERATOR
     * SUPERVISOR
     * DIRECTOR
	 *
	 * @return EmployeeType employeeType
	 */
	public abstract EmployeeType getEmployeeType();

	/**
	 * Instantiates a new employee.
	 *
	 * @param idEmployee long
	 * @param name String
	 * @param surname String
	 * @param age Integer
	 * @param married Boolean
	 * @param salary double
	 * 
	 */
	public Employee(long idEmployee, String name, String surname, Integer age, Boolean married,
			double salary) {
		super();
		Validate.notNull(idEmployee);
		Validate.notNull(name);
		Validate.notNull(surname);
		Validate.notNull(age);
		Validate.notNull(married);
		Validate.notNull(salary);
		this.idEmployee = idEmployee;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.married = married;
		this.salary = salary;
		this.employeeStatus = EmployeeStatus.AVAILABLE;
		this.attendedCalls = new ConcurrentLinkedDeque<>();
	}
	
	/**
	 * Build the queue that contains CallCenter employees, operators, supervisors and directors, determining the amount of each one
	 *
	 * @param amountOperator Integer
	 * @param amounSupervisor Integer
	 * @param amountDirector Integer
	 * @return PriorityBlockingQueue Employee
	 */
	public static PriorityBlockingQueue<Employee> buildEmployeeList(Integer amountOperator, Integer amounSupervisor, Integer amountDirector){
		Validate.notNull(amountOperator);
		Validate.notNull(amounSupervisor);
		Validate.notNull(amountDirector);
		PriorityBlockingQueue<Employee> employeeList = new PriorityBlockingQueue<>();
		long j = 0;
		for (Integer i = 0; i < amountOperator; i++) {
        	Operator operator = new Operator(j,"Name_Operator_".concat(i.toString()),"Surname _Operator_".concat(i.toString()),
        			ThreadLocalRandom.current().nextInt(20,99),Boolean.FALSE,ThreadLocalRandom.current().nextInt(10000,20000));
        	employeeList.add(operator);
        	j++;
        }
        for (Integer i = 0; i < amounSupervisor; i++) {
        	Supervisor supervisor = new Supervisor(j,"Name_Supervisor_".concat(i.toString()),"Surname _Supervisor_".concat(i.toString()),
        			ThreadLocalRandom.current().nextInt(20,99),Boolean.TRUE,ThreadLocalRandom.current().nextInt(50000,100000));
        	employeeList.add(supervisor);
        	j++;
        }
        for (Integer i = 0; i < amountDirector; i++) {
        	Director director = new Director(j,"Name_Director_".concat(i.toString()),"Surname _Director_".concat(i.toString()),
        			ThreadLocalRandom.current().nextInt(20,99),Boolean.TRUE,ThreadLocalRandom.current().nextInt(100000,200000));
        	employeeList.add(director);
        	j++;
        }
        return employeeList;
        
	}
	

	
    /** Attend. This class attends the call that is, it is when an employee is assigned to a call from a client. The call 
     * goes to active state and the employee has been occupied which was taken out of the employee's queue 
     * (PriorityBlockingQueue) because it is busy
	 *
	 * @param call Call
	 * 
	 */
    public void attend(Call call) {
        
    	try {
    		
    		//Asign employee to the call
    		call.setEmployee(this);
        	
    		//The date on which the conversation between the employee and the client begins
        	call.setCreationDateConversation(LocalDateTime.now());
        	
        	//I put the call in Active state
            call.setCallStatus(CallStatus.ACTIVE);
            
            //Set a random duration between 5 and 10 seconds.
            call.setDurationInSeconds(ThreadLocalRandom.current().nextInt(Constants.MIN_CALL_DURATION,Constants.MAX_CALL_DURATION + 1));
            
            //simulate a message of conversation
            call.setMessage("Conversation Call ID: " + call.getIdCall() + " of Client Name: " + call.getClient().getName() + " Surname : " + call.getClient().getSurname() + 
            		" talking with " + this.getEmployeeType() + " (" + this.getName() + " " + this.getSurname() + ")");            
            
            //Simulates the duration of the call conversation
            TimeUnit.SECONDS.sleep(call.getDurationInSeconds());
            
            //Increase the number of clients served by the employee
            this.amountAttendedCalls++;
            
            //Change the status of the call to finished
        	call.setCallStatus(CallStatus.FINISHED);
        
        } catch (InterruptedException e) {
        	Thread.currentThread().interrupt();
        	call.setCallStatus(CallStatus.DISCONNECTED);
        	logger.error("Conversation Call ID: " + call.getIdCall() + " of Client Name: " + call.getClient().getName() + " " + call.getClient().getSurname() + 
        			" talking with " + this.getEmployeeType() + " (" + this.getName() + " " + this.getSurname() + ")" + 
        			"was interrupted and could NOT FINISH");
        	logger.info("----------------------------------------------------------------------------------------------------------------------------");
		} finally {
			//change the status of the employee AVAILABLE
        	this.employeeStatus = EmployeeStatus.AVAILABLE;
        	//Add calls attended by the employee
        	this.attendedCalls.add(Call.convertCallToCallDTO(call));
        }
        
        logger.info("Conversation Call ID: " + call.getIdCall() + " of Client Name: " + call.getClient().getName() + " " + call.getClient().getSurname() + 
        		" talking with " + this.getEmployeeType() + " (" + this.getName() + " " + this.getSurname() + ")" + "was finished call of " + 
        		call.getDurationInSeconds() + " seconds " + " Creation Date Conversation " + call.getCreationDateConversation());
        logger.info("Employee " + this.getName() + " attended " + this.amountAttendedCalls);
        logger.info("----------------------------------------------------------------------------------------------------------------------------");
		
    }
    
    /**
     * Convert the business bean employee to a DTO to pass it to the REST service layer
     * 
     * @param employee Employee
     * @return EmployeeDTO 
     */
    public static EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
    	EmployeeDTO employeeDTO = new EmployeeDTO();
    	if (employee != null){
        	employeeDTO.setIdEmployee(employee.getIdEmployee());
        	employeeDTO.setAge(employee.getAge());
        	employeeDTO.setAmountAttendedCalls(employee.getAmountAnsweredCalls());
        	employeeDTO.setEmployeeStatus(employee.getEmployeeStatus());
        	employeeDTO.setName(employee.getName());
        	employeeDTO.setSalary(employee.getSalary());
        	employeeDTO.setSurname(employee.getSurname());
        	employeeDTO.setPrioridad(employee.getPrioridad());
        	return employeeDTO;
    	}
    	return null;
    }
}
