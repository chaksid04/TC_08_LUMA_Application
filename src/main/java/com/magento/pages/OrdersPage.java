package com.magento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.magento.base.TestBase;

public class OrdersPage extends TestBase {

	Actions actions;
	JavascriptExecutor je;

	public OrdersPage() {

		PageFactory.initElements(driver, this);

	}

	/*
	 * Identification of object properties to be used by the page interactions
	 */

	@FindBy(xpath = "//span[text()='Gear']")
	WebElement gearOrderType;

	@FindBy(xpath = "(//span[text()='Gear'])[2]")
	WebElement gearlogo;

	@FindBy(xpath = "//div[@data-role='title' and contains(text(),'Style')]")
	WebElement shoppingOption;

	@FindBy(xpath = "//span[text()='Add to Cart']")
	WebElement addToCartLink;

	@FindBy(xpath = "//span[@class='counter qty']")
	WebElement goToCart;

	@FindBy(xpath = "//span[text()='View and Edit Cart']")
	WebElement viewEditCart;

	// Verify whether Gear order page exists
	public boolean verifyGearOrderPageExists() {
		return gearlogo.isDisplayed();
	}

	// Click on Gear Order
	public void clickGearOrder() {

		gearOrderType.click();
	}

	// Validate order home page exists or not
	public boolean validateOrderHomePageExists() {

		return gearOrderType.isDisplayed();
	}

	// Select the sub item of Gear products
	public void clickGearItem(String gearItem) {

		driver.findElement(By.xpath("(//a[contains(text(),'" + gearItem + "')])[1]")).click();
	}

	// Expand shopping option section
	public void clickShoppingOption() {

		shoppingOption.click();
	}

	// Select the style for filtering
	public void selectStyle(String gearStyle) {

		driver.findElement(By.xpath("//li[@class='item']/a[contains(text(),'" + gearStyle + "')]")).click();
	}

	// Add an item to Cart
	public void addtoCart(String itemName) {

		actions = new Actions(driver);

		WebElement itemOption = driver
				.findElement(By.xpath("//a[@class='product-item-link'][contains(text(),'" + itemName + "')]"));

		actions.moveToElement(itemOption);
		actions.moveToElement(addToCartLink);
		actions.click().build().perform();

	}

	// Navigate to Shopping Cart
	public ShoppingCartPage goToShoppingCart() {
		waitSafe(4000);
		goToCart.click();
		viewEditCart.click();
		waitSafe(4000);
		return new ShoppingCartPage();
	}

}
