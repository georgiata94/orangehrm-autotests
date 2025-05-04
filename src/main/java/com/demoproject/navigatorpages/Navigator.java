package com.demoproject.navigatorpages;

import com.demoproject.navigatorpages.pages.BasePage;
import com.demoproject.navigatorpages.pages.LoginPage;
import com.demoproject.utils.MyWebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Navigator {

    private static Navigator instance;
    private final WebDriver driver;
    protected final Logger logger = LogManager.getLogger(getClass());

    private Navigator(WebDriver driver) {
        this.driver = driver;
    }

    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator(MyWebDriverManager.getDriver());
            instance.logger.info("Navigator instance created.");
        }
        return instance;
    }

    public BasePage getOrange(boolean shouldLogin) {
        if (shouldLogin) {
            logger.info("Login requested with default credentials.");
            LoginPage loginPage = new LoginPage(driver);
            BasePage page = loginPage.login();
            logger.info("Login completed using default credentials.");
            return page;
        } else {
            logger.info("Returning BasePage without login.");
            return new BasePage(driver);
        }
    }

    public BasePage getOrange(String username, String password) {
        logger.info("Login requested with provided credentials for user: {}", username);
        LoginPage loginPage = new LoginPage(driver);
        BasePage page = loginPage.login(username, password);
        logger.info("Login completed for user: {}", username);
        return page;
    }
}
