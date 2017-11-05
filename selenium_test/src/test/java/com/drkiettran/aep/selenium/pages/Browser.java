package com.drkiettran.aep.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * We want a 'single' driver at any giving time for testing.
 * <p>
 *
 * <code>
 * -Dwebdriver.driver=[chrome|firefox|internetexplorer]
 * -Dwebdriver.chrome.driver=/usr/local/bin/chromedriver
 * </code>
 *
 * @author ktran
 */
public class Browser {
    private static Browser BROWSER = null;
    private final WebDriver driver;

    private Browser(WebDriver driver) {
        this.driver = driver;
    }

    public static Browser getInstance(WebDriver driver) {
        if (BROWSER != null) return BROWSER;

        BROWSER = new Browser(driver);

        return BROWSER;
    }

    public static Browser getInstance() {
        return BROWSER;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void goTo(String url) {
        driver.get(url);
    }

    public WebElement input(String attr, String using) {
        wait_for_ajax();
        return find("input", attr.toLowerCase(), using);
    }

    private void wait_for_ajax() {
    }

    public WebElement button(String attr, String using) {
        wait_for_ajax();
        return find("button", attr.toLowerCase(), using);
    }

    private WebElement find(String tagName, String attribute, String using) {
        List<WebElement> weList = driver.findElements(By.tagName(tagName));
        for (WebElement we : weList) {
            if (we.getAttribute(attribute).equals(using))
                return we;
        }
        return null;
    }

    public WebElement span(String attr, String using) {
        wait_for_ajax();
        return find("span", attr.toString().toLowerCase(), using);
    }
}
