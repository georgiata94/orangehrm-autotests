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

    @BeforeMethod
    public void setUp() {
        MyWebDriverManager.quitDriver();
        driver = MyWebDriverManager.getDriver();
        ActionHelper.setDriver(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("defaultTimeout"))));
        driver.manage().window().maximize();
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