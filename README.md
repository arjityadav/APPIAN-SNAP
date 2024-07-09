# SNAP Appian Automation Framework!
![SnapLogo](lib/SnapLogo/logo.png)
## Introduction

> Introducing SNAP - Simplified Next-gen Automation Platform!
> 
> Streamline your testing process with our cutting-edge framework. SNAP offers cross-browser support, intelligent automation, and seamless integrations. 
> Experience faster, reliable testing and unlock unparalleled efficiency.
> Join the future of automation with SNAP!

## Required System Configuration
1. Intellij Community Edition
2. JDK - 11 (Update compiler to Java 11 on Intellij and Also update the same on global level on your pc)
3. Git Bash

## Steps to run Sample Test present on the framework
1. First open the framework in Intellij Community Edition
2. Right click build.gradle and click on Build Module "Snap-Appian-Test-Framework"
3. After the build is successful
4. Go to 'src/test/java/scripts/SampleTest.java' and Right-click on the file
5. Click Run "SampleTest"

## Steps to configure according to the Project's Needs.

1. Open this project in an IDE, such as [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/#section=windows)
2. Update configurations:
   1. Open file `configs/custom.properties`
   2. Update Project Related configs:
      1. TEST_BROWSER = *Enter Browser Name on which you want to run your tests - CHROME or EDGE*
      2. TEST_SITE_URL = *Enter Website URL you want to test*
      3. Example:
         * TEST_BROWSER = CHROME
         * TEST_SITE_URL = *Present inside the code*

   3. Update User Related Configs
      1. TEST_USERNAME = *Enter Username to login*
      2. TEST_PASSWORD = *Enter Password to login*
      3. Example:
         * TEST_USERNAME = *Present inside the code*
         * TEST_PASSWORD = *Present inside the code*
   
   4. Update Report to Email Related Configs (This feature requires additional security approvals, wont suggest to be used on personal mail ID)
      1. TEST_REPORT_RECEIVER_EMAIL = *Enter Email on which you want to receive test report*
      2. Example:
         * TEST_REPORT_RECEIVER_EMAIL = *PUT IN YOUR MAIL ID*

   5. Intellij Specific Settings
      1. Go to File > Settings > Build, Execution, Deployment > Build Tools > Gradle
      2. Change 'Run Test Using' to _Intellij IDEA_
      3. Change 'Gradle JVM' to 11
   

   

