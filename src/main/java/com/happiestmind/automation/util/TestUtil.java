package com.happiestmind.automation.util;

import org.apache.log4j.Logger;

import com.happiestmind.automation.exception.ParameterReaderCustomException;

public class TestUtil {

	final static Logger LOG = Logger.getLogger(TestUtil.class);

	/***
	 * finds if the test suite is runnable
	 * 
	 * @param xls
	 * @param suiteName
	 * @return
	 * @throws ParameterReaderCustomException 
	 */
	public static boolean isSuiteRunnable(ParameterReader xls, String suiteName) throws ParameterReaderCustomException {
		boolean isExecutable = false;
		LOG.info("Checking if suite : " +suiteName +" is available for execution.");
		for (int i = 2; i <= xls.getRowCount("Test Suite"); i++) {

			if (xls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase(suiteName)) {
				if (xls.getCellData("Test Suite", "Runmode", i).equalsIgnoreCase("Y")) {
					LOG.info("Suite : " +suiteName +" is available for execution.");
					isExecutable = true;
				} else {
					LOG.warn("Suite : " +suiteName +" is not available for execution.");
					isExecutable = false;
				}
			}

		}
		xls = null; // release memory
		return isExecutable;

	}

	/***
	 * returns true if runmode of the test is equal to Y
	 * 
	 * @param xls
	 * @param testCaseName
	 * @return
	 * @throws ParameterReaderCustomException 
	 */
	public static boolean isTestCaseRunnable(ParameterReader xls, String testCaseName) throws ParameterReaderCustomException {
		boolean isExecutable = false;
		LOG.info("Checking if Test Case : " +testCaseName +" is available for execution.");
		for (int i = 2; i <= xls.getRowCount("Test Cases"); i++) {
			if (xls.getCellData("Test Cases", "TCID", i).equalsIgnoreCase(testCaseName)) {
				if (xls.getCellData("Test Cases", "Runmode", i).equalsIgnoreCase("Y")) {
					LOG.info("Test Case : " +testCaseName +" is available for execution.");
					isExecutable = true;
				} else {
					LOG.warn("Test Case : " +testCaseName +" is not available for execution.");
					isExecutable = false;
				}
			}
		}

		return isExecutable;

	}

	/***
	 * checks RUnmode for dataSet
	 * 
	 * @param xlsFile
	 * @param sheetName
	 * @return
	 * @throws ParameterReaderCustomException 
	 */
	public static String[] getDataSetRunmodes(ParameterReader xlsFile, String sheetName) throws ParameterReaderCustomException {
		String[] runmodes = null;
		if (!xlsFile.isSheetExist(sheetName)) {
			xlsFile = null;
			sheetName = null;
			runmodes = new String[1];
			runmodes[0] = "Y";
			xlsFile = null;
			sheetName = null;
			return runmodes;
		}
		runmodes = new String[xlsFile.getRowCount(sheetName) - 1];
		for (int i = 2; i <= runmodes.length + 1; i++) {
			runmodes[i - 2] = xlsFile.getCellData(sheetName, "Runmode", i);
		}
		xlsFile = null;
		sheetName = null;
		return runmodes;

	}

	/***
	 * update results for a particular data set
	 * 
	 * @param xls
	 * @param testCaseName
	 * @param rowNum
	 * @param result
	 */
	public static void reportDataSetResult(ParameterReader xls, String testCaseName, int rowNum, String result) {
		xls.setCellData(testCaseName, "Results", rowNum, result);
	}

	/***
	 * return the row num for a test
	 * 
	 * @param xls
	 * @param id
	 * @return
	 * @throws ParameterReaderCustomException 
	 */
	public static int getRowNum(ParameterReader xls, String id) throws ParameterReaderCustomException {
		for (int i = 2; i <= xls.getRowCount("Test Cases"); i++) {
			String tcid = xls.getCellData("Test Cases", "TCID", i);

			if (tcid.equals(id)) {
				xls = null;
				return i;
			}

		}

		return -1;
	}

}
