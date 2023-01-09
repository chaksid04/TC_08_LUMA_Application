package com.magento.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.magento.base.TestBase;
import com.magento.pages.HomePage;
import com.magento.pages.LoginPage;

public class LoginTest extends TestBase {

	HomePage hmpg;
	LoginPage loginPage;
	ExtentTest test;
	ExtentReports extent;

	public LoginTest() {
		super();
	}

	
	
	@BeforeSuite
	public void setupExtent() {
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");

		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		
	}
	
	
	@BeforeTest
	public void initialise() {

		initialization();
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");

		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		test = extent.createTest("LoginTest", "Validation of Login to Magento");
		test.log(Status.INFO, "Starting Test Case");
	}

	@BeforeMethod
	public void setUp() {

		hmpg = new HomePage();
		loginPage = new LoginPage();
	}

	
	/*
	 * This method validates whether the Home page exists 
	 * 
	 */
	@Test(priority = 1)
	public void homePagePresentTest() {
		
		//Validate Home Page
		Boolean flag = hmpg.validateHomePage();
		Assert.assertTrue(flag);
		if(flag) {
			log.info("Home page validation successful");
			test.pass("Home page validation successful");
		}
	}

	/*
	 * This method validates the Login functionality 
	 * 
	 */
	@Test(dependsOnMethods = { "homePagePresentTest" })
	public void validateLoginTest() {
		
		//Validate login
		loginPage = hmpg.singIn();
		hmpg = loginPage.login(prop.getProperty("email"), prop.getProperty("password"));
		Boolean flag = hmpg.validateHomePage();
		Assert.assertTrue(flag);
		if(flag) {
			log.info("Login to the Magento app successful");
			test.pass("Login to the Magento app successful");
		}
			
	}

	/*
	 * This method validates the logout
	 * functionality
	 */
	@Test(dependsOnMethods = { "validateLoginTest" })
	public void validateLogoutTest() {
		
		//Validate logout
		Assert.assertTrue(hmpg.signoutFromLUMA());
		Boolean flag = hmpg.validateHomePage();
		Assert.assertTrue(flag);
		if(flag) {
			log.info("Logout from the Magento application successful");
			test.pass("Logout from the Magento application successful");
		}
	}
	
	@AfterMethod
	public void close() {

		// driver.quit();
	}

	@AfterTest
	public void tearDown() {
		extent.flush();
		driver.quit();
	}
	
	
	@AfterSuite
	public void extentFlush() {

		extent.flush();
	}


}
