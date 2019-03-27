package com.almundo.callcenter.util;

//TODO: Auto-generated Javadoc
/**
* CustomErrorType. It is an exception message handler, managed at the business level
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
* 
*/
public class CustomErrorType {

    private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
