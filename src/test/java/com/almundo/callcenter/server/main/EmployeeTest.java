package com.almundo.callcenter.server.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

import org.junit.Test;

import com.almundo.callcenter.beans.Call;
import com.almundo.callcenter.beans.Employee;
import com.almundo.callcenter.beans.EmployeeStatus;
import com.almundo.callcenter.beans.EmployeeType;


public class EmployeeTest {
	
    @Test(expected = NullPointerException.class)
    public void testEmployeeInvalidCreation() {
        new Employee(0,null,null,null,null,0) {
			
			@Override
			public int compareTo(Employee o) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public EmployeeType getEmployeeType() {
				// TODO Auto-generated method stub
				return null;
			}
		};
    }
    
    
    @Test
    public void testEmployeeAttendWhileAvailable() throws InterruptedException {
    	PriorityBlockingQueue<Employee> employee = Employee.buildEmployeeList(1,1,1);
    	List<Call> calls = Call.buildListOfRandomCalls(3,null);
    	
    	Employee employeeOperator = employee.take();
    	calls.get(0).setIdCall(Long.valueOf(0));
    	employeeOperator.attend(calls.get(0));
    	assertEquals(employeeOperator.getAmountAnsweredCalls(),Integer.valueOf(1));
    	
        Employee employeeSupervisor = employee.take();
        calls.get(0).setIdCall(Long.valueOf(1));
        employeeSupervisor.attend(calls.get(1));
        assertEquals(employeeSupervisor.getAmountAnsweredCalls(),Integer.valueOf(1));
        
        Employee employeeDirector = employee.take();
        calls.get(0).setIdCall(Long.valueOf(2));
        employeeDirector.attend(calls.get(2));
        assertEquals(employeeDirector.getAmountAnsweredCalls(),Integer.valueOf(1));

    }


    
    
    @Test
    public void testBuildEmployeeList() throws InterruptedException{
    	PriorityBlockingQueue<Employee> employee = Employee.buildEmployeeList(1,1,1);
    	
    	assertNotNull(employee);
        assertEquals(employee.size(),3);
        Employee employeeOperator = employee.take();
        Employee employeeSupervisor = employee.take();
        Employee employeeDirector = employee.take();
        assertEquals(EmployeeType.OPERATOR, employeeOperator.getEmployeeType());
        assertEquals(EmployeeStatus.AVAILABLE, employeeOperator.getEmployeeStatus());
        assertEquals(EmployeeType.SUPERVISOR, employeeSupervisor.getEmployeeType());
        assertEquals(EmployeeStatus.AVAILABLE, employeeSupervisor.getEmployeeStatus());
        assertEquals(EmployeeType.DIRECTOR, employeeDirector.getEmployeeType());
        assertEquals(EmployeeStatus.AVAILABLE, employeeDirector.getEmployeeStatus());
    }
    
    
    
    
}
