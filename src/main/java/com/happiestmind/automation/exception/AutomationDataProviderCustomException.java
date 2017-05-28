package com.happiestmind.automation.exception;

public class AutomationDataProviderCustomException extends Exception implements CloudLendingAutomationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -467783825587092393L;
	
   public AutomationDataProviderCustomException(String message) {
       super(message);
   }

   public AutomationDataProviderCustomException(String filePath, Throwable cause) {
       super(filePath, cause);
   }
	

}
