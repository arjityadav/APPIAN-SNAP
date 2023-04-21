package utils;

import base.BaseClass;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import java.util.Arrays;

public class TestListener extends BaseClass implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        String name = String.valueOf(context.getRequiredTestMethod());
        String[] str1 = name.split(" ");
        name = str1[2];
        String[] str2 = name.split("\\(");
        name = str2[0];
        String searchSubstring = name;

        StackTraceElement[] stackTrace = throwable.getStackTrace();

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