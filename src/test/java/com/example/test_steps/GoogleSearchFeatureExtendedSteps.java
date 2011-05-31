package com.example.test_steps;

import java.text.ParseException;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.example.base.CommandWrapper;
import com.example.base.TestUtils;
import com.example.base.ThreadLocalSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;

import cuke4duke.annotation.I18n;

/**
*
* @author: PMAC
* Date: 25-Apr-2011
*/

public class GoogleSearchFeatureExtendedSteps {

    @I18n.EN.Given("^Using The Browser (.*)$")
    public void usingTheBrowser(String browserName) throws Exception {
    	TestUtils.init();
    	TestUtils.setUp(browserName);
    }

    @I18n.EN.Given("^Searching For (.*)$")
    public void searchingForSearchString(String searchString) throws InterruptedException, ParseException {
    	//ThreadLocalSelenium.getTestVars().driver.get(ThreadLocalSelenium.getTestVars().BASE_URL);
    	TestUtils.pause(2000);
    	CommandWrapper.type("//input[@name='q']", searchString, "xpath");

    }

    @I18n.EN.When("^The Form is Submitted$")
    public void theSearchIsSubmitted() throws InterruptedException, ParseException {
    	CommandWrapper.click("//input[@name='btnG']", "xpath");
        TestUtils.pause(2000);

    }

    @I18n.EN.Then("^The Window Title Should Contain (.*)$")
    public void theBrowserTitleShouldHaveSearchString(String searchString) throws InterruptedException, ParseException {
    	Assert.assertTrue(CommandWrapper.getTitle().toLowerCase().contains(searchString.toLowerCase()));
    	TestUtils.tearDown();
    }
}