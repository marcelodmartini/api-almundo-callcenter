package com.almundo.callcenter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO: Auto-generated Javadoc
/**
* CallCenterExcepcion. It is an exception message handler, managed at the business level
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
* 
*/
public class CallCenterExcepcion extends Exception{
	     
		private static final long serialVersionUID = 1L;

		public static final Logger logger = LoggerFactory.getLogger(CallCenterExcepcion.class);
	    
		private final int codeError;
	     
	    public CallCenterExcepcion(int codeError){
	        super();
	        this.codeError=codeError;
	    }
	     
	    @Override
	    public String getMessage(){
	         
	        String mensaje="";
	         
	        switch(codeError){
	            case 1:
	                mensaje="Call Center is slowed down. You must start it before, with the service (api / start)";
	                break;
	            case 2:
	                mensaje="The Call Center not started";
	                break;
	            default:
	                break;
	        }
	        return mensaje;
	         
	    }	     
}