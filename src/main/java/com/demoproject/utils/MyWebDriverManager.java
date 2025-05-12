package com.demoproject.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MyWebDriverManager {
    private static final Logger logger = LogManager.getLogger(MyWebDriverManager.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Map<DriverManagerType, Boolean> driverSetupStatus = new ConcurrentHashMap<>();

    private MyWebDriverManager() {

    }

    public static synchronized WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();

        if (driver == null || !isSessionActive(driver)) {
            quitDriver();
            driver = initDriver();
            driverThreadLocal.set(driver);
        }

        return driver;
    }

    private static WebDriver initDriver() {
        String browserName = ConfigReader.getProperty("browser").toLowerCase();
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));
        int implicitWait = Integer.parseInt(ConfigReader.getProperty("defaultTimeout"));

        DriverManagerType driverType;
        WebDriver driver;

        try {
            switch (browserName) {
                case "chrome":
                    driverType = DriverManagerType.CHROME;
                    setupDriverManager(driverType);

                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) {
                        chromeOptions.addArguments(
                                "--disable-gpu",
                                "--window-size=1920,1080",
                                "--no-sandbox",
                                "--disable-dev-shm-usage",
                                "--headless=new"
                        );
                    }

                    chromeOptions.addArguments(
                            "--start-maximized",
                            "--disable-infobars",
                            "--disable-extensions",
                            "--disable-notifications"
                    );

                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "firefox":
                    driverType = DriverManagerType.FIREFOX;
                    setupDriverManager(driverType);

                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) {
                        firefoxOptions.addArguments("--width=1920", "--height=1080", "--headless=new");
                    }

                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browserName);
            }


            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

            if (!headless) {
                driver.manage().window().maximize();
            }

            logger.info("Initialized {} driver (headless: {})", browserName, headless);
            return driver;

        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver: {}", e.getMessage(), e);
            throw new RuntimeException("WebDriver initialization failed", e);
        }
    }

    private static synchronized void setupDriverManager(DriverManagerType driverType) {
        if (!driverSetupStatus.getOrDefault(driverType, false)) {
            WebDriverManager.getInstance(driverType).setup();
            driverSetupStatus.put(driverType, true);
            logger.debug("Setup WebDriverManager for {}", driverType);
        }
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                try {
                    driver.close();
                } catch (Exception e) {
                    logger.warn("Error closing windows: {}", e.getMessage());
                }
                driver.quit();
            } catch (Exception e) {
                logger.error("Error quitting driver: {}", e.getMessage());
            } finally {
                driverThreadLocal.remove();
            }
        }
    }

    private static boolean isSessionActive(WebDriver driver) {
        try {
            if (driver == null) return false;
            return ((RemoteWebDriver)driver).getSessionId() != null &&
                    !driver.toString().contains("(null)");
        } catch (Exception e) {
            logger.debug("WebDriver session is not active: {}", e.getMessage());
            return false;
        }
    }

    public static void setProxy(String proxyAddress) {
        if (proxyAddress == null || proxyAddress.isEmpty()) {
            throw new IllegalArgumentException("Proxy address cannot be null or empty");
        }

        WebDriver currentDriver = driverThreadLocal.get();
        if (currentDriver != null) {
            throw new IllegalStateException("Cannot set proxy on an already initialized driver");
        }

        String browser = ConfigReader.getProperty("browser").toLowerCase();
        Map<String, String> proxySettings = new HashMap<>();
        proxySettings.put("httpProxy", proxyAddress);
        proxySettings.put("sslProxy", proxyAddress);

        try {
            switch (browser) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setCapability("proxy", proxySettings);
                    WebDriverManager.chromedriver().setup();
                    driverThreadLocal.set(new ChromeDriver(chromeOptions));
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setCapability("proxy", proxySettings);
                    WebDriverManager.firefoxdriver().setup();
                    driverThreadLocal.set(new FirefoxDriver(firefoxOptions));
                    break;

                default:
                    throw new UnsupportedOperationException("Proxy setup not supported for browser: " + browser);
            }
            logger.info("Proxy configured: {}", proxyAddress);
        } catch (Exception e) {
            logger.error("Failed to configure proxy: {}", e.getMessage(), e);
            throw new RuntimeException("Proxy configuration failed", e);
        }
    }
}