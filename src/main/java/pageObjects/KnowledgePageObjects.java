package pageObjects;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Report;
import utils.Utility;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KnowledgePageObjects extends BaseClass {

    int countRichTextEditor = 0;
    int countVideoArticle = 1;
    int countLinkArticle = 1;
    int countImageArticle = 1;

    int countListArticle = 1;

    @FindBy(xpath = "//*[text()='kCardTitle']/following-sibling::*//input")
    WebElement inputTitle;

    @FindBy(xpath = "//textarea")
    WebElement inputSummary;

    @FindBy(xpath = "//input[@multiple]")
    WebElement inputMultiDocs;

    @FindBy(xpath = "//*[not(@multiple) and @type='file']")
    WebElement inputMainImage;

    @FindBy(xpath = "//span[text()='Image']/parent::div/following-sibling::div//input")
    WebElement inputImageArticle;

    @FindBy(xpath = "//*[@id='tinymce']/p")
    WebElement inputRichTextArticle;

    @FindBy(xpath = "//*[text()='Expiry Date']/ancestor::div[2]//input")
    WebElement inputExpiryDate;

    @FindBy(xpath = "//*[text()='Recommended Review Date']/ancestor::div[2]//input")
    WebElement inputReviewDate;

    @FindBy(xpath = "//*[text()='Valid From']/ancestor::div[2]//input")
    WebElement inputValidFromDate;

    @FindBy(xpath = "//input[@value='New List']")
    WebElement inputListName;

    public KnowledgePageObjects(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setCardTitle(String strTitle) {
        strTitle = " - " + strTitle;
        inputTitle.sendKeys(strTitle);
        report.logEvent("pass", "K Card Title set");
    }

    public void setCardSummary() {
        String summary = util.randomTextGenerate(50);
        inputSummary.sendKeys(summary);
        report.logEvent("pass", "K Card summary set");
    }

    public void attachDocuments() {
        inputMultiDocs.sendKeys(currentDirectory + "/testData/DOCUMENT_TEST_1.pdf");
        inputMultiDocs.sendKeys(currentDirectory + "/testData/DOCUMENT_TEST_2.pdf");
        report.logEvent("pass", "Documents uploaded");
    }

    public void uploadMainImage() {
        inputMainImage.sendKeys(currentDirectory + "/testData/test_JPEG_1MB.jpg");
        report.logEvent("pass", "Main Image uploaded");
    }

    public void uploadArticleType(String strArticle) {
        generic.clickOnButton(strArticle);
        switch (strArticle) {

            case "Image":
                if (countImageArticle % 2 == 0) {
                    inputImageArticle.sendKeys(currentDirectory + "/testData/test_PNG_5MB.png");
                } else {
                    inputImageArticle.sendKeys(currentDirectory + "/testData/test_JPEG_1MB.jpg");
                }
                countImageArticle++;
                break;

            case "Video":
                WebElement video = driver.findElement(By.xpath("(//*[text()='Video URL']/ancestor::div[2]//input)[" + countVideoArticle + "]"));
                video.sendKeys("www.example.com/video_" + countVideoArticle + ".mp4");
                countVideoArticle++;
                break;

            case "Rich Text Editor":
                fixture.waitForSeconds(5);
                driver.switchTo().frame(countRichTextEditor);
                driver.findElement(By.id("editorBorderNone")).click();
                driver.switchTo().frame(0);
                String strText = util.randomTextGenerate(100);
                driver.findElement(By.id("tinymce")).sendKeys(strText);
                driver.switchTo().defaultContent();
                countRichTextEditor++;
                break;

            case "Link":
                WebElement linkLabel = driver.findElement(By.xpath("(//*[text()='Link Label']/ancestor::div[2]//input)[" + countLinkArticle + "]"));
                linkLabel.sendKeys("Test Link " + countLinkArticle);

                WebElement source = driver.findElement(By.xpath("(//*[text()='Source']/ancestor::div[2]//input)[" + countLinkArticle + "]"));
                source.sendKeys("https://www.google.com");

                countLinkArticle++;
                break;

            case "List":
                generic.clickOnLinkText("Add new Item");
                generic.clickOnLinkText("Add new Item");

                inputListName.sendKeys("ATMN");
                driver.findElement(By.xpath("//*[@id='workingIndicatorContainer']/following-sibling::div/following-sibling::div//*[text()='DOCUMENT']")).click();
                WebElement inputDocument = driver.findElement(By.xpath("//*[text()='Source']/ancestor::thead/following-sibling::tbody//input[@type='file']"));
                inputDocument.sendKeys(currentDirectory + "/testData/HEAVY_DOC_2.pdf");

                fixture.populateFieldWithValue("List Title", "List Type No. " + countListArticle + "- Public Url");
                fixture.populateFieldWithValue("Source", "https://www.novartis.com");


                break;
        }
        report.logEvent("info", "Clicked and Filled Article type: " + strArticle);
        fixture.waitForProgressBar();
    }

    public void setMetadataFields(String strObjectType) {

        fixture.populateFieldWithValue("Owner / Author", "Arjit Yadav");
        fixture.waitForProgressBar();

        fixture.populateFieldWith("SME Quality Check", new String[]{"Arjit Yadav", "TEST USER01"});
        fixture.waitForProgressBar();

//        fixture.populateFieldWith("SME Group", randomize.randomMultiSelect("SME Group"));
//        fixture.waitForProgressBar();

        fixture.populateFieldWithValue("Approver", "TEST USER04");
        fixture.waitForProgressBar();

        fixture.populateFieldWithValue("Status", "Approved");
        fixture.waitForProgressBar();

        inputExpiryDate.sendKeys("15/07/2023");
        fixture.waitForProgressBar();

        inputValidFromDate.sendKeys("21/09/2023");
        fixture.waitForProgressBar();

//        fixture.populateFieldWith("Explicit Tag", randomize.randomMultiSelect("Explicit Tag"));
//        fixture.waitForProgressBar();

//        randomize.randomMultiSelect("kArticleValidFor");

//        if (strObjectType.contains("CURATED NEWS") || strObjectType.contains("GENERAL KNOWLEDGE")) {
//            fixture.populateFieldWithValue("Category", randomize.randomSingleSelect("Category"));
//            fixture.populateFieldWithValue("Organization", randomize.randomSingleSelect("Organization"));
//            fixture.populateFieldWithValue("ET Applications", randomize.randomSingleSelect("ET Applications"));
//            fixture.populateFieldWithValue("GCO Ways Of Working", randomize.randomSingleSelect("GCO Ways Of Working"));
//            report.logEvent("info", "Filled extra details present on related objects tab!");
//        }
//
//        report.logEvent("pass", "All Meta Data Details filled");
    }

    public void clickCreateNewObject(String strObjectType) {
        WebElement createNewBtn = driver.findElement(By.xpath("//*[text()='" + strObjectType + "']/ancestor::div/following-sibling::div//button[text()='Create New']"));
        createNewBtn.click();
        fixture.waitForProgressBar();
        Report.logEvent("info", "Clicked Create New " + strObjectType + " button");
        createNew.createNewRelatedObject(strObjectType);
    }

    public void addViaSearch(String strObject) {

        WebElement addViaSearchBtn = driver.findElement(By.xpath("//*[text()='" + strObject + "']/ancestor::div[6]/following-sibling::div//button[text()='Add Via Search']"));
        addViaSearchBtn.click();
        fixture.waitForProgressBar();
        Report.logEvent("info", "Clicked Add Via Search button for " + strObject);

        String strObjectType = Utility.capitalizeText(strObject);
        System.out.println(strObjectType);
        if (getNumberOfAvailableObjects(strObjectType) != 0) {

            this.addAllBtn(strObjectType);
            this.removeAllBtn(strObjectType);
            List<WebElement> availableObjects = driver.findElements(By.xpath("//*[contains(text(),'Available " + strObjectType + "')]/ancestor::div[5]/following-sibling::div//label"));
            String selectedObject = availableObjects.get(0).getText();
            availableObjects.get(0).click();
            fixture.waitForProgressBar();
            this.addSelectedBtn(strObjectType);
            this.clickSaveAvailableObjectBtn(strObjectType);
            Report.logEvent("info", "Name of Object Linked to " + strObjectType + " via search: " + selectedObject);
        } else {
            Report.logEvent("info", "No " + strObjectType + " object to link");
        }
    }

    public void addAllBtn(String strObjectType) {
        WebElement addAllBtn = driver.findElement(By.xpath("//*[contains(text(),'Available " + strObjectType + "')]/ancestor::div[5]/following-sibling::div//button[text()='Add All']"));
        addAllBtn.click();
        fixture.waitForProgressBar();
        Report.logEvent("info", "Clicked Add All button for " + strObjectType);
    }

    public void removeAllBtn(String strObjectType) {
        WebElement removeAllBtn = driver.findElement(By.xpath("//*[contains(text(),'Available " + strObjectType + "')]/ancestor::div[5]/following-sibling::div//button[text()='Remove All']"));
        removeAllBtn.click();
        fixture.waitForProgressBar();
        Report.logEvent("info", "Clicked Remove All button for " + strObjectType);
    }

    public void addSelectedBtn(String strObjectType) {
        WebElement addSelectedBtn = driver.findElement(By.xpath("//*[contains(text(),'Available " + strObjectType + "')]/ancestor::div[5]/following-sibling::div//button[text()='Add Selected']"));
        addSelectedBtn.click();
        fixture.waitForProgressBar();
        Report.logEvent("info", "Clicked Add Selected button for " + strObjectType);
    }

    public void clickSaveAvailableObjectBtn(String strObjectType) {
        WebElement saveAvailableObjectBtn = driver.findElement(By.xpath("//*[contains(text(),'Available " + strObjectType + "')]/ancestor::div[5]/following-sibling::div[3]//button[text()='Save']"));
        saveAvailableObjectBtn.click();
        fixture.waitForProgressBar();
        Report.logEvent("info", "Clicked Save button for selected object: " + strObjectType);
    }

    public int getNumberOfAvailableObjects(String strObjectType) {
        WebElement availableObjectsTitle = driver.findElement(By.xpath("//*[contains(text(),'Available " + strObjectType + "')]"));
        String strAvailable = availableObjectsTitle.getText();
        System.out.println("number: " + fetchNumFromWithinBrackets(strAvailable));
        return this.fetchNumFromWithinBrackets(strAvailable);
    }

    public int fetchNumFromWithinBrackets(String strText) {
        Pattern pattern = Pattern.compile("\\((\\d+)\\)");
        Matcher matcher = pattern.matcher(strText);
        int number = 0;
        if (matcher.find()) {
            String numberStr = matcher.group(1);
            number = Integer.parseInt(numberStr);
        }
        return number;
    }


}


