package com.happiestmind.automation.suite.smoke;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.happiestmind.automation.browsers.BrowserFactory;
import com.happiestmind.automation.dataprovider.AutomationDataProvider;
import com.happiestmind.automation.pagefactory.GoogleSearchPage;
import com.happiestmind.automation.util.ErrorHandler;
import com.happiestmind.automation.util.TestUtil;

public class GoogleSearchTest extends SmokeSuiteBaseImpl {
	
	
	final static Logger LOG = Logger.getLogger(GoogleSearchTest.class);

	String runmodes[]=null;
	static int count=-1;
	//static boolean pass=false;
	static boolean fail=false;
	static boolean skip=false;
	static boolean isTestPass=true;
	// Runmode of test case in a suite
	@BeforeTest
	public void checkTestSkip(){
		
		if(!TestUtil.isTestCaseRunnable(smokeSuite,this.getClass().getSimpleName())){
			throw new SkipException("Skipping Test Case"+this.getClass().getSimpleName()+" as runmode set to NO");//reports
		}
		// load the runmodes off the tests
		runmodes=TestUtil.getDataSetRunmodes(smokeSuite, this.getClass().getSimpleName());
	}
	
	@Test(dataProvider="getTestData")
	public void testcaseA1(
						String browser,
						String uri,
						String searchWord,
						String title
							) throws InterruptedException, IOException, URISyntaxException{
		// test the runmode of current dataset
		String pagetitle;
		count++;
		if(!runmodes[count].equalsIgnoreCase("Y")){
			skip=true;
			throw new SkipException("Runmode for test set data set to no "+count);
		}
		
		LOG.info(" Executing : "+this.getClass().getSimpleName());
		LOG.info(browser +" -- "+uri +" -- "+searchWord);
		
		WebDriver driver = BrowserFactory.startBrowser(browser, uri);

		GoogleSearchPage googleSearchPage = PageFactory.initElements(driver, GoogleSearchPage.class);

		googleSearchPage.search(searchWord);
		
		pagetitle = driver.getTitle();
		

		try{
		Assert.assertEquals(title, pagetitle);
		}catch(Throwable t){
			// code to report the error in testng
			ErrorHandler.setVerificationFailure(t);
			// report the error in xls files
			fail=true;
			driver.quit();
			return;
			
		}
		driver.quit();
		
	}
	
	@AfterMethod
	public void reportDataSetResult(){
		if(skip)
			TestUtil.reportDataSetResult(smokeSuite, this.getClass().getSimpleName(), count+2, "SKIP");
		else if(fail){
			isTestPass=false;
			TestUtil.reportDataSetResult(smokeSuite, this.getClass().getSimpleName(), count+2, "FAIL");
		}
		else
			TestUtil.reportDataSetResult(smokeSuite, this.getClass().getSimpleName(), count+2, "PASS");
		
		skip=false;
		fail=false;
		

	}
	
	@AfterTest
	public void reportTestResult(){
		if(isTestPass)
			TestUtil.reportDataSetResult(smokeSuite, "Test Cases", TestUtil.getRowNum(smokeSuite,this.getClass().getSimpleName()), "PASS");
		else
			TestUtil.reportDataSetResult(smokeSuite, "Test Cases", TestUtil.getRowNum(smokeSuite,this.getClass().getSimpleName()), "FAIL");
	
	}
	
	@DataProvider
	public Object[][] getTestData(){
		return AutomationDataProvider.getData(smokeSuite, this.getClass().getSimpleName()) ;
	}
	
	
}
