package com.demoproject.tests;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ConfigReader;
import com.demoproject.utils.MyWebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Object driverLock = new Object();

    @BeforeMethod
    public void setUp() {
        synchronized (driverLock) {
            try {
                // Clean up existing driver
                MyWebDriverManager.quitDriver();

                // Initialize new driver
                driver = MyWebDriverManager.getDriver();
                if (driver == null) {
                    throw new RuntimeException("Driver initialization failed");
                }

                ActionHelper.setDriver(driver);

                // Configure wait with timeout from properties
                int timeout = Integer.parseInt(ConfigReader.getProperty("defaultTimeout"));
                wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

                // Only maximize if not in headless mode
                if (!Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
                    driver.manage().window().maximize();
                }
            } catch (Exception e) {
                MyWebDriverManager.quitDriver();
                throw new RuntimeException("Test setup failed: " + e.getMessage(), e);
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            if (driver != null) {
                driver.manage().deleteAllCookies();
            }
        } finally {
            MyWebDriverManager.quitDriver();
        }
    }
}