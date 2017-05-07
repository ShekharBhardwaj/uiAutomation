package com.happiestmind.automation.dataprovider;

import org.apache.log4j.Logger;

import com.happiestmind.automation.util.ParameterReader;

public class AutomationDataProvider {

	final static Logger LOG = Logger.getLogger(AutomationDataProvider.class);
	/**
	 * return the test data from a test in a 2 dim array
	 * 
	 * @param xls
	 * @param testCaseName
	 * @return
	 */
	public static Object[][] getData(ParameterReader xls, String testCaseName) {
		// if the sheet is not present
		
		LOG.info("Providing data for: "+ testCaseName);
		if (!xls.isSheetExist(testCaseName)) {
			xls = null;
			LOG.warn("xls file not found.");
			return new Object[1][0];
		}

		int rows = xls.getRowCount(testCaseName);
		int cols = xls.getColumnCount(testCaseName);

		Object[][] data = new Object[rows - 1][cols - 2];
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols - 2; colNum++) {
				data[rowNum - 2][colNum] = xls.getCellData(testCaseName, colNum, rowNum);
			}
		}
		return data;

	}

	

}
