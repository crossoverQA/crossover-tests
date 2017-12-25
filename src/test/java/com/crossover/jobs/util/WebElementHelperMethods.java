package com.crossover.jobs.util;

import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WebElementHelperMethods extends WebElementCheck {
    private static final Logger logger = LoggerFactory.getLogger(WebElementHelperMethods.class);

    protected WebElementHelperMethods(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getHandleToWindow(final String title) {
        WebDriver popup = null;
        final Set<String> windowIterator = itsDriver.getWindowHandles();
        logger.info("Total no of windows: " + windowIterator.size());

        for (final String window : windowIterator) {
            final String windowHandle = window;
            popup = itsDriver.switchTo().window(windowHandle);
            logger.info("Window Title: " + popup.getTitle() + " URL: " + popup.getCurrentUrl());

            if (popup.getTitle().contains(title)) {
                logger.info("Selected Window Title : " + popup.getTitle());
                return popup;
            }
        }
        final String popupTitle = popup != null ? popup.getTitle() : "<<Missing_Popup>>";
        logger.info("Window Title: " + popupTitle);
        return popup;
    }

    protected WebElement findParentElement(final WebElement element, final int level) {
        WebElement localElement = element;
        for (int i = 0; i < level; i++) {
            localElement = localElement.findElement(By.xpath(".."));
        }
        return localElement;
    }

    protected void clickOnElementAfterReplaceValueFromXpath(
            final String xpath,
            final String target,
            final String relacement) {
        clickOnElementFromXpath(xpath.replace(target, relacement));
    }

    protected void clickOnElementWhenTextMatches(final List<WebElement> listOfElements, final String textToCheck) {
        boolean found = false;
        for (final WebElement element : listOfElements) {
            if (element.getText().trim().contains(textToCheck)) {
                element.click();
                found = true;
                break;
            }
        }
        if (found == false) {
            Assert.fail(String.format("FAIL : '%s' Button is not present ", textToCheck));
        }
    }


    protected void clickClearSendkeys(final WebElement element, final String textToPopulate) {
        waitUntilElementIsClickable(element);
        waitUntilElementIsVisible(element);
        element.clear();
        element.sendKeys(textToPopulate);
    }


    protected void clickOnElementFromXpath(final String xpath) {
        itsDriver.findElement(By.xpath(xpath)).click();
    }


    protected WebElement getElementAfterReplaceValueFromXpath(
            final String xpath,
            final String target,
            final String replacement)
            throws NoSuchElementException {
        return itsDriver.findElement(By.xpath(xpath.replace(target, replacement)));
    }


    protected void clickUsingJavaScript(final WebElement element) {
        try {
            final JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].click();", element);
        } catch (final Exception exception) {
            assertFail("Unable to click on element " + exception.getStackTrace());
        }
    }


    protected void typeEditBox(final WebElement editBox, final String valueToType) {
        waitForVisible(editBox);
        editBox.clear();
        editBox.sendKeys(valueToType);
    }

    // Select radio button for given element

    protected void selectRadioButton(final WebElement rdoElement) {
        waitForVisible(rdoElement);
        if (!rdoElement.isSelected()) {
            rdoElement.click();
        }
    }
    // select combobox by text

    protected void selectListByVisibleText(final WebElement listElement, final String optionText) {
        waitForVisible(listElement);
        final Select selectList = new Select(listElement);
        logger.info("Select list option: " + optionText);
        selectList.selectByVisibleText(optionText);
    }

    // Click on web element

    protected void clickElement(final WebElement webElement) {
        waitForVisible(webElement);
        webElement.click();
    }

    // Set focus element

    protected void focusElement(final WebElement webElement) {
        waitForVisible(webElement);
        Actions builder = new Actions(itsDriver);
        builder.moveToElement(webElement).click(webElement).perform();
    }
    // Get focus element

    protected Boolean getFocusElement(final WebElement webElement) {
        waitForVisible(webElement);
        if (webElement.equals(itsDriver.switchTo().activeElement())) {
            return true;
        }
        return false;
    }

    public void fillText(WebElement webElement, String text) {
        waitForVisible(webElement);
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected void setValue(final String name, final String value) {
        ((JavascriptExecutor) itsDriver).executeScript("document.getElementsByName('" + name
                + "')[0].setAttribute('value', '" + value + "')");
    }

    // Get "value" attribute of an element

    protected String getAttributeValue(final WebElement webElement) {
        return webElement.getAttribute("value");
    }

    // Wait for alert and click "Accept" if flag is true, otherwise simply close it.

    protected void waitAlertAndClose(final boolean isAccept) {
        new WebDriverWait(itsDriver, WAIT_TIMEOUT_DEFAULT).until(ExpectedConditions.alertIsPresent());
        final Alert alert = itsDriver.switchTo().alert();
        if (isAccept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }
}
