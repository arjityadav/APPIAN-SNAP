package manager;

import base.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager extends BaseClass {

    public static WebDriver setBrowser(String browserType){
        WebDriver driver;
//        String currentUser = System.getProperty("user.name");
        switch (browserType){
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                ChromeOptions option = new ChromeOptions();
//                option.addArguments("--headless");
//                option.addArguments("--disable-gpu");
//                option.addArguments("--no-sandbox");
//                option.addArguments("--disable-dev-shm-usage");
//                option.addArguments("--window-size=1920,1080");
                option.addArguments("--disable-infobars");
                option.addArguments("--ignore-ssl-errors=yes");
                option.addArguments("--ignore-certificate-errors");
                option.addArguments("--remote-allow-origins=*");
                option.addArguments("--start-maximized");
                driver = new ChromeDriver(option);
                driver.get("chrome://settings/");
                break;

            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;

            default:
                throw new IllegalStateException("Unexpected Browser value: " + browserType);
        }
        return driver;
    }

    public static void setZoom(String strZoomDecimal){
        String strZoom = util.percentToDecimal(strZoomDecimal);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("chrome.settingsPrivate.setDefaultZoom("+strZoom+");");
    }
}
