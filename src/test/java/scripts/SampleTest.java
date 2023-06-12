/**
 * Author: Xebia | Appcino
 * Framework: SNAP Appian Test Framework (https://github.com/arjitappcino/Snap-Appian-Test-Framework)
 * Class Description: This is a sample test based on the Demo Form on the Nexus website. Change values accordingly and run it to test.
 */

package scripts;

import base.BaseClass;
import org.junit.jupiter.api.Test;

public class SampleTest extends BaseClass {

    @Test
    public void sampleLogin(){
        logger = extent.createTest("Sample Test Login");
        appianObject.login();
        appianObject.clickOnText("QA Practice"); //site page
        appianObject.clickOnText("Add User"); //card type
        appianObject.setFieldWithSingleValue("First Name","user1"); //text field
        appianObject.setFieldWithSingleValue("Last Name","name1"); //text field
        appianObject.setFieldWithSingleValue("Address","Patrika, Jaipur"); //paragraph field
        appianObject.setFieldWithSingleValue("Email","arjit@abc.com"); //email field
        appianObject.setFieldWithSingleValue("Phone","7894561203"); //phone field
        appianObject.setFieldWithSingleValue("Gender","Male"); //radio button
        appianObject.setFieldWithMultipleValue("Interests","Automation Testing,Manual Testing"); //Checkboxes
        appianObject.setFieldWithSingleValue("Instagram Handle","@arjityadavv"); //text field
        appianObject.setFieldWithMultipleValue("Languages","Arabic,Hindi"); //dropdown field
        appianObject.setFieldWithSingleValue("Skill","API"); //dropdown field
        appianObject.setFieldWithSingleValue("Country","India"); //dropdown field
        appianObject.clickOnButton("SUBMIT");
        appianObject.logout();
        appianObject.closeBrowser();
    }

}
