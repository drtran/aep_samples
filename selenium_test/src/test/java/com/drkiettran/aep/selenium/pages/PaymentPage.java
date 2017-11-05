package com.drkiettran.aep.selenium.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class PaymentPage {
	private Browser browser = null;

	public PaymentPage() {
		browser = Browser.getInstance();
	}

	public void fillOutInfo(String name, String address, String email, String payType) {
		WebElement orderName = browser.getDriver().findElement(By.xpath("//input[@id='order_name']"));
		orderName.sendKeys(name);

		WebElement orderAddr = browser.getDriver().findElement(By.xpath("//textarea[@id='order_address']"));
		orderAddr.sendKeys(address);

		WebElement orderEmail = browser.getDriver().findElement(By.xpath("//input[@id='order_email']"));
		orderEmail.sendKeys(email);

		WebElement select = browser.getDriver().findElement(By.xpath("//select[@id='order_pay_type']"));
		Select dropDown = new Select(select);
		List<WebElement> options = dropDown.getOptions();
		for (WebElement option : options) {
			if (option.getText().equals(payType)) {
				option.click();
				break;
			}
		}

	}

	public void placeOrder() {
		WebElement commit = browser.getDriver().findElement(By.xpath("//input[@name='commit']"));
		commit.click();
	}

}
