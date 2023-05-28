package base;

import com.appiancorp.ps.automatedtest.common.PropertiesUtilities;
import com.appiancorp.ps.automatedtest.fixture.BaseFixture;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import manager.DriverManager;
import com.appiancorp.ps.automatedtest.fixture.SitesFixture;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pageObjects.GenericObjects;
import pageObjects.HomePageObjects;
import utils.Report;
import utils.TestListener;
import utils.Utility;

import java.util.Properties;

@ExtendWith(TestListener.class)
public class BaseClass extends BaseFixture {

    public static SitesFixture appianFeature;
    public static WebDriver driver;
    public static HomePageObjects homePage;
    public static GenericObjects generic;
    public static Utility util;
    public static ExtentSparkReporter reporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    public static Report report;
    public static String artifactsFolder;
    public static Properties props;
    public static String currentDirectory = System.getProperty("user.dir");
    public static int passedTests = 0;
    public static int failedTests = 0;
    public static int skippedTests = 0;
    public static String ReportPath;

    @BeforeAll
    public static void reporting() {
        report = new Report();
        artifactsFolder = Utility.currentTimestamp();
        extent = report.startReporting();
    }

    @BeforeEach
    public void setUp() {
        appianFeature = new SitesFixture();
        this.setUpBrowser();
        homePage = new HomePageObjects(driver);
        generic = new GenericObjects(driver);
        util = new Utility();
    }

    @AfterAll
    public static void extentFlush() {
        extent.flush();
        Report.sendEmail();
    }


    public void setUpBrowser() {
        PropertiesUtilities.loadProperties();
        props = PropertiesUtilities.getProps();
        driver = DriverManager.setBrowser(props.getProperty("TEST_BROWSER"));
        DriverManager.setZoom(props.getProperty("TEST_ZOOM"));
        appianFeature.setWebDriver(driver);
        appianFeature.setAppianUrlTo(props.getProperty("TEST_SITE_URL"));
        appianFeature.setAppianLocaleTo(props.getProperty("TEST_SITE_LOCALE"));
        appianFeature.setAppianVersionTo(props.getProperty("TEST_SITE_VERSION"));
        appianFeature.setTimeoutSecondsTo(Integer.valueOf(props.getProperty("TEST_TIMEOUT")));
    }
}
