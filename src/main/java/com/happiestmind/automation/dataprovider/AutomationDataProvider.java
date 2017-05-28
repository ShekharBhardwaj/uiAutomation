package com.happiestmind.automation.dataprovider;

import org.apache.log4j.Logger;

import com.happiestmind.automation.exception.AutomationDataProviderCustomException;
import com.happiestmind.automation.util.ParameterReader;

public class AutomationDataProvider {

	final static Logger LOG = Logger.getLogger(AutomationDataProvider.class);
	/**
	 * return the test data from a test in a 2 dim array
	 * 
	 * @param xls
	 * @param testCaseName
	 * @return
	 * @throws AutomationDataProviderCustomException 
	 */
	public static Object[][] getData(ParameterReader xls, String testCaseName) throws AutomationDataProviderCustomException {
		// if the sheet is not present
		
		Object[][] data = null; 
		
		try {
		LOG.info("Providing data for: "+ testCaseName);
		if (!xls.isSheetExist(testCaseName)) {
			xls = null;
			LOG.warn("xls file or xls sheet for test case not found, your test may fail, extent is not recording this event.");
			LOG.warn("xls file or xls sheet for test case not found, your test may fail, testng may be not recording this event.");
			LOG.warn("If your test case has failed please check if xls and worksheet is present and with correct name and re-run your test.");
			return new Object[1][0];
		}

		int rows = xls.getRowCount(testCaseName);
		int cols = xls.getColumnCount(testCaseName);

		 data = new Object[rows - 1][cols - 2];
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols - 2; colNum++) {
				data[rowNum - 2][colNum] = xls.getCellData(testCaseName, colNum, rowNum);
			}
		}
		} catch (Exception e){
			throw new AutomationDataProviderCustomException(testCaseName, e);
		}
		return data;

	}

	

}
