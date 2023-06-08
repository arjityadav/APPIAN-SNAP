package utils;

import base.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

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

    public static void sendEmail() {
        String recipient = props.getProperty("toEmail");
        String sender = "appcinoqa@gmail.com";
        String password = "eypulhncecqpbjkk";
        String subject = "Test Automation Report";

        String tableHtml = tableHTML();

        // Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart tablePart = new MimeBodyPart();
            tablePart.setContent(tableHtml, "text/html");
            multipart.addBodyPart(tablePart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }

    public static String tableHTML() {
        String tableHTML = null;
        String os = System.getProperty("os.name");
        String browserName = props.getProperty("TEST_BROWSER");
        String suiteName = new File(currentDirectory).getName();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Dear Team,");
            sb.append("<br />");
            sb.append("<br />");
            sb.append("Your test suite run has been completed. Here are the key details:");
            sb.append("<br />");
            sb.append("<br />");
            sb.append("<table width=\"500\" border=\"5\">");
            sb.append("<tr>");
            sb.append("<td><b>Suite Name</b></td>");
            sb.append("<td>" + suiteName + "</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td><b>Host Name</b></td>");
            sb.append("<td>" + InetAddress.getLocalHost().getHostName() + "</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td><b>Operating System</b></td>");
            sb.append("<td>" + os.toUpperCase() + "</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td><b>Browser Name</b></td>");
            sb.append("<td>" + browserName + "</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td><b>Device Type</b></td>");
            sb.append("<td>" + os + "</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td><b>Result</b></td>");
            sb.append("<td><b><font color=\"green\">&nbsp;PASS: &nbsp; " + passedTests + "&nbsp;&nbsp;</font><font><b>&nbsp;|&nbsp;</b></font><font color=\"red\"> &nbsp;F&nbsp;AIL:  " + failedTests + "&nbsp;&nbsp;</font><font><b>&nbsp;|&nbsp;</b></font><font color=\"orange\">&nbsp; SKIP: &nbsp; " + skippedTests + "</font></b></td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("<br />");
            sb.append("<br />");
            sb.append("This email was sent automatically. Please do not reply.");
            sb.append("<br />");
            sb.append("<br />");
            sb.append("Thanks,");
            sb.append("<br />");
            sb.append("Xebia QA Team");

            tableHTML = String.format(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableHTML;
    }
}
