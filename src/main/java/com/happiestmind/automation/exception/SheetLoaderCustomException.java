package com.happiestmind.automation.exception;

public class SheetLoaderCustomException extends Exception implements CloudLendingAutomationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -467783825587092393L;
	
   public SheetLoaderCustomException(String message) {
       super(message);
   }

   public SheetLoaderCustomException(String message, Throwable cause) {
       super(message, cause);
   }
	

}
