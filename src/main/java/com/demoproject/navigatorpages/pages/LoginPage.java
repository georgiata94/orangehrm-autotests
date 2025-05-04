package com.demoproject.navigatorpages.pages;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import com.demoproject.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public BasePage login(String username, String password) {
        String user = username != null ? username : ConfigReader.getProperty("username");
        String pass = password != null ? password : ConfigReader.getProperty("password");

        logger.info("Attempting to log in with user: {}", user);

        driver.get(ConfigReader.getProperty("base.url"));
        logger.debug("Navigated to URL: {}", ConfigReader.getProperty("base.url"));

        By loginTextElement = ButtonManager.get("login.text.xpath");
        By usernameField = ButtonManager.get("login.username.xpath");
        By passwordField = ButtonManager.get("login.password.xpath");
        By loginButton = ButtonManager.get("login.button.xpath");
        By dashboardLogo = ButtonManager.get("login.success.xpath");

        logger.debug("Waiting for login text element to appear...");
        ActionHelper.waitForVisibility(loginTextElement);

        logger.debug("Typing username...");
        ActionHelper.type(usernameField, user);

        logger.debug("Typing password...");
        ActionHelper.type(passwordField, pass);

        logger.debug("Clicking login button...");
        ActionHelper.click(loginButton);

        logger.debug("Waiting for dashboard logo (successful login)...");
        ActionHelper.waitForVisibility(dashboardLogo);

        logger.info("Login successful for user: {}", user);
        return new BasePage(driver);
    }

    public BasePage login() {
        return login(null, null);
    }
}
