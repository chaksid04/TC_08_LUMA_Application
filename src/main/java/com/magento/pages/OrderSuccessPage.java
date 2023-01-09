package com.magento.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.magento.base.TestBase;

public class OrderSuccessPage extends TestBase {

	Actions actions;
	JavascriptExecutor je;

	public OrderSuccessPage() {

		PageFactory.initElements(driver, this);

	}

	/*
	 *Identification of object properties 
	 *to be used by the page interactions 
	 */
	
	@FindBy(xpath = "//span[text()='Thank you for your purchase!']")
	WebElement orderSuccessMessage;


	@FindBy(xpath = "//div[@class='checkout-success']/p/a/strong")
	WebElement orderNumber;
	
	
	
	
	//Validate Order Success Page is present 
	public boolean validateOrderSuccess() {
		
		return orderSuccessMessage.isDisplayed();
	}
	
	//Get Order Number
	public String getOrderNumber( ) {
		
		return orderNumber.getText();
	}






}


