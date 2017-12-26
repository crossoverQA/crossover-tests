package com.crossover.jobs.pages.locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * All Locators
 *
 * @author Tauqir Sarwar
 */
public class AllLocators {

    // these are all locators which are using in this job search

    @FindBy(xpath = "//h1[@class='page-title' and contains(text(), 'WE BUILD WORLD-CLASS TEAMS')]")
    public WebElement homePage;

    @FindBy(xpath = "//div[@class='cell title ng-binding']")
    public List<WebElement> jobResultsList;

    @FindBy(xpath = "//input[@ng-model='data.searchTxt']")
    public WebElement jobTitleSearchField;

    @FindBy(xpath = "//li[@class='ui-select-choices-group']/div")
    public List<WebElement> jobListCategory;

    @FindBy(xpath = "//*[@class='glyphicon glyphicon-menu-hamburger pull-right mobile-nav-trigger']")
    public WebElement mobileMenuButton;

    public String getHLink(String linkText) {
        String hLinkXPath = "//*[contains(text(),'" + linkText + "')]";
        return hLinkXPath;
    }

    public String getButton(String buttonName) {
        String buttonXPath = "//button[contains(text(),'" + buttonName + "')]";
        return buttonXPath;
    }

    public String getComboBox(String comboName) {
        String comboBoxXPath = "//span[@class='ng-binding ng-scope' and contains(text(),'" + comboName + "')]";
        return comboBoxXPath;
    }
}