package com.magento.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.magento.base.TestBase;
import com.magento.pages.HomePage;
import com.magento.pages.LoginPage;
import com.magento.pages.MyAccountPage;
import com.magento.pages.MyAddressPage;

public class ValidateAddressTest extends TestBase {

	HomePage hmpg;
	LoginPage loginPage;
	MyAccountPage myAccountPage;
	MyAddressPage myAddressPage;

	public ValidateAddressTest() {
		super();
	}

	@BeforeTest
	public void initialise() {

		initialization();
	}

	@BeforeMethod
	public void setUp() {

		hmpg = new HomePage();
		loginPage = new LoginPage();
		myAddressPage = new MyAddressPage();
		myAccountPage = new MyAccountPage();

	}

	
	/*
	 * This method validates the new address creation
	 * 
	 */
	
	@Test(priority = 1)
	public void validateCreateNewAddressTest() {

		if (hmpg.validateHomePage()) {
			hmpg.singIn();
			loginPage.login(prop.getProperty("email"), prop.getProperty("password"));
			
			//Navigate to my account
			hmpg.navigatetoMyAccount();
			
			//Validate my account page
			if (myAccountPage.validateMyAccountPage()) {
				myAccountPage.openAddressBook();
				
				//Validate new address page
				if (myAddressPage.validatenewAddressPage()) {
					
					//Enter/Update address
					myAddressPage.enterUpdateAddress("", "", prop.getProperty("company"),
							prop.getProperty("phonenumber"), prop.getProperty("streetaddress1"),
							prop.getProperty("streetaddress2"), prop.getProperty("streetaddress3"),
							prop.getProperty("city"), prop.getProperty("state"), prop.getProperty("postalcode"),
							prop.getProperty("country"));

					Assert.assertTrue(myAddressPage.validateAddressSaved());
				}
			}
		}
	}

	
	/*
	 * This method validates whether the saved address 
	 */
	@Test(dependsOnMethods = { "validateCreateNewAddressTest" })
	public void validateSavedAddressVerificationTest() {
		
		//Validate address saved successfully
		if (myAddressPage.validatesavedAddress().contains(prop.getProperty("streetaddress1"))
				&& myAddressPage.validatesavedAddress().contains(prop.getProperty("country"))) {

			Assert.assertTrue(true);
			log.info("New Address created successfully");
		}
	}

	
	/*
	 * This method validates whether the address is edited successfully
	 */
	@Test(dependsOnMethods = { "validateSavedAddressVerificationTest" })
	public void validateEditAddressVerificationTest() {
		
		
		//Edit Address and verify edit successful
		myAddressPage.editAddress();
		myAddressPage.enterUpdateAddress("", "", "", prop.getProperty("phonenumberupdate"), "", "", "", "", "", "", "");

		Assert.assertEquals(myAddressPage.validatePhoneNumber(), prop.getProperty("phonenumberupdate"));
		log.info("Address edit verification successful");
	}

	/*
	 * This method validates the logout functionality
	 */
	@Test(dependsOnMethods = { "validateSavedAddressVerificationTest" })
	public void validateLogoutTest() {
		
		//Logout from the Application
		Assert.assertTrue(hmpg.signoutFromLUMA());
		Boolean flag = hmpg.validateHomePage();
		Assert.assertTrue(flag);
		if(flag) {
			log.info("Logout from the Magento app successful");
		}
			
	}

	@AfterMethod
	public void close() {

		// driver.quit();
	}

	@AfterTest
	public void tearDown() {

		driver.quit();
	}

}
