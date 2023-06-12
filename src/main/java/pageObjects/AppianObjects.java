/**
 * Author: Xebia | Appcino
 * Framework: SNAP Appian Test Framework (https://github.com/arjitappcino/Snap-Appian-Test-Framework)
 * Class Description: This class is the Appian Objects class. Most of the scenarios would be covered using the methods present in this class, otherwise you can build
 * custom functions in the Project Specific Class.
 */

package pageObjects;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Report;

import java.util.Arrays;
import java.util.List;

public class AppianObjects extends BaseClass {

    @FindBy(id="un")
    WebElement inputUsername;

    @FindBy(id="pw")
    WebElement inputPassword;

    @FindBy(xpath="//*[@value='I Agree']")
    WebElement agreeBtn;

    @FindBy(xpath="//*[@value='Sign In']")
    WebElement signinBtn;

    @FindBy(xpath="//*[@aria-label='Open user options menu']")
    WebElement userProfileIcon;

    @FindBy(xpath="//*[text()='Sign Out']")
    WebElement signoutBtn;

    public AppianObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login() {
        String username = props.getProperty("TEST_USERNAME");
        String password = props.getProperty("TEST_PASSWORD");
        String url = props.getProperty("TEST_SITE_URL");
        driver.get(url);
        agreeBtn.click();
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        Report.logEvent("pass", "Hit Url");
        signinBtn.click();
        fixture.waitForSeconds(10);
        Report.logEvent("pass", "Login Successful");
    }

    public void logout(){
        userProfileIcon.click();
        signoutBtn.click();
    }

    public void clickOnButton(String strButton) {
        fixture.clickOnButton(strButton);
        fixture.waitForProgressBar();
        Report.logEvent("info", "Clicked on " + strButton + " button");
    }

    public boolean verifyTextIsPresent(String strText) {
        WebElement textIsPresent = driver.findElement(By.xpath("//*[contains(text(),'" + strText + "')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", textIsPresent);
        fixture.waitForSeconds(2);
        return textIsPresent.isDisplayed();
    }

    public void clickOnText(String strText) {
        WebElement text = driver.findElement(By.xpath("//*[contains(text(),'" + strText + "')]"));
        text.click();
        fixture.waitForProgressBar();
        fixture.waitForSeconds(1);
        Report.logEvent("info", "Clicked on text: " + strText);

    }

    public void clickOnSitePage(String strSitePageName){
        fixture.clickOnSitePage(strSitePageName);
        Report.logEvent("info", "Clicked on " + strSitePageName + " site page");
    }

    public void setFieldWithSingleValue(String fieldName, String value){
        fixture.populateFieldWithValue(fieldName,value);
        Report.logEvent("info","<b>Set "+fieldName+":</b> "+value);
    }

    public void setFieldWithMultipleValue(String fieldName, String multipleValuesCommaSeparated){
        String[] strValues = multipleValuesCommaSeparated.split(",");
        fixture.populateFieldWith(fieldName,strValues);
        String strValuesString = String.join(",", strValues);
        Report.logEvent("info","Set "+fieldName+": "+strValuesString);
    }

    public void closeBrowser(){
        driver.quit();
    }

}
