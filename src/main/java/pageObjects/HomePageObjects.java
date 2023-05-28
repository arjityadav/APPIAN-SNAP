package pageObjects;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects extends BaseClass {

    @FindBy(xpath = "")
    WebElement sample;

    public HomePageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
