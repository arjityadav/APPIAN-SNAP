package pageObjects;

import base.BaseClass;
import org.junit.jupiter.api.Assertions;

public class CreateNewRelatedObject extends BaseClass {

    public void createNewRelatedObject(String strObjectType) {
        String title = "hello";
        report.logEvent("info", "Starting new "+strObjectType+" creation for linking");
        knowledgePage.setCardTitle(title);
        knowledgePage.setCardSummary();
        knowledgePage.attachDocuments();
        knowledgePage.uploadMainImage();
        knowledgePage.uploadArticleType("Image");
        knowledgePage.uploadArticleType("Video");
        knowledgePage.uploadArticleType("Rich Text Editor");
        knowledgePage.uploadArticleType("Link");
        knowledgePage.uploadArticleType("Video");
        knowledgePage.uploadArticleType("Image");
        knowledgePage.uploadArticleType("Rich Text Editor");
        knowledgePage.uploadArticleType("Image");
        knowledgePage.uploadArticleType("Link");
        knowledgePage.uploadArticleType("Video");
        knowledgePage.uploadArticleType("Image");
        knowledgePage.uploadArticleType("Rich Text Editor");
        knowledgePage.uploadArticleType("Link");
        knowledgePage.uploadArticleType("Image");
        knowledgePage.uploadArticleType("Rich Text Editor");
        generic.clickOnButton("Next");
        knowledgePage.setMetadataFields(strObjectType);
        generic.clickOnButton("Next");
        generic.clickOnButton("Next");
        homePage.setComment(3);
        generic.clickOnButton("Next");
        report.logEvent("milestone", "On Summary Page");
        generic.clickOnButton("Save");
        Assertions.assertEquals(generic.verifyText(title),true);
        report.logEvent("info", "Completed "+strObjectType+" creation for linking");
    }
}
