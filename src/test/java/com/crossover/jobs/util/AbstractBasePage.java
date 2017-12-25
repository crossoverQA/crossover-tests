package com.crossover.jobs.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractBasePage extends WebElementHelperMethods
{
	protected AbstractBasePage(final WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}

	protected String getElementText(final WebElement element)
	{
		waitForVisible(element);
		final String buttonText = element.getAttribute("value");
		return !buttonText.equals("") ? buttonText : element.getText();
	}


	protected String getElementColor(final WebElement element, final String colorAttribute)
	{
		waitForVisible(element);
		return element.getCssValue(colorAttribute);
	}


	protected String getElementAttribute(final WebElement element, final String attribute)
	{
		return element.getAttribute(attribute);
	}
}
