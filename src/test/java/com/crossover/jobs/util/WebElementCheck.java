package com.crossover.jobs.util;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class WebElementCheck extends WebElementWait {
    public WebElementCheck(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void assertFail(final String message) {
        Assert.fail(message);
    }

    // assert method for element visibility or display on page

    public void assertElementIsVisible(final WebElement webElement, final String message) {
        waitForVisible(webElement);
        Assert.assertTrue(message, webElement.isDisplayed());
    }

}
