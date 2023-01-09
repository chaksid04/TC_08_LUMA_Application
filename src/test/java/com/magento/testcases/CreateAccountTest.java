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
import com.magento.pages.CreateAccountPage;
import com.magento.pages.HomePage;
import com.magento.pages.MyAccountPage;
import com.magento.util.TestUtil;

public class CreateAccountTest extends TestBase {

	HomePage hmpg;
	CreateAccountPage createAccPg;
	MyAccountPage myAccPage;
	String emailFinal;
	ExtentTest test;
	ExtentReports extent;

	public CreateAccountTest() {

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
		
		test = extent.createTest("CreateAccountTest", "Validation of Account Creation");
		test.log(Status.INFO, "Starting Test Case");

	}

	@BeforeMethod
	public void setUp() {

		hmpg = new HomePage();
		createAccPg = new CreateAccountPage();
		myAccPage = new MyAccountPage();
	}

	/*
	 * This method validates whether the Account creation page exists or not
	 */

	@Test(priority = 1)
	public void CreateAccountPagePresentTest() {
		// Validate Home Page Exists
		hmpg.validateHomePage();
		// Create Account
		hmpg.createAcc();
		Boolean flag = createAccPg.validateCreateAccountPage();
		Assert.assertTrue(flag);
		if (flag) {
			log.info("Account creation page is present");
			test.pass("Account creation page is present");
		}
	}

	/*
	 * This method validates the Account creation, Newsletter subscription and
	 * subscription cancellation verification
	 * 
	 */
	@Test(dependsOnMethods = { "CreateAccountPagePresentTest" })
	public void ValidateCreateAccountTest() {

		// Creating dynamic login for unattended runs
		emailFinal = prop.getProperty("firstname") + prop.getProperty("lastname") + TestUtil.generateRandomNumber()
				+ "@mail.com";

		// Enter account details
		myAccPage = createAccPg.enterAccountDetails(prop.getProperty("firstname"), prop.getProperty("lastname"),
				emailFinal, prop.getProperty("password"));

		// Account Creation Validation
		Boolean flag = myAccPage.validateAccountCreation();
		Assert.assertTrue(flag);
		test.pass("Account creation successful");

		// Login Details Validation
		String loginCreds = myAccPage.getloginDetails();

		Assert.assertTrue(loginCreds.contains(prop.getProperty("firstname")));
		Assert.assertTrue(loginCreds.contains(prop.getProperty("lastname")));
		Assert.assertTrue(loginCreds.contains(emailFinal));
		log.info("Account Creation Validation Successful");
		test.pass("Account creation validation successful");

		// Subscription Validation
		Boolean newsletterSubscription = myAccPage.getNewsletterSubscription();
		Assert.assertTrue(newsletterSubscription);
		test.pass("Subscription Validation successful");

		// Subscription Cancellation Validation
		myAccPage.cancelSubscription();
		Boolean flagSubscription = myAccPage.validateSubscripionCancelled();
		Assert.assertTrue(flagSubscription);
		log.info("Newsletter subscription cancellation successful");
		test.pass("Newsletter subscription cancellation successful");
	}

	/*
	 * This method logs out from the application
	 * 
	 */
	@Test(dependsOnMethods = { "ValidateCreateAccountTest" })
	public void validateLogoutTest() {

		// Click on Logout
		Assert.assertTrue(hmpg.signoutFromLUMA());
		Boolean flag = hmpg.validateHomePage();
		Assert.assertTrue(flag);
		if (flag) {
			log.info("Logout from the application successful");
			test.pass("Logout from the application successful");
		}

		test.info("Test completed");
	}

	@AfterMethod
	public void close() {

		// driver.quit();
	}

	@AfterTest
	public void tearDown() {

		// Enter the email value in Config
		extent.flush();
		TestUtil.writeData("email", emailFinal, "config");
		driver.quit();
	}
	
	@AfterSuite
	public void extentFlush() {

		extent.flush();
	}

}
