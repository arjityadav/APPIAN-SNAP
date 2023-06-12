/**
 * Author: Xebia | Appcino
 * Framework: SNAP Appian Test Framework (https://github.com/arjitappcino/Snap-Appian-Test-Framework)
 * Class Description: This is Listener class. It is responsible to capture counts for Failures, Successes, and Skipped test cases.
 */

package utils;

import base.BaseClass;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Arrays;
import java.util.Optional;

public class TestListener extends BaseClass implements TestWatcher {

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
        skippedTests++;
        Report.logEvent("skip","ABORTED");
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        failedTests++;
        Report.logEvent("fail","DISABLED");
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        passedTests++;
        Report.logEvent("pass","PASSED");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        failedTests++;
        String name = String.valueOf(context.getRequiredTestMethod());
        String[] str1 = name.split(" ");
        name = str1[2];
        String[] str2 = name.split("\\(");
        name = str2[0];
        String searchSubstring = name;

        StackTraceElement[] stackTrace = cause.getStackTrace();

        String[] stackTraceStrings = Arrays.stream(stackTrace)
                .map(StackTraceElement::toString)
                .toArray(String[]::new);

        String result = Arrays.stream(stackTraceStrings)
                .filter(str -> str.contains(searchSubstring))
                .findFirst()
                .orElse(null);

        String finalResult = result.substring(result.lastIndexOf("_") + 1);
        Report.logEvent("fail", "Failed at : " + finalResult);
    }
}