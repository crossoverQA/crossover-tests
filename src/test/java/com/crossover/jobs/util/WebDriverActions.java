package com.crossover.jobs.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.Scenario;

public class WebDriverActions
{
	private static final Logger logger = LoggerFactory.getLogger(WebDriverActions.class);

	private static Map<String, WebDriver>	DRIVER_CACHE	= new HashMap<>();
	private static Object			MONITOR		= new Object();

	private WebDriverActions()
	{
	}

	public static WebDriver openBrowser(final Scenario scenario)
		throws ScumberException
	{
		logger.info("Called openBrowser Name: " + scenario.getName() + " ID: " + scenario.getId());

		final String id = scenario.getId();

		synchronized (MONITOR)
		{
			final WebDriver driver = DRIVER_CACHE.get(id);

			if (driver != null)
			{
				// Driver already exists, thus lets reuse it.
				return driver;
			}

			final WebDriver newDriver = WebDriverFactory.createWebDriver();

			initDriver(newDriver);
			DRIVER_CACHE.put(id, newDriver);

			return newDriver;
		}
	}

	private static void freeDriver(final String id)
	{
		synchronized (MONITOR)
		{
			final WebDriver driver = DRIVER_CACHE.remove(id);

			assert driver != null : "Expected to have a driver to free: " + id;
			if (driver != null)
			{
				driver.quit();
			}
		}
	}

	private static void initDriver(final WebDriver driver)
	{
		final String userDir = System.getProperty("user.dir");

		logger.info("Initializing new driver for user: " + userDir);

		final Options manage = driver.manage();

		manage.deleteAllCookies();
		manage.timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1040, 795));

//		manage.window().maximize();
	}

	public static void closeBrowser(final Scenario scenario, final WebDriver driver)
	{
		logger.info("Called closeBrowser Name: " + scenario.getName() + " ID: " + scenario.getId());

		try
		{
			if (scenario.isFailed())
				embedScreenShot(scenario, driver);

			driver.quit();
		}
		finally
		{
			freeDriver(scenario.getId());
		}
	}

	public static boolean hasQuit(WebDriver driver) {
		return ((RemoteWebDriver)driver).getSessionId() == null;
	}

	public static void embedScreenShot(final Scenario scenario, final WebDriver driver)
	{
		logger.info("Called embedScreenShot Name: " + scenario.getName() + " ID: " + scenario.getId());

		try
		{
			scenario.write("Current Page URL is " + driver.getCurrentUrl());

			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		}
		catch (final WebDriverException somePlatformsDontSupportScreenshots)
		{
			logger.warn(somePlatformsDontSupportScreenshots.getMessage());
		}
	}
}
