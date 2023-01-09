package com.magento.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.magento.base.TestBase;

public class MyOrderPage extends TestBase {

	public MyOrderPage() {
		PageFactory.initElements(driver, this);
	}

	/*
	 * Identification of object properties to be used by the page interactions
	 */
	@FindBy(xpath = "//table[@id='my-orders-table']")
	WebElement myOrderPresent;

	@FindBy(xpath = "//a[@class='action order']/span[contains(text(),'Reorder')]")
	WebElement linkToReorder;

	// Validate my order is present or not
	public boolean isMyOrderPresent() {

		return myOrderPresent.isDisplayed();
	}

	// Click on Reorder button to reorder an existing order
	public ShoppingCartPage clickToReorder() {

		linkToReorder.click();
		waitSafe(5000);

		return new ShoppingCartPage();
	}

}
