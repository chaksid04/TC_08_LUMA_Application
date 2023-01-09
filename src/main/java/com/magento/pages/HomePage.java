package com.magento.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.magento.base.TestBase;

public class HomePage extends TestBase {

	public HomePage() {

		PageFactory.initElements(driver, this);

	}

	/*
	 * Identification of Object Properties to be used by the page interactions
	 */

	@FindBy(xpath = "//a[@class='logo']")
	WebElement homepageLogo;

	@FindBy(xpath = "//a[contains(text(),'Create an Account')]")
	WebElement hpcreateAccount;

	@FindBy(xpath = "//a[contains(text(),'Sign In')]")
	WebElement hpsignIn;

	@FindBy(xpath = "//button[@data-action='customer-menu-toggle']")
	WebElement menuOption;

	@FindBy(xpath = "//a[contains(text(),'Sign Out')]")
	WebElement hpsignOut;

	@FindBy(xpath = "//a[contains(text(),'My Account')]")
	WebElement hpmyAccount;

	@FindBy(xpath = "//span[contains(text(),'You are signed out')]")
	WebElement signoutConfirm;

	@FindBy(xpath = "//span[text()='Gear']")
	WebElement mainitemGear;

	// Signout from Magento App
	public boolean signoutFromLUMA() {

		menuOption.click();
		hpsignOut.click();
		return signoutConfirm.isDisplayed();
	}

	// Validate if the home page logo is visible
	public boolean validateHomePage() {
		return homepageLogo.isDisplayed();
	}

	// Validate the home page title
	public String validateHomePageTitle() {
		return driver.getTitle();
	}

	// Click on signIn to login to the application
	public LoginPage singIn() {
		hpsignIn.click();

		return new LoginPage();
	}

	// Click on a menu-item to create account
	public CreateAccountPage createAcc() {
		hpcreateAccount.click();

		return new CreateAccountPage();
	}

	// Click on a menu-item to navigate to my account
	public MyAccountPage navigatetoMyAccount() {

		menuOption.click();
		hpmyAccount.click();

		return new MyAccountPage();
	}

	// Navigate to Gear Category
	public OrdersPage navigateToGears() {
		mainitemGear.click();
		return new OrdersPage();
	}

}
