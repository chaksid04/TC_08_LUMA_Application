package com.magento.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.magento.base.TestBase;

public class MyAccountPage extends TestBase {

	public MyAccountPage() {

		PageFactory.initElements(driver, this);

	}

	/*
	 * Identification of Object Properties to be used by the page interactions
	 */

	@FindBy(xpath = "//span[text()='My Account']")
	WebElement myAccountPageVerification;

	@FindBy(xpath = "//div[contains(text(),'Thank you for registering with Fake Online Clothing Store.')]")
	WebElement acc_creation_Verification;

	@FindBy(xpath = "//div[@class='box-content']/p")
	WebElement user_Verification;

	@FindBy(xpath = "//p[contains(text(),'General Subscription')]")
	WebElement subscription_Verification;

	@FindBy(xpath = "//a[contains(text(),'Address Book')]")
	WebElement addressBook;

	@FindBy(xpath = "//a[contains(text(),'My Orders')]")
	WebElement myOrders;

	@FindBy(xpath = "(//span[text()='Edit'])[2]")
	WebElement editSubscription;

	@FindBy(name = "is_subscribed")
	WebElement changeSubscription;

	@FindBy(xpath = "//span[text()='Save']")
	WebElement saveSubscription;

	@FindBy(xpath = "(//div[@class='box-content']//p)[2]")
	WebElement no_subscription_Verification;

	
	
	public boolean validateMyAccountPage() {
		return myAccountPageVerification.isDisplayed();
	}

	public boolean validateAccountCreation() {
		return acc_creation_Verification.isDisplayed();
	}

	public String getloginDetails() {
		return user_Verification.getText();
	}

	public boolean getNewsletterSubscription() {
		return subscription_Verification.isDisplayed();
	}

	public MyAddressPage openAddressBook() {
		addressBook.click();

		return new MyAddressPage();
	}

	public MyOrderPage openMyOrders() {
		myOrders.click();

		return new MyOrderPage();
	}

	public void cancelSubscription() {

		editSubscription.click();
		changeSubscription.click();
		saveSubscription.click();
	}

	public boolean validateSubscripionCancelled() {

		return no_subscription_Verification.getText().contains("You aren't subscribed to our newsletter.");
	}
}
