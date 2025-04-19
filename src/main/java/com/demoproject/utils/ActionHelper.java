package com.demoproject.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;

public final class ActionHelper {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private ActionHelper() {}

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    private static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    private static WebDriverWait getWait() {
        int timeout = Integer.parseInt(ConfigReader.getProperty("defaultTimeout"));
        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
    }


    public static void click(By locator) {
        WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }


    public static void type(By locator, String text) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }


    public static boolean isVisible(By locator) {
        try {
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static void waitForVisibility(By locator) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForPresence(By locator) {
        getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForPageLoad() {
        getWait().until(driver ->
                Objects.equals(((JavascriptExecutor) driver).executeScript("return document.readyState"), "complete"));
    }

    public static void waitForUrlToContain(String text) {
        getWait().until(ExpectedConditions.urlContains(text));
    }

    public static String getText(By locator) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }


    public static void scrollToElement(By locator) {
        WebElement element = getDriver().findElement(locator);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public static void selectByVisibleText(By dropdownLocator, String text) {
        WebElement dropdown = getDriver().findElement(dropdownLocator);
        new Select(dropdown).selectByVisibleText(text);
    }


    public static void actionClick(By locator) {
        WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
        new Actions(getDriver()).moveToElement(element).click().perform();
    }


    public static void jsClick(By locator) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }


    public static void waitForCheckboxToBeEnabled(By locator) {
        getWait().until(d -> d.findElement(locator).isEnabled());
    }


    public static void waitForCheckboxToBeDisabled(By locator) {
        getWait().until(d -> !d.findElement(locator).isEnabled());
    }
}
