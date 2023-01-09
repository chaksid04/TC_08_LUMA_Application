package com.magento.pages;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.magento.base.TestBase;

public class ShoppingCartPage extends TestBase {

	String valSubTotal, valDiscount, valOrderTotal;
	double valFSubTotal, valFDiscount, valFOrderTotal;
	BigDecimal d1;

	public ShoppingCartPage() {

		PageFactory.initElements(driver, this);

	}

	/*
	 * Identification of object properties to be used by the page interactions
	 */

	@FindBy(xpath = "//span[text()='Shopping Cart']")
	WebElement shoppingCartLogo;

	@FindBy(xpath = "//input[@name='qty']")
	WebElement updateItemQuantity;

	@FindBy(xpath = "(//a[@title='Crown Summit Backpack'])[2]/ancestor::td/following-sibling::td[2]/div/div/label/input")
	WebElement originalItemQuantity;

	@FindBy(xpath = "//button[@id='product-updatecart-button']")
	WebElement updateCartButton;

	@FindBy(xpath = "//td[@class='amount']/span")
	WebElement subTotal;

	@FindBy(xpath = "//td[@data-th='Discount']/span/span")
	WebElement discount;

	@FindBy(xpath = "//tr[@class='totals shipping excl']/td/span")
	WebElement shippingCost;

	@FindBy(xpath = "//td[@data-th='Order Total']/strong/span")
	WebElement orderTotal;

	@FindBy(xpath = "//button[@title='Proceed to Checkout']/span")
	WebElement proceedToCheckoutButton;

	@FindBy(xpath = "//span[contains(text(),'Next')]")
	WebElement nextCheckout;

	@FindBy(xpath = "//span[contains(text(),'Place Order')]")
	WebElement btnPlaceOrder;

	// Delete an item from Shopping Cart
	public int deleteItemFromShoppingCart(String itemToRemove) {
		waitSafe(5000);
		driver.findElement(By.xpath("(//a[@title='" + itemToRemove
				+ "'])[2]/ancestor::tr/following-sibling::tr/child::td/div/a[@title='Remove item']")).click();
		waitSafe(1000);
		return driver.findElements(By.xpath("(//a[@title='" + itemToRemove + "'])[2]")).size();
	}

	// Edit an existing item in shopping cart
	public void editItemFromShoppingCart(String itemToEdit) {

		driver.findElement(By.xpath("(//a[@title='" + itemToEdit
				+ "'])[2]/ancestor::tr/following-sibling::tr/child::td/div/a[@class='action action-edit']")).click();
		waitSafe(5000);

	}

	// Enter updated quantity of the product
	public void enterUpdatedQuantity(String quantityOfProduct) {

		updateItemQuantity.clear();
		updateItemQuantity.sendKeys(quantityOfProduct);
	}

	// Fetch the existing quantity of the item
	public String fetchExistingQuantity() {

		return originalItemQuantity.getText();
	}

	// Validate Shopping Cart Page
	public boolean validateShoppingCartPage() {
		return shoppingCartLogo.isDisplayed();

	}

	// Update sopping cart
	public void updateCart() {

		updateCartButton.click();
		waitSafe(5000);
	}

	// Validate whether the total value of product is properly calculated
	public boolean validateTotals() {

		valSubTotal = subTotal.getText();
		valSubTotal = valSubTotal.substring(1);
		valDiscount = discount.getText();
		valDiscount = valDiscount.substring(2);
		valOrderTotal = orderTotal.getText();
		valOrderTotal = valOrderTotal.substring(1);
		valFSubTotal = Double.parseDouble(valSubTotal);
		valFDiscount = Double.parseDouble(valDiscount);
		valFOrderTotal = Double.parseDouble(valOrderTotal);

		d1 = new BigDecimal(valFSubTotal).subtract(new BigDecimal(valFDiscount));

		d1 = d1.setScale(1, RoundingMode.HALF_DOWN);

		if (d1.equals(BigDecimal.valueOf(valFOrderTotal))) {
			return true;

		} else {

			return false;
		}

	}

	// Click on Proceed to checkout button
	public void clickProceedToCheckout() {
		waitSafe(4000);
		proceedToCheckoutButton.click();
		waitSafe(6000);
	}

	// Click on Next button
	public void clickNextCheckout() {

		nextCheckout.click();
		waitSafe(4000);
	}

	// Select shipping method based on User Input
	public void selectShippingMethod(String shippingMethod) {

		driver.findElement(By.xpath("//td[contains(text(),'" + shippingMethod + "')]/preceding-sibling::td/input"))
				.click();
	}

	// Click on Place Order
	public void placeOrder() {
		waitSafe(4000);
		btnPlaceOrder.click();
		waitSafe(4000);
	}

}
