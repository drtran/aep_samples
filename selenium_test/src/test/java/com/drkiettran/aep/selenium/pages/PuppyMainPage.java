package com.drkiettran.aep.selenium.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class PuppyMainPage {

    private Browser browser = null;

    public PuppyMainPage() {
        browser = Browser.getInstance();
    }

    public void visit(String url) {
        browser.goTo(url);
    }

    public void viewDetails(String petName) {
        List<WebElement> names = browser.getDriver().findElements(By.xpath("//div[@class='name']"));
        List<WebElement> values = browser.getDriver().findElements(By.xpath("//input[@value='View Details']"));

        int index = 0;
        for (WebElement name : names) {
            if (name.getText().equals(petName)) {
                values.get(index).click();
                break;
            }
            index++;
        }
    }

    public String getNotice() {
        WebElement notice = browser.getDriver().findElement(By.id("notice"));
        return notice.getText();
    }

}
