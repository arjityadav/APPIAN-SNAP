package pageObjects;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Report;

public class AppianObjects extends BaseClass {

    @FindBy(id="un")
    WebElement inputUsername;

    @FindBy(id="pw")
    WebElement inputPassword;

    @FindBy(xpath="//*[@value='I Agree']")
    WebElement agreeBtn;

    @FindBy(xpath="//*[@value='Sign In']")
    WebElement signinBtn;

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
        Report.logEvent("pass", "Hit Login Page Url");
        signinBtn.click();
        fixture.waitForSeconds(10);
        Report.logEvent("pass", "Login Successful");
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

    public void clickOnLinkText(String strText) {
        driver.findElement(By.xpath("//*[contains(text(),'" + strText + "')]")).click();
        Report.logEvent("info", "Clicked on " + strText + " link text");

    }

    public void clickOnSitePage(String strSitePageName){
        fixture.clickOnSitePage(strSitePageName);
        Report.logEvent("info", "Clicked on " + strSitePageName + " site page");
    }

    public void closeBrowser(){
        driver.quit();
    }

}
