package com.crossover.jobs.pages.objects;

import java.util.ArrayList;
import java.util.List;

import com.crossover.jobs.util.DataFileReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.crossover.jobs.pages.locators.AllLocators;
import com.crossover.jobs.util.AbstractBasePage;

public class PageObjects extends AbstractBasePage {
    private static final Logger logger = LoggerFactory.getLogger(PageObjects.class);

    private AllLocators locator;

    //all method implementations (objects)

    @Autowired
    public PageObjects(final WebDriver driver) {
        super(driver);
        locator = PageFactory.initElements(driver, AllLocators.class);
    }

    public void verifyHomePageDisplayed() {
        logger.info("Verifying Page Displayed....");
        assertElementIsVisible(locator.homePage, "Home Page not opened");
    }

    public void clickOnHLink(String linkName) {
        WebElement element = itsDriver.findElement(By.xpath(locator.getHLink(linkName)));
        ((JavascriptExecutor) itsDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        clickElement(element);
    }

    public void verifyDefaultJobPage() {
        assertElementIsVisible(locator.jobTitleSearchField, "job Title Search field not visible");
        Assert.assertTrue(itsDriver.getCurrentUrl().endsWith("marketplace/available-jobs"));

    }

    public void focusOnField(String fieldName) {
        if (fieldName.equalsIgnoreCase("job title")) {
            focusElement(locator.jobTitleSearchField);
        }
    }

    public void verifyFocusOnField(String fieldName) {
        if (fieldName.equalsIgnoreCase("job title")) {
            Assert.assertTrue(getFocusElement(locator.jobTitleSearchField));
        }
    }

    public void typeTextInField(String text, String fieldName) {

        waitForVisible(locator.jobTitleSearchField);
        locator.jobTitleSearchField.clear();
        locator.jobTitleSearchField.sendKeys(text);
    }

    public void verifyAttributeValue(String value, String fieldName) {

        waitForVisible(locator.jobTitleSearchField);
        Assert.assertTrue(getAttributeValue(locator.jobTitleSearchField).equals(value));
    }

    public void clickButton(String buttonName) {

        WebElement element = itsDriver.findElement(By.xpath(locator.getButton(buttonName)));
        waitForVisible(element);
        clickElement(element);
    }

    public void clickMobileMenuButton() {

        waitForVisible(locator.mobileMenuButton);
        locator.mobileMenuButton.click();

    }
    public void verifySearchList(String searchText) {

        if (locator.jobResultsList.size() > 0) {
            waitForVisible(locator.jobResultsList.get(0));
            for (WebElement searchListElement : locator.jobResultsList) {
                if (searchListElement.getText().contains(searchText)) {
                    logger.info("Chief Job Found against  " + searchText + " :  " + searchListElement.getText());
                }
            }
        } else {
            logger.info("No Job Found against title : " + searchText);
        }

        int searchCount = locator.jobResultsList.size();
        DataFileReader.writeData("searchListCount", Integer.toString(searchCount));
        logger.info("Search List Count  :  "+searchCount);
    }

    public void chooseOption(String fieldName, String option) {

        clickElement(itsDriver.findElement(By.xpath(locator.getComboBox(fieldName))));
        if (locator.jobListCategory.size() > 0) {
            for (WebElement listElement : locator.jobListCategory) {
                if (listElement.getText().equals(option)) {
                    ((JavascriptExecutor) itsDriver).executeScript("arguments[0].scrollIntoView(true);", listElement);
                    clickElement(listElement);
                    break;
                }
            }
        } else {
            logger.info("No Option Found under combo box");
        }
    }

    public void closeBrowser() {
        itsDriver.quit();
    }

    public void moveBetweenTab(int tabNo) {
        List<String> windowHandles = new ArrayList<String>(itsDriver.getWindowHandles());
        itsDriver.switchTo().window(windowHandles.get(tabNo));
    }
}