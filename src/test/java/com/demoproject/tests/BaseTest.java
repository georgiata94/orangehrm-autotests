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
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Object lock = new Object();

    @BeforeMethod
    public void setUp() {
        synchronized (lock) {
            if (driver == null || !MyWebDriverManager.isSessionActive(driver)) {
                MyWebDriverManager.quitDriver();
                driver = MyWebDriverManager.getDriver();
            }
            ActionHelper.setDriver(driver);
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            if (!Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
                driver.manage().window().maximize();
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        synchronized (lock) {
            try {
                if (ITestResult.FAILURE == result.getStatus()) {
                    String html = driver.getPageSource();
                    logger.info("HTML when failed:\n{}", html);
                    captureScreenshot(result.getMethod().getMethodName());
                }
                logger.info("Cleaning up test...");
                if (driver != null) {
                    logger.debug("Deleting cookies...");
                    driver.manage().deleteAllCookies();
                }
            } finally {
                logger.debug("Quitting driver...");
                MyWebDriverManager.quitDriver();
                driver = null;
                logger.info("Cleanup completed");
            }
        }
    }

    public void captureScreenshot(String methodName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = "screenshot_" + methodName + "_" + timestamp + ".png";
            File destination = new File("screenshots/" + screenshotName);
            FileUtils.copyFile(source, destination);
            logger.info("Screenshot taken: {}", screenshotName);
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: ", e);
        }
    }
}
