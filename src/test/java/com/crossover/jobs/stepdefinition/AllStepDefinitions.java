package com.crossover.jobs.stepdefinition;

import com.crossover.jobs.util.DataFileReader;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crossover.jobs.pages.objects.PageObjects;
import com.crossover.jobs.util.ScumberException;
import com.crossover.jobs.util.WebDriverActions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AllStepDefinitions {
    private static final Logger logger = LoggerFactory.getLogger(AllStepDefinitions.class);

    private WebDriver driver;
    private PageObjects jobPageObjects;
    private Scenario scenario;

    public AllStepDefinitions() {
        logger.info("Constructor: LoginStepDefinition");
    }

    // this class has all step defination which is using in feature files

    @Before
    public void before(final Scenario scenario)
            throws ScumberException {
        driver = WebDriverActions.openBrowser(scenario);
        jobPageObjects = PageFactory.initElements(driver, PageObjects.class);
        this.scenario = scenario;
    }

    @Given("^navigates to URL : \"([^\"]*)\" application$")
    public void navigateToUI(final String appURL) throws Throwable {
        logger.info("---------Navigate to URL Step Started-------");
        try {
            String redirectUrl = "http://" + appURL;
            driver.get(redirectUrl);
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw e;
        } finally {
            WebDriverActions.embedScreenShot(scenario, driver);
            logger.info("---------Navigate to URL Step Ended-------");

        }
    }

    @Then("^application shows main home page$")
    public void applicationMainHomePage() {
        logger.info("---------application shows main home page Step Started-------");
        try {
            jobPageObjects.verifyHomePageDisplayed();
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw e;
        } finally {
            WebDriverActions.embedScreenShot(scenario, driver);
            logger.info("---------application shows main home page Step Ended-------");
        }
    }

    @When("^click \"([^\"]*)\" link on the page$")
    public void clickLinkOnThePage(String linkName) {
        logger.info("---------click  " + linkName + " link on the page Step Started-------");
        try {
            jobPageObjects.clickOnHLink(linkName);
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw e;
        } finally {
            WebDriverActions.embedScreenShot(scenario, driver);
            logger.info("---------click  " + linkName + " link on the page Step Ended-------");
        }
    }

    @And("^move to (\\d+) tab in browser$")
    public void moveToTabInBrowser(int tabNo) {
        logger.info("---------move to tab Step Started-------");
        try {
            jobPageObjects.moveBetweenTab(tabNo);
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw e;
        } finally {
            WebDriverActions.embedScreenShot(scenario, driver);
            logger.info("---------move to tab Step Ended-------");
        }
    }


    @Then("^verify that default job page is opened$")
    public void verifyThatPageShouldBeOpened() {
        logger.info("---------default job page is opened Step Started-------");
        try {
            jobPageObjects.verifyDefaultJobPage();
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw e;
        } finally {
            WebDriverActions.embedScreenShot(scenario, driver);
            logger.info("---------default job page is opened Step Ended-------");
        }
    }

    @And("^focus on the \"([^\"]*)\" field$")
    public void focusOnTheField(String fieldName) {
        logger.info("---------focus on Step Started-------");
        try {
            jobPageObjects.focusOnField(fieldName);
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw e;
        } finally {
            WebDriverActions.embedScreenShot(scenario, driver);
            logger.info("---------focus on Step Ended-------");
        }
    }

    @Then("^verify that field \"([^\"]*)\" is currently focused$")
    public void verifyThatFieldIsCurrentlyFocused(String fieldName) {
        logger.info("---------Verify focus on Step Started-------");
        try {
            jobPageObjects.verifyFocusOnField(fieldName);
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw e;
        } finally {
            WebDriverActions.embedScreenShot(scenario, driver);
            logger.info("---------Verify focus on Step Ended-------");
        }
    }

    @When("^type \"([^\"]*)\" in \"([^\"]*)\" field$")
    public void typeInField(String searchText, String fieldName) {
        logger.info("---------Type " + searchText + " on field " + fieldName + " Step Started-------");
        try {
            DataFileReader.writeData("jobTileSeach", searchText);
            jobPageObjects.typeTextInField(searchText, fieldName);
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw e;
        } finally {
            WebDriverActions.embedScreenShot(scenario, driver);
            logger.info("---------Type " + searchText + " on field " + fieldName + " Step Ended-------");
        }
    }

    @Then("^verify that field \"([^\"]*)\" has value \"([^\"]*)\" in form$")
    public void verifyThatFieldHasValueInForm(String fieldName, String value) {
        logger.info("---------verify that field value Step Started-------");
        try {
            jobPageObjects.verifyAttributeValue(value, fieldName);
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw e;
        } finally {
            WebDriverActions.embedScreenShot(scenario, driver);
            logger.info("---------verify that field value Step Ended-------");
        }
    }

    @When("^click on \"([^\"]*)\" button$")
    public void clickOnButton(String butonName) {
        logger.info("---------click on " + butonName + " button Step Started-------");
        try {
            jobPageObjects.clickButton(butonName);
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw e;
        } finally {
            WebDriverActions.embedScreenShot(scenario, driver);
            logger.info("---------click on " + butonName + " button Step Ended-------");
        }
    }

    @Then("^verify that results are shown with \"([^\"]*)\"$")
    public void verifyThatResultsAreShownWith(String searchText) {
        logger.info("---------verify that results are shown Step Started-------");
        try {
            jobPageObjects.verifySearchList(searchText);
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw e;
        } finally {
            WebDriverActions.embedScreenShot(scenario, driver);
            logger.info("---------verify that results are shown Step Ended-------");
        }
    }

    @When("^choose \"([^\"]*)\" in \"([^\"]*)\" combobox field$")
    public void chooseInComboboxField(String option, String fieldName) {
        logger.info("---------choose " + option + " from  " + fieldName + " Step Started-------");
        try {
            DataFileReader.writeData("jobCategorySeach", option);
            jobPageObjects.chooseOption(fieldName, option);
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw e;
        } finally {
            WebDriverActions.embedScreenShot(scenario, driver);
            logger.info("---------choose " + option + " from  " + fieldName + " Step Ended-------");
        }
    }

    @After
    @When("^close the browser$")
    public void closeTheBrowser() {
        logger.info("---------closing browser Step Started-------");
        try {
            jobPageObjects.closeBrowser();
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw e;
        } finally {
            WebDriverActions.embedScreenShot(scenario, driver);
            logger.info("---------closing browser Step Ended-------");
        }
    }

    @Then("^application is closed$")
    public void applicationIsClosed() {
        if (!WebDriverActions.hasQuit(driver)) {
            Assert.fail();
        }
    }

    @When("^click on mobile menu button$")
    public void clickOnMobileMenuButton() {
        logger.info("---------click on mobile menu button Step Started-------");
        try {
            jobPageObjects.clickMobileMenuButton();
        } catch (Exception e) {
            logger.error("Exception occurred: ", e);
            throw e;
        } finally {
            WebDriverActions.embedScreenShot(scenario, driver);
            logger.info("---------click on mobile menu button Step Ended-------");
        }
    }
}
