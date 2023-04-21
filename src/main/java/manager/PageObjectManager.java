package manager;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import pageObjects.HomePageObjects;

public class PageObjectManager extends BaseClass {
    WebDriver driver;
    HomePageObjects homePage;

    public PageObjectManager(WebDriver driver){
        this.driver=driver;
    }

//    public HomePageObjects getHomePage(){
//        return (homePage == null) ? homePage = new HomePageObjects() : homePage;
//    }
}
