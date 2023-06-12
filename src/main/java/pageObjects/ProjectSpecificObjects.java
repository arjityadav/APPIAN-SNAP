/**
 * Author: Xebia | Appcino
 * Framework: SNAP Appian Test Framework (https://github.com/arjitappcino/Snap-Appian-Test-Framework)
 * Class Description: All the project specific web elements and methods would be stored in this class via PAGE FACTORY APPROACH.
 */

package pageObjects;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectSpecificObjects extends BaseClass {

    @FindBy(xpath = "")
    WebElement sample;

    public ProjectSpecificObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
