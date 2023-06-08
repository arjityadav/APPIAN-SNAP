package testScripts;

import base.BaseClass;
import org.junit.jupiter.api.Test;

public class SampleTest extends BaseClass {

    @Test
    public void sampleLogin(){
        logger = extent.createTest("Sample Test Login");
        appianObject.login();
//        generic.clickOnLinkText("QA Practice");
        appianObject.clickOnLinkText("Add User");
        fixture.populateFieldWithValue("Country","India");
//        generic.closeBrowser();
    }

}
