package com.magento.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.magento.base.TestBase;

public class CreateAccountPage extends TestBase {

	public CreateAccountPage() {

		PageFactory.initElements(driver, this);

	}

	/*
	 * Identification of Object Properties to be used by the page interactions
	 */
	@FindBy(name = "firstname")
	WebElement firstname;

	@FindBy(name = "lastname")
	WebElement lastname;

	@FindBy(name = "is_subscribed")
	WebElement subscribeCheckBox;

	@FindBy(name = "email")
	WebElement email;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(name = "password_confirmation")
	WebElement passwordConfirm;

	@FindBy(xpath = "//span[contains(text(),'Create New Customer Account')]")
	WebElement createAccountLabel;

	@FindBy(xpath = "//button[@title='Create an Account']")
	WebElement createAccountBtn;

	@FindBy(id = "newsletter")
	WebElement newsletter;

	@FindBy(xpath = "//button[@title='Subscribe']")
	WebElement subscribeBtn;

	// Page Interactions with identified objects
	public boolean validateCreateAccountPage() {

		return createAccountLabel.isDisplayed();
	}

	public MyAccountPage enterAccountDetails(String firstName, String lastName, String emailID, String passwordData) {

		firstname.sendKeys(firstName);
		lastname.sendKeys(lastName);
		subscribeCheckBox.click();
		email.sendKeys(emailID);
		password.sendKeys(passwordData);
		passwordConfirm.sendKeys(passwordData);
		createAccountBtn.click();

		return new MyAccountPage();
	}

}
