package com.demoproject.tests;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ConfigReader;
import com.demoproject.utils.MyWebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;


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
    public void tearDown() {
        synchronized (lock) {
            try {
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
}
