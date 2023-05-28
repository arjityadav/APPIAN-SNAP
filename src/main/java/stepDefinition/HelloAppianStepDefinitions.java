package stepDefinition;

import base.BaseClass;

import io.cucumber.java.en.Given;

public class HelloAppianStepDefinitions extends BaseClass {

    @Given("I login with credentials")
    public void loginWithCredentials() {
        generic.login();
    }

    @Given("I click on Site Page {string}")
    public void clickOnSitePage(String strSitePage) {
        generic.clickOnSitePage(strSitePage);
    }

    @Given("I close the browser")
    public void tearDownSite() {
        appianFeature.tearDown();
    }
}
