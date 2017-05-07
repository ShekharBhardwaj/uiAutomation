package com.happiestmind.automation.adapters;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

public class AbstractReportAdapter implements IReporter {
	
	final static Logger LOG = Logger.getLogger(AbstractReportAdapter.class);

	public void generateReport(List<XmlSuite> arg0, List<ISuite> suites, String outputDirectory) {
		// Iterating over each suite included in the test
		LOG.info("*****Abstract Report******");

		for (ISuite suite : suites) {
			// Following code gets the suite name
			String suiteName = suite.getName();
			// Getting the results for the said suite
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			for (ISuiteResult sr : suiteResults.values()) {
				ITestContext tc = sr.getTestContext();
				LOG.info(
						"Passed tests for suite '" + suiteName + "' is:" + tc.getPassedTests().getAllResults().size());
				LOG.info(
						"Failed tests for suite '" + suiteName + "' is:" + tc.getFailedTests().getAllResults().size());
				LOG.info("Skipped tests for suite '" + suiteName + "' is:"
						+ tc.getSkippedTests().getAllResults().size());
				LOG.info("*****End of Report******");

			}
		}

	}

}
