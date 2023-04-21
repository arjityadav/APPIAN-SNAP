package base;

import com.appiancorp.ps.automatedtest.common.PropertiesUtilities;
import com.appiancorp.ps.automatedtest.fixture.BaseFixture;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import manager.DriverManager;
import com.appiancorp.ps.automatedtest.fixture.SitesFixture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.openqa.selenium.WebDriver;
import pageObjects.CreateNewRelatedObject;
import pageObjects.GenericObjects;
import pageObjects.HomePageObjects;
import pageObjects.KnowledgePageObjects;
import utils.Report;
import utils.TestListener;
import utils.Utility;

import java.util.Properties;

@ExtendWith(TestListener.class)
public class BaseClass extends BaseFixture {

    public static SitesFixture fixture;
    public static WebDriver driver;
    public static HomePageObjects homePage;
    public static KnowledgePageObjects knowledgePage;
    public static GenericObjects generic;
    public static Utility util;
    public static CreateNewRelatedObject createNew;
    public static ExtentSparkReporter reporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    public static Report report;
    public static String artifactsFolder;
    public static Properties props;
    public static String currentDirectory = System.getProperty("user.dir");

    @BeforeAll
    public static void reporting() {
        report = new Report();
        artifactsFolder = Utility.currentTimestamp();
        extent = report.startReporting();
    }

    @BeforeEach
    public void setUp() {

        fixture = new SitesFixture();
        this.setUpBrowser();

        //Used Class Objects
        homePage = new HomePageObjects(driver);
        knowledgePage = new KnowledgePageObjects(driver);
        generic = new GenericObjects(driver);
        createNew = new CreateNewRelatedObject();
        util = new Utility();

    }

    @AfterAll
    public static void extentFlush() {
        extent.flush();
    }


    public void setUpBrowser() {
        PropertiesUtilities.loadProperties();
        props = PropertiesUtilities.getProps();
        driver = DriverManager.setBrowser(props.getProperty("TEST_BROWSER"));
        //DriverManager.setZoom(props.getProperty("TEST_ZOOM"));
        fixture.setWebDriver(driver);
        fixture.setAppianUrlTo(props.getProperty("TEST_SITE_URL"));
        fixture.setAppianVersionTo(props.getProperty("TEST_SITE_VERSION"));
        fixture.setAppianLocaleTo(props.getProperty("TEST_SITE_LOCALE"));
        fixture.setTimeoutSecondsTo(Integer.valueOf(props.getProperty("TEST_TIMEOUT")));

    }
}
