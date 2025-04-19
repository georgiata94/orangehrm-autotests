package com.demoproject.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MyWebDriverManager {

    private WebDriver driver;
    private static MyWebDriverManager instance;

    private MyWebDriverManager() {}

    public static synchronized MyWebDriverManager getInstance() {
        if (instance == null) {
            instance = new MyWebDriverManager();
        }
        return instance;
    }


    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private void initDriver() {
        String browser = ConfigReader.getProperty("browser");

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}