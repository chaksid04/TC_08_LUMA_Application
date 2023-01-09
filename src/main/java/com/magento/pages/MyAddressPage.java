package com.magento.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.magento.base.TestBase;

public class MyAddressPage extends TestBase {

	public MyAddressPage() {

		PageFactory.initElements(driver, this);
	}
	
	/*
	 *Identification of object properties to be used by the page interactions 
	 */

	@FindBy(xpath = "//span[contains(text(),'Add New Address')]")
	WebElement addressPage;

	@FindBy(name = "firstname")
	WebElement addressFirstName;

	@FindBy(name = "lastname")
	WebElement addressLastName;

	@FindBy(name = "company")
	WebElement addressCompany;

	@FindBy(name = "telephone")
	WebElement addressTelephone;

	@FindBy(id = "street_1")
	WebElement addressStreet1;

	@FindBy(id = "street_2")
	WebElement addressStreet2;

	@FindBy(id = "street_3")
	WebElement addressStreet3;

	@FindBy(name = "city")
	WebElement addressCity;

	@FindBy(name = "region_id")
	WebElement addressState;

	@FindBy(name = "postcode")
	WebElement addressPCode;

	@FindBy(name = "country_id")
	WebElement addressCntry;

	@FindBy(xpath = "//span[text()='Save Address']")
	WebElement addressSave;

	@FindBy(xpath = "//div[text()='You saved the address.']")
	WebElement addressSaveValidation;

	@FindBy(xpath = "//span[text()='Change Billing Address']")
	WebElement editAddress;

	@FindBy(xpath = "//div[@class='box-content']//address")
	WebElement savedAddress;

	@FindBy(xpath = "//div[@class='box-content']/child::address/a")
	WebElement savedPhoneNumber;

	@FindBy(xpath = "//span[text()='Add New Address']")
	WebElement addnewAddress;

	@FindBy(xpath = "//table[@id='additional-addresses-table']")
	WebElement additionalAddressTable;

	
	//Validate new address page
	public boolean validatenewAddressPage() {

		return addressPage.isDisplayed();
	}

	//Enter|Update Address
	public void enterUpdateAddress(String firstName, String lastName, String company, String phoneNumber,
			String streetAddress1, String streetAddress2, String streetAddress3, String cityAddress,
			String stateAddress, String addressPostCode, String addressCountry) {

		if (!"".equals(firstName)) {
			addressFirstName.clear();
			addressFirstName.sendKeys(firstName);
		}

		if (!"".equals(lastName)) {
			addressLastName.clear();
			addressLastName.sendKeys(lastName);
		}
		
		if (!"".equals(company)) {
			addressCompany.clear();
			addressCompany.sendKeys(company);
		}

		if (!"".equals(phoneNumber)) {
			addressTelephone.clear();
			addressTelephone.sendKeys(phoneNumber);
		}
		
		if (!"".equals(streetAddress1)) {
			addressStreet1.clear();
			addressStreet1.sendKeys(streetAddress1);
		}
		
		if (!"".equals(streetAddress2)) {
			addressStreet2.clear();
			addressStreet2.sendKeys(streetAddress2);
		}
		
		if (!"".equals(streetAddress3)) {
			addressStreet3.clear();
			addressStreet3.sendKeys(streetAddress3);
		}
		
		if (!"".equals(cityAddress)) {
			addressCity.clear();
			addressCity.sendKeys(cityAddress);
		}
		
		if (!"".equals(stateAddress)) {
			selectValueFromDropdown(addressState, stateAddress);
		}
		
		if (!"".equals(addressPostCode)) {
			addressPCode.clear();
			addressPCode.sendKeys(addressPostCode);
		}
		
		if (!"".equals(addressCountry)) {
			selectValueFromDropdown(addressCntry, addressCountry);
		}
		


		addressSave.click();
	}
	
	//Validate Address is Saved
	public boolean validateAddressSaved() {

		return addressSaveValidation.isDisplayed();
	}

	//Get details from saved address
	public String validatesavedAddress() {

		return savedAddress.getText();
	}

	//Validate phone number
	public String validatePhoneNumber() {

		return savedPhoneNumber.getText();
	}
	
	//Click Edit Address
	public void editAddress( ) {
		
		editAddress.click();
	}
	
	//Add additional address
	public void addAdditionalAddress() {

		addnewAddress.click();
	}

	//Validate additional address created
	public boolean validateAdditionalAddressCreated() {

		return additionalAddressTable.isDisplayed();
	}

}
