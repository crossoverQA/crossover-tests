package com.crossover.jobs.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WebDriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

    private WebDriverFactory() {
    }

    public static WebDriver createWebDriver()
            throws ScumberException {
        final Properties props = PropertiesLoader.getInstance().load("config/browser.properties");

        final String browser = System.getProperty("browser", props.getProperty("browser"));

        return createWebDriver(browser);
    }

    public static WebDriver createWebDriver(final String browser)
            throws ScumberException {
        logger.info("Creating browser instance: " + browser);

        switch (browser) {
            case "ie":
                return createIeWebDriver();

            case "chrome":
                return createChromeWebDriver();

            case "firefox":
                return createFirefoxWebDriver();
        }

        throw new ScumberException("Unknown browser type: " + browser);
    }

    // create ie webdriver

    private static WebDriver createIeWebDriver() {
        System.setProperty("webdriver.ie.driver", "src/test/java/webdrivers/IEDriverServer.exe");

        final DesiredCapabilities caps = DesiredCapabilities.internetExplorer();

        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        caps.setJavascriptEnabled(true);
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
        caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);

        return new InternetExplorerDriver(caps);
    }

    // create chrome webdriver with arguments

    private static WebDriver createChromeWebDriver() {
        final String nativeOS = System.getProperty("os.name").toLowerCase();
        if (nativeOS.indexOf("linux") >= 0) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/webdrivers/linux/chromedriver");
        } else if (nativeOS.indexOf("mac") >= 0) {
            System.setProperty("webdriver.chrome.driver", "src/test/java/webdrivers/osx/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", "src/test/java/webdrivers/chromedriver.exe");
        }

        final ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", System.getProperty("user.dir"));
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        options.addArguments("--test-type");
        String headlessType = System.getProperty("headless.execution");
        if (headlessType != null && !headlessType.isEmpty() && headlessType.equalsIgnoreCase("Yes")) {
            options.addArguments("--headless");
        }
        options.addArguments("--no-sandbox");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-gl-extensions");
        options.addArguments("--disable-extensions");
        options.setExperimentalOption("prefs", prefs);
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        return new ChromeDriver(caps);
    }

    // create firfox webdriver

    private static WebDriver createFirefoxWebDriver() {
        return new FirefoxDriver(DesiredCapabilities.firefox());
    }
}
