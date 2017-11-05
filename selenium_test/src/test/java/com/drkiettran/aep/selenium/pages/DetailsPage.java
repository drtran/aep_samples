package com.drkiettran.aep.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DetailsPage {
	public static final By XPATH_ADOPT_ME_BUTTON = By.xpath( "//input[@value='Adopt Me!']" );
	private Browser browser = null;

	public DetailsPage() {
		browser = Browser.getInstance();
	}
	public void adoptMe() {
		WebElement adoptMe = browser.getDriver().findElement
				(XPATH_ADOPT_ME_BUTTON );
		adoptMe.click();
	}

}
