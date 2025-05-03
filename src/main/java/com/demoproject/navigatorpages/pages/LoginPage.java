package com.demoproject.navigatorpages.pages;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import com.demoproject.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public BasePage login(String username, String password) {
        String user = username != null ? username : ConfigReader.getProperty("username");
        String pass = password != null ? password : ConfigReader.getProperty("password");

        driver.get(ConfigReader.getProperty("base.url"));

        By loginTextElement = ButtonManager.get("login.text.xpath");
        By usernameField = ButtonManager.get("login.username.xpath");
        By passwordField = ButtonManager.get("login.password.xpath");
        By loginButton = ButtonManager.get("login.button.xpath");
        By dashboardLogo = ButtonManager.get("login.success.xpath");

        ActionHelper.waitForVisibility(loginTextElement);
        ActionHelper.type(usernameField, user);
        ActionHelper.type(passwordField, pass);
        ActionHelper.click(loginButton);
        ActionHelper.waitForVisibility(dashboardLogo);

        return new BasePage(driver);
    }

    public BasePage login() {
        return login(null, null);
    }
}
