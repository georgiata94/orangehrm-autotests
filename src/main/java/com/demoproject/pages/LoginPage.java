package com.demoproject.pages;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private final By usernameField = By.xpath("//input[@name='username']");
    private final By passwordField = By.xpath("//input[@name='password']");
    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By loginTextElement = By.xpath("//p[text()='Username : Admin']");
    private final By dashboardLogo = By.xpath("//h6[text()='Dashboard']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public BasePage login(String username, String password) {

        String user = username != null ? username : ConfigReader.getProperty("username");
        String pass = password != null ? password : ConfigReader.getProperty("password");
        driver.get(ConfigReader.getProperty("base.url"));
        ActionHelper.waitForVisibility(loginTextElement);
        ActionHelper.type(usernameField, user);
        ActionHelper.type(passwordField, pass);
        ActionHelper.click(loginButton);
        ActionHelper.waitForVisibility(dashboardLogo);
        return new BasePage(driver);
    }

    public BasePage login(){
        String user =  ConfigReader.getProperty("username");
        String pass =  ConfigReader.getProperty("password");
        driver.get(ConfigReader.getProperty("base.url"));
        ActionHelper.waitForVisibility(loginTextElement);
        ActionHelper.type(usernameField, user);
        ActionHelper.type(passwordField, pass);
        ActionHelper.click(loginButton);
        ActionHelper.waitForVisibility(dashboardLogo);
        return new BasePage(driver);
    }
}
