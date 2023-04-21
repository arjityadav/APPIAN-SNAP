package utils;

import base.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Report extends BaseClass {

    public ExtentReports startReporting() {
        Utility.createTestArtifactsFolder();
        String path = currentDirectory + "/testResults/" + artifactsFolder + "/report.html";
        reporter = new ExtentSparkReporter(path);
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        return extent;
    }
    public static void logEvent(String status, String description) {
        util.screenshotBase64();

        String ssAttach = "<details><summary>" + description + "</summary>" +
                "<img src='" + util.screenshotBase64() + "' alt='Screenshot'/>" +
                "</details>";

        if (status.equals("info"))
            logger.log(Status.INFO, ssAttach);
        else if (status.equals("fail"))
            logger.log(Status.FAIL, ssAttach);
        else if (status.equals("pass"))
            logger.log(Status.PASS, ssAttach);
        else if (status.equals("skip"))
            logger.log(Status.SKIP, ssAttach);
    }
}
