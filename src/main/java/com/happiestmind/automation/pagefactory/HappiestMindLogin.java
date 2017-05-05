package com.happiestmind.automation.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.happiestmind.automation.util.MiscUtils;

public class HappiestMindLogin {
	
	
	WebDriver driver;

	public HappiestMindLogin(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	
	@FindBy(how  = How.XPATH, using = "//ghgjgj")
	WebElement usrname;
	
	@FindBy(how  = How.XPATH, using = "//sadas")
	WebElement password;
	
	@FindBy(how  = How.XPATH, using = "//bnmbmnb")
	WebElement login;
	
	
	
	public void login(String username, String passcode) throws InterruptedException{
		
		MiscUtils.isElementPresent(usrname, driver);
		usrname.sendKeys(username);
		MiscUtils.isElementPresent(password, driver);
		password.sendKeys(passcode);
		MiscUtils.isElementPresent(login, driver);
		login.click();
		
	}
	
	
	

}
