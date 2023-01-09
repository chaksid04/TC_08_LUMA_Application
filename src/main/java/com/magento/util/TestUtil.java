package com.magento.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class TestUtil {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public static int generateRandomNumber() {

		Random random = new Random();
		int randomNum = random.nextInt(100000);
		return randomNum;
	}
	//Write data to config file
	public static void writeData(String key, String value, String fileName) {

		try {
			String pathin = System.getProperty("user.dir");
			FileInputStream in = new FileInputStream(
					pathin + "/src/main/java/com/magento/config/" + fileName + ".properties");
			Properties props = new Properties();
			props.load(in);
			in.close();
			String pathout = System.getProperty("user.dir");
			FileOutputStream out = new FileOutputStream(
					pathout + "/src/main/java/com/magento/config/" + fileName + ".properties");
			props.setProperty(key, value);
			props.store(out, null);
			out.close();
		} catch (FileNotFoundException ex) {
			// file does not exist
		} catch (IOException ex) {
			// I/O error
		}
	}

}
