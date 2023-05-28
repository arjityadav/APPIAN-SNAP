package testScripts;

import base.BaseClass;
import org.junit.jupiter.api.Test;

public class SampleTest extends BaseClass {

    @Test
    public void sampleLogin(){
        logger = extent.createTest("Sample Test Login");
        generic.login();
        generic.clickOnSitePage("QA PRACTICE");
        generic.clickOnLinkText("Add User");
        generic.closeBrowser();
    }

}
