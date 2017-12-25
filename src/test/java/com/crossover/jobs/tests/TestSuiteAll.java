package com.crossover.jobs.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

// test class file to execute scenarios and used cucumber default reports

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true,
        plugin = {
                "pretty", "html:target/automation/html",
                "json:target/automation/seachJobFilters.json",
                "junit:target/automation/seachJobFilters.xml"},
        glue = {"com.crossover.jobs.stepdefinition"},
        features = {"classpath:feature_files/seachJobFilters.feature"})
public class TestSuiteAll {

}

