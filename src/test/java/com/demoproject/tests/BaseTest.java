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
        MyWebDriverManager manager = MyWebDriverManager.getInstance();
        driver = manager.getDriver();
        ActionHelper.setDriver(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("defaultTimeout"))));
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            MyWebDriverManager.getInstance().quitDriver();
        }
    }
}