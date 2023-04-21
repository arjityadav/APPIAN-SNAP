package pageObjects;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class GenericObjects extends BaseClass {

    public GenericObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login() {
        String url = props.getProperty("TEST_SITE_URL");
        String username = props.getProperty("TEST_USERNAME");
        String password = props.getProperty("TEST_PASSWORD");
        fixture.loginIntoWithUsernameAndPassword(url, username, password);
        fixture.waitForProgressBar();
        report.logEvent("pass", "Login Successful");
    }

    public void clickOnButton(String strButton) {
        fixture.clickOnButton(strButton);
        fixture.waitForProgressBar();
        report.logEvent("info", "Clicked on " + strButton + " button");
    }

    public boolean verifyText(String strText) {
        WebElement textIsPresent = driver.findElement(By.xpath("//*[contains(text(),'" + strText + "')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", textIsPresent);
        fixture.waitForSeconds(2);
        return textIsPresent.isDisplayed();
    }

    public void clickOnLinkText(String strText) {
        driver.findElement(By.xpath("//*[text()='" + strText + "']")).click();
        report.logEvent("info", "Clicked on " + strText + " link text");

    }

}
