package com.happiestmind.automation.exception;

public class ParameterReaderCustomException extends Exception implements CloudLendingAutomationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -467783825587092393L;
	
   public ParameterReaderCustomException(String message) {
       super(message);
   }

   public ParameterReaderCustomException(String filePath, Throwable cause) {
       super(filePath, cause);
   }
	

}
