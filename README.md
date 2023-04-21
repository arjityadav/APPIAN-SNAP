# Appian Selenium API - PageFactory, Extent Reporting with Java

## Introduction

> A template using Appian Selenium API with Java. 

## Usage

1. Open this project in an IDE, such as IntelliJ IDEA or Eclipse.
1. Update configurations:
    1. Open file `configs/custom.properties` 
        1. Update `automated.testing.home` with the path to this installation. 
        Examples: 
            * Windows: `automated.testing.home=C:\\testFolder\\appian-selenium-api-example-java`    
            * Mac: `automated.testing.home=/Users/tester/Desktop/appian-selenium-api-example-java`
        2. Update `chrome.driver.home` with the path to the `Chrome` driver that comes with this installation. 
        Examples:
            * Windows: `automated.testing.home=C:\\testFolder\\appian-selenium-api-example-java\\lib\\drivers\\chromedriver.exe`    
            * Mac: `automated.testing.home=/Users/tester/Desktop/appian-selenium-api-example-java/lib/drivers/chromedriver-mac`
    1. Open file `configs/users.properties`, for each username used in a test add a line in the format of 
    `<username>=<password>` to this file.
        * If you prefer not to disclose your password in plain text, you can also choose to use 
        `com.appiancorp.ps.automatedtest.fixture.BaseFixture.loginIntoWithUsernameAndPassword` fixture method
        to login by passing in the password from a system property value.
1. Open `src/main/java/com/appiancorp/example/HelloAppian.java`:
    1. Update `TEST_SITE_URL` to your Appian URL (ending in `/suite`), such as `protected static String TEST_SITE_URL = "https://example.appiancloud.com/suite";`
    1. Update `TEST_USERNAME` with a username, such as `protected static String TEST_USERNAME = "tester.tester";`
        * Make sure this user exists in `configs/users.properties`
    1. Update `TEST_SITE_VERSION` with a valid Appian site version, such as `protected static String TEST_SITE_VERSION = "21.2"`

