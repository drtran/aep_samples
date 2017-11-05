package com.drkiettran.aep.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class deals with the adoption of particular pet.
 */
public class AdoptingPetsPage {
    private Browser browser = null;

    public AdoptingPetsPage() {
        browser = Browser.getInstance();
    }

    public void completeAdoption() {
        WebElement completeTheAdoption = browser.getDriver()
                .findElement(By.xpath("//input[@value='Complete the Adoption']"));
        completeTheAdoption.click();
    }

}
