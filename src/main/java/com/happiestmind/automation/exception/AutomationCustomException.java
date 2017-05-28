package com.happiestmind.automation.exception;

public class AutomationCustomException extends Exception implements CloudLendingAutomationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public AutomationCustomException() {}

     public AutomationCustomException(String message)
     {
        super(message);
     }
	
     public AutomationCustomException(String message, Throwable cause)
     {
        super(message, cause);
     }
	

}
