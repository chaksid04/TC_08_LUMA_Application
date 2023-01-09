package com.magento.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.magento.base.TestBase;

public class LoginPage extends TestBase {

	public LoginPage() {

		PageFactory.initElements(driver, this);

	}

	/*
	 * Identification of object properties to be used by the page interactions
	 */

	@FindBy(name = "login[username]")
	WebElement username;

	@FindBy(xpath = "//span[contains(text(),'Customer Login')]")
	WebElement customerLogin;

	@FindBy(name = "login[password]")
	WebElement password;

	@FindBy(xpath = "//button[@name='send']")
	WebElement signIn;

	@FindBy(xpath = "(//a[@href='https://magento.softwaretestingboard.com/customer/account/create/'])[3]")
	WebElement createAccount;

	@FindBy(name = "email")
	WebElement subscribe;

	// Get the title of the login page
	public String validateLoginPageTitle() {

		return driver.getTitle();
	}

	// Validate login page is visible
	public boolean validateLoginPage() {

		return customerLogin.isDisplayed();
	}

	// Click on Create Account link
	public CreateAccountPage createAccount() {
		createAccount.click();

		return new CreateAccountPage();

	}

	// Login with given credentials
	public HomePage login(String un, String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		signIn.click();

		return new HomePage();

	}

}
