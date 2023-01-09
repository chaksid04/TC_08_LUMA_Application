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
import com.magento.pages.OrderSuccessPage;
import com.magento.pages.OrdersPage;
import com.magento.pages.ShoppingCartPage;
import com.magento.util.TestUtil;

public class CreateOrderTest extends TestBase {

	HomePage hmpg;
	LoginPage loginPage;
	OrdersPage ordersPage;
	ShoppingCartPage shoppingCartPage;
	OrderSuccessPage orderSuccessPage;

	public CreateOrderTest() {
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
		ordersPage = new OrdersPage();
		shoppingCartPage = new ShoppingCartPage();
		orderSuccessPage = new OrderSuccessPage();

	}

	/*
	 * This method validates the Cart creation
	 */
	@Test(priority = 1)
	public void validateCartCreationTest() {

		if (hmpg.validateHomePage()) {
			hmpg.singIn();
			loginPage.login(prop.getProperty("email"), prop.getProperty("password"));

			if (ordersPage.validateOrderHomePageExists()) {
				//Selecting Gear item 
				ordersPage.clickGearOrder();
				
				if (ordersPage.verifyGearOrderPageExists()) {
					//Select type of gear based on user input
					ordersPage.clickGearItem(prop.getProperty("orderitem"));
					
					//Select style of gear based on user input
					ordersPage.clickShoppingOption();
					ordersPage.selectStyle(prop.getProperty("orderitemstyle"));
					
					//Add items to Cart based on user input
					ordersPage.addtoCart(prop.getProperty("orderitem1"));
					ordersPage.addtoCart(prop.getProperty("orderitem2"));
					ordersPage.addtoCart(prop.getProperty("orderitem3"));
					ordersPage.addtoCart(prop.getProperty("orderitem4"));

					log.info("Order items added to cart");
					
					shoppingCartPage = ordersPage.goToShoppingCart();
					
					//Validate Shopping Cart Page navigation
					Assert.assertTrue(shoppingCartPage.validateShoppingCartPage());
					if (shoppingCartPage.validateShoppingCartPage()) {
						log.info("Shopping Cart Navigation Successful");
					}

				}
			}
		}
	}

	
	/*
	 * This method validates update of the shopping cart
	 * 
	 */
	@Test(dependsOnMethods = { "validateCartCreationTest" })
	public void updateShoppingCartTest() {

		if (shoppingCartPage.validateShoppingCartPage()) {

			// Validate removal from shopping cart based on user input
			Assert.assertEquals(0, shoppingCartPage.deleteItemFromShoppingCart(prop.getProperty("itemtobedeleted")));
			log.info("Order item deletion successful from Shopping Cart");

			// Update shopping cart based on user input
			String orignal_quantity = shoppingCartPage.fetchExistingQuantity();
			shoppingCartPage.editItemFromShoppingCart(prop.getProperty("itemtobeedited"));
			shoppingCartPage.enterUpdatedQuantity(prop.getProperty("updatedquantity"));
			shoppingCartPage.updateCart();

			// Validate updated shopping cart
			String updated_quantity = shoppingCartPage.fetchExistingQuantity();
			if (!orignal_quantity.equals(updated_quantity)) {

				Assert.assertTrue(true);
				log.info("ShoppingCart update validation Successful");
			}
		}

	}
	

	/*
	 * This method validates the order placing and store
	 * the order number to the config file
	 */
	@Test(dependsOnMethods = { "updateShoppingCartTest" })
	public void placeOrderTest() {

		// Checkout
		shoppingCartPage.clickProceedToCheckout();
		
		// Select shipping method
		shoppingCartPage.selectShippingMethod(prop.getProperty("shippingmethod"));
		shoppingCartPage.clickNextCheckout();
		
		// Place Order
		shoppingCartPage.placeOrder();
		
		// Validate Order Placed
		Assert.assertEquals(orderSuccessPage.validateOrderSuccess(), true);
		if (orderSuccessPage.validateOrderSuccess()) {
			log.info("Order placing validation successful");
		}

		// Get Order Number
		String orderNum = orderSuccessPage.getOrderNumber();
		
		// Store Order Number
		TestUtil.writeData("orderNumber", orderNum, "config");
		log.info("Order Number is successfully stored in config");
	}
	
	
	/*
	 * This method validates the logout functionality
	 * 
	 */

	@Test(dependsOnMethods = { "placeOrderTest" })
	public void validateLogoutTest() {

		//Logout from the Magento Application
		Assert.assertTrue(hmpg.signoutFromLUMA());
		Boolean flag = hmpg.validateHomePage();
		Assert.assertTrue(flag);
		if (flag) {
			log.info("Logout from the app successful");
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
