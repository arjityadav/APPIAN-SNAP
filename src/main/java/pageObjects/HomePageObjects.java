package pageObjects;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects extends BaseClass {

    @FindBy(xpath = "//*[text()='Create New Knowledge']")
    WebElement createNewKnowledgeBtn;

    @FindBy(xpath = "//*[text()='CREATE NEW KNOWLEDGE CARD']")
    WebElement createNewKCard;

    @FindBy(xpath = "//textarea")
    WebElement commentField;

    public HomePageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickCreateNewKCard() {
        createNewKCard.click();
        fixture.waitForProgressBar();
        report.logEvent("info", "Clicked on Create K Card Button");
    }

    public void clickCreateNewKnowledgeBtn() {
        createNewKnowledgeBtn.click();
        fixture.waitForProgressBar();
        report.logEvent("info", "Selected Create New K Card");
    }

    public void selectCard(String strCard) {
        driver.findElement(By.xpath("//li//*[contains(text(),'" + strCard + "')]")).click();
        fixture.waitForProgressBar();
        report.logEvent("info", "Selected on " + strCard + " Card");
    }

    public void setComment(int x) {
        for (int i = 0; i < x; i++) {
            fixture.clickOnButton("Add Comment");
            String strComment = util.randomTextGenerate(10);
            commentField.sendKeys(strComment);
            driver.findElement(By.xpath("//*[text()='KEY METADATA']")).click();
            fixture.waitForSeconds(2);
            fixture.clickOnButton("Submit");
            report.logEvent("info", "Comment added");
        }
    }

}
