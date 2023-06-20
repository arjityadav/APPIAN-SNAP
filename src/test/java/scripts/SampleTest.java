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
        appianObject.populateFieldWithSingleValue("First Name","user1"); //text field
        appianObject.populateFieldWithSingleValue("Last Name","name1"); //text field
        appianObject.populateFieldWithSingleValue("Address","Patrika, Jaipur"); //paragraph field
        appianObject.populateFieldWithSingleValue("Email","arjit@abc.com"); //email field
        appianObject.populateFieldWithSingleValue("Phone","7894561203"); //phone field
        appianObject.populateFieldWithSingleValue("Gender","Male"); //radio button
        appianObject.populateFieldWithMultipleValues("Interests","Automation Testing,Manual Testing"); //Checkboxes
        appianObject.populateFieldWithSingleValue("Instagram Handle","@arjityadavv"); //text field
        appianObject.populateFieldWithMultipleValues("Languages","Arabic,Hindi"); //dropdown field
        appianObject.populateFieldWithSingleValue("Skill","API"); //dropdown field
        appianObject.populateFieldWithSingleValue("Country","India"); //dropdown field
        appianObject.clickOnButton("SUBMIT");
        appianObject.logout();
        appianObject.closeBrowser();
    }

}
