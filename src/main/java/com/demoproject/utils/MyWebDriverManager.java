package com.demoproject.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;

public class MyWebDriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(MyWebDriverManager.class);

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            try {
                initDriver();
            } catch (Exception e) {
                logger.info("Error initializing WebDriver: {}", e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("WebDriver initialization failed", e);
            }
        }
        return driverThreadLocal.get();
    }

    private static void initDriver() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));
        int implicitWaitTimeout = Integer.parseInt(ConfigReader.getProperty("defaultTimeout"));

        try {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) {
                        chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920x1080");
                    }
                    driverThreadLocal.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) {
                        firefoxOptions.addArguments("--headless");
                    }
                    driverThreadLocal.set(new FirefoxDriver(firefoxOptions));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            getDriver().manage().window().maximize();

            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTimeout));

        } catch (Exception e) {
            logger.info("Error while initializing the driver: {}", e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("WebDriver initialization failed", e);
        }
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            try {
                driverThreadLocal.get().quit();
            } catch (Exception e) {
                logger.info("Error while quitting WebDriver: {}", e.getMessage());
            } finally {
                driverThreadLocal.remove();
            }
        }
    }

    public static void setProxy(String proxy) {
        WebDriver driver = getDriver();
        if (driver instanceof ChromeDriver) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--proxy-server=" + proxy);
            driver = new ChromeDriver(options);
        } else if (driver instanceof FirefoxDriver) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-proxy-server=" + proxy);
            driver = new FirefoxDriver(options);
        }
    }
}
