package com.happiestmind.automation.TestCases;

import java.io.IOException;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.happiestmind.automation.pagefactory.HappiestMindLogin;
import com.happiestmind.automation.util.BrowserFactory;

public class LoginTest {

	@Test
	public void loginTest() throws IOException, URISyntaxException, InterruptedException {

		WebDriver driver = BrowserFactory.startBrowser("chrome", "http://www.login.com");
		HappiestMindLogin happiestMindLogin = PageFactory.initElements(driver, HappiestMindLogin.class);
		happiestMindLogin.login("tom", "password");
		driver.quit();

	}

}
