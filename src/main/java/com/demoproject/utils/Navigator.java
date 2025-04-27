package com.demoproject.utils;

import com.demoproject.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Navigator {

    private static Navigator instance;
    private final WebDriver driver;
    private boolean isLoggedIn = false;
    protected final Logger logger = LogManager.getLogger(getClass());

    private Navigator(WebDriver driver) {
        this.driver = driver;
    }

    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator(MyWebDriverManager.getDriver());
        }
        return instance;
    }

    public BasePage getOrange() {
        if (!isLoggedIn) {
            LoginPage loginPage = new LoginPage(driver);
            BasePage basePage = loginPage.login();
            isLoggedIn = true;
            return basePage;
        } else {
            logger.info("Вече сте влезли в системата. Вие сте на основната страница.");
            return new BasePage(driver);
        }
    }

    public BasePage getOrange(boolean performNavigation) {
        if (!performNavigation) {
            return new BasePage(driver);
        }
        return getOrange();
    }
}