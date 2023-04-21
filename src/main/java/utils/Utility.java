package utils;

import base.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import javax.imageio.ImageIO;

public class Utility extends BaseClass {

    static TakesScreenshot finalScrShot;

    public String screenshotBase64() {
        String screenshotBase64 = "";
        try {
            finalScrShot = ((TakesScreenshot) driver);
            File src = finalScrShot.getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(src);

            screenshotBase64 = "data:image/jpg;base64," + Base64.getEncoder().encodeToString(fileContent);


            String directory = "./testResults/" + artifactsFolder + "/screenshots";
            Path path = Paths.get(directory);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS");
            String fileName = "screenshot_" + dateFormat.format(new Date()) + ".png";

            File destFile = new File(directory, fileName);

            ImageIO.write(ImageIO.read(src), "png", destFile);
        } catch (IOException e) {
            System.out.println(e);
        }
        return screenshotBase64;
    }

    @Override
    public int getRandomIntegerFromTo(int min, int max) {
        return super.getRandomIntegerFromTo(min, max);
    }

    public String randomTextGenerate(int charCount) {
        Lorem lorem = LoremIpsum.getInstance();
        String loremIpsum = lorem.getWords(charCount);

        return loremIpsum;
    }

    public static String currentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = now.format(formatter);
        return "ARTIFACTS_" + formattedDateTime;
    }

    public static void createTestArtifactsFolder() {
        String testResultsFolderName = "testResults";
        String testFolderName = artifactsFolder;
        String screenshotsFolderName = "screenshots";

        // Create a File object for the test results folder
        File testResultsFolder = new File(testResultsFolderName);

        // Create the test results folder if it doesn't exist
        if (!testResultsFolder.exists()) {
            boolean isTestResultsFolderCreated = testResultsFolder.mkdir();
            if (isTestResultsFolderCreated) {
                System.out.println("Test results folder created successfully!");
            } else {
                System.out.println("Failed to create test results folder");
            }
        }

        // Create a File object for the test folder inside the test results folder
        File testFolder = new File(testResultsFolder, testFolderName);

        // Create the test folder if it doesn't exist
        if (!testFolder.exists()) {
            boolean isTestFolderCreated = testFolder.mkdir();
            if (isTestFolderCreated) {
                System.out.println("Test folder created successfully!");

                // Create a File object for the screenshots folder inside the test folder
                File screenshotsFolder = new File(testFolder, screenshotsFolderName);

                // Create the screenshots folder
                boolean isScreenshotsFolderCreated = screenshotsFolder.mkdir();
                if (isScreenshotsFolderCreated) {
                    System.out.println("Screenshots folder created successfully!");
                } else {
                    System.out.println("Failed to create screenshots folder");
                }
            } else {
                System.out.println("Failed to create test folder");
            }
        } else {
            System.out.println("Test folder already exists");
        }
    }

    public static String capitalizeText(String input){
        String[] words = input.split(" ");
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0) {
                output.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase());
            } else {
                output.append(" ").append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase());
            }
        }

        return output.toString();
    }
}

