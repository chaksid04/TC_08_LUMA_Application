package com.magento.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.magento.util.TestUtil;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait webDriverWait;
	public static Logger log;

	// Constructor
	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/magento/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Method to initialize the driver actions
	public static void initialization() {

		log = Logger.getLogger("Magento App Log");
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/java/com/magento/config/log4j.properties");
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();
		} else {

			System.out.println("execution on defaul browser....");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);

		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));

		driver.get(prop.getProperty("url"));

		webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));

	}

	// Method to select a value from dropdown
	public void selectValueFromDropdown(WebElement element, String value) {

		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	// Safe wait method for sync
	public void waitSafe(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}

}
