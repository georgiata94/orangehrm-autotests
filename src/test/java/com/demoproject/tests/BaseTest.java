package com.demoproject.tests;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ConfigReader;
import com.demoproject.utils.MyWebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public abstract class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriverWait wait;

    protected WebDriver getDriver() {
        return MyWebDriverManager.getDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        if (!Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
            getDriver().manage().window().maximize();
        }

        logger.info("New session is started.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        WebDriver driver = null;
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                captureFailureData(result);
            }
        } catch (Exception e) {
            logger.error("Error during teardown for {}: {}",
                    result.getMethod().getMethodName(), e.getMessage());
        } finally {

            MyWebDriverManager.quitDriver();
            logger.info("Driver is closed after test: {}", result.getMethod().getMethodName());
        }
    }

    private void captureFailureData(ITestResult result) {
        try {
             captureScreenshot(result.getName());
        } catch (Exception e) {
            logger.error("Failed to capture failure data: {}", e.getMessage());
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
