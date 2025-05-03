package com.demoproject.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

public final class ActionHelper {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(ActionHelper.class);

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

    public static List<WebElement> findElements(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static void click(By locator) {
        WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public static WebElement findElement(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public static void type(By locator, String text) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));

        try {
            element.clear();
            element.sendKeys(text);
            if (!Objects.equals(element.getDomProperty("value"), text)) {
                throw new Exception("Standard method failed");
            }
        } catch (Exception e) {
            String script = "var elem = arguments[0];" +
                    "elem.value = '';" +
                    "elem.dispatchEvent(new Event('change'));" +
                    "elem.dispatchEvent(new Event('input'));";
            ((JavascriptExecutor)getDriver()).executeScript(script, element);
            element.sendKeys(text);
        }
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

    public static void jsScrollClick(By locator) {
        WebElement element = getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }


    public static void waitForCheckboxToBeEnabled(By locator) {
        getWait().until(d -> d.findElement(locator).isEnabled());
    }


    public static void waitForCheckboxToBeDisabled(By locator) {
        getWait().until(d -> !d.findElement(locator).isEnabled());
    }

    public static void setCheckbox(By locator, boolean shouldBeChecked) {
        WebElement checkbox = getWait().until(ExpectedConditions.elementToBeClickable(locator));
        boolean isChecked = checkbox.isSelected();

        if (isChecked != shouldBeChecked) {
            checkbox.click();
            logger.info("Checkbox at {} set to {}", locator, shouldBeChecked ? "checked" : "unchecked");
        } else {
            logger.info("Checkbox at {} already in desired state: {}", locator, shouldBeChecked ? "checked" : "unchecked");
        }
    }

    public enum CheckboxState {
        ENABLE,
        DISABLE
    }

    public static boolean isCurrentUrlContains(String expectedPartialUrl) {
        String currentUrl = Objects.requireNonNull(getDriver().getCurrentUrl());
        return currentUrl.contains(expectedPartialUrl);
    }

}
