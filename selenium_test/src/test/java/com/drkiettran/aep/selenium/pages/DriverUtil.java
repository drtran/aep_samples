package com.drkiettran.aep.selenium.pages;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This utility class enable you to get a local browser or a remote browser in your
 * selenium Java code.
 * 
 * You must have the selenium server running using this command:
 * START java -jar ..\bin\selenium-server\selenium-server-standalone-2.45.0.jar
 * You can download the server code from the Selenium HQ website.
 * For simplicity, at this time, I use chrome.
 * 
 * @author ktran
 *
 */
public class DriverUtil {

	public static enum DRIVER_TYPE {REMOTE, LOCAL}

	public static WebDriver getDriver(DRIVER_TYPE type) {
		switch (type) {
		case REMOTE:
			URL serverUrl = getSeleniumServerUrl();
			DesiredCapabilities browserType = getBrowserType();
			return new RemoteWebDriver(serverUrl, browserType);
		case LOCAL:
			return getLocalDriver();
		default:
			break; // ERROR
		}
		return null;
	}

	private static WebDriver getLocalDriver() {
		DesiredCapabilities browserType = getBrowserType();
        return new ChromeDriver(browserType);
	}

	private static DesiredCapabilities getBrowserType() {
		return DesiredCapabilities.chrome();
	}

	private static URL getSeleniumServerUrl() {
		try {
			return new URL("http://127.0.0.1:4444/wd/hub");
		} catch (MalformedURLException e) {
			System.out.println("ERROR: "+e);
		}
		return null;
	};
	
	public static void quit(WebDriver driver) {
		driver.close();
		driver.quit();
	}
	

}
