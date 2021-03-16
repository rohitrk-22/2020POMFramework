 package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the driver on basis of given browser
	 * value...
	 * 
	 * @param browserName
	 * @return WebDriver
	 */

	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		highlight = prop.getProperty("highlight").trim();
		optionsManager = new OptionsManager(prop);
		System.out.println("Browser name is : " + browserName);
		

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
//			driver= new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			driver= new FirefoxDriver(optionsManager.getFireFocOption());
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFocOption()));
		} else if (browserName.equalsIgnoreCase("safari")) {
//			driver= new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Browser is not found...please pass the correct browser name" + browserName);
		}
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());

		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize the properties from property file
	 * 
	 * @return Properties prop
	 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;

		String env = System.getProperty("env");
		if (env == null) {
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {

			try {
				switch (env) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;

				default:
					System.out.println("Please pass the right env value...");
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();

			}

		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}
	
	/**
	 * Take screenshot
	 */
	
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;

	}


}
