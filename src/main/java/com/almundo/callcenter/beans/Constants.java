package com.almundo.callcenter.beans;


//TODO: Auto-generated Javadoc
/**
* 
* Constants Used to start the CallCenter and the application
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
* 
* 
* 
*/
public class Constants {
	
    /** The Constant MIN_CALL_DURATION. */
    public static final int MIN_CALL_DURATION = 5;

    /** The Constant MAX_CALL_DURATION. */
    public static final int MAX_CALL_DURATION = 10;
    
    /** The Constant CALL_AMOUNT. */
    public static final int CALL_AMOUNT = 15;
    
    /** The Constant AMOUNT_OPERATOR. */
    public static final int AMOUNT_OPERATOR = 2; 
    
    /** The Constant AMOUNT_SUPERVISOR. */
    public static final int AMOUNT_SUPERVISOR = 2;
    
    /** The Constant AMOUNT_DIRECTOR. */
    public static final int AMOUNT_DIRECTOR = 1;
    
    /** The Constant MAX_THREADS.This constant tells 
     * the Dispatcher class that it must have the ability 
     * to process 10 calls at the same time (concurrently) */
    public static final int MAX_THREADS = 10;

}
