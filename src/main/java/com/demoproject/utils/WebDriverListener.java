package com.demoproject.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebDriverListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverListener.class);
    @Override
    public void onFinish(ITestContext context) {

        if (MyWebDriverManager.getDriver() != null) {
            MyWebDriverManager.quitDriver();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = MyWebDriverManager.getDriver();
        if (driver != null) {
            captureScreenshot(result.getMethod().getMethodName());
        }
    }

    public void captureScreenshot(String methodName) {
        WebDriver driver = MyWebDriverManager.getDriver();
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = "screenshot_" + methodName + "_" + timestamp + ".png";

            File directory = new File("screenshots");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File destination = new File("screenshots/" + screenshotName);
            FileUtils.copyFile(source, destination);
            logger.info("Screenshot taken: {}", screenshotName);
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: ", e);
        }
    }
}