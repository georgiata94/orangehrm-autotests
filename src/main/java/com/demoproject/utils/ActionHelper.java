package com.demoproject.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ActionHelper {

    private static final Logger logger = LogManager.getLogger(ActionHelper.class);

    private ActionHelper() {}

    private static WebDriver getDriver() {
        return MyWebDriverManager.getDriver();
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

    public static void switchToWindowByIndex(int windowIndex) {
        try {
            String currentWindow = getDriver().getWindowHandle();
            List<String> windows = new ArrayList<>(getDriver().getWindowHandles());

            if (windowIndex >= 0 && windowIndex < windows.size()) {
                String targetWindow = windows.get(windowIndex);
                if (!currentWindow.equals(targetWindow)) {
                    getDriver().switchTo().window(targetWindow);
                    logger.info("Switched to window with index: {}", windowIndex);
                }
            } else {
                throw new IndexOutOfBoundsException("Window index " + windowIndex + " is out of bounds. Total windows: " + windows.size());
            }
        } catch (Exception e) {
            logger.error("Failed to switch to window by index {}: {}", windowIndex, e.getMessage());
            throw e;
        }
    }

    public static void switchToNewestWindow() {
        List<String> windows = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(windows.getLast());
        logger.info("Switched to the newest window");
    }

    public static void closeWindowByIndex(int windowIndex) {
        List<String> windows = new ArrayList<>(getDriver().getWindowHandles());
        if (windowIndex >= 0 && windowIndex < windows.size()) {
            getDriver().switchTo().window(windows.get(windowIndex));
            getDriver().close();
            logger.info("Closed window with index: {}", windowIndex);
        }
    }

    public static void switchToDefaultWindow() {
        List<String> windows = new ArrayList<>(getDriver().getWindowHandles());
        if (!windows.isEmpty()) {
            getDriver().switchTo().window(windows.getFirst());
            logger.info("Switched back to the default window");
        }
    }

    public static void switchToIframe(By iframeLocator) {
        try {
            WebElement iframe = getWait().until(ExpectedConditions.visibilityOfElementLocated(iframeLocator));
            getDriver().switchTo().frame(iframe);
            logger.info("Switched to iframe located by: {}", iframeLocator);
        } catch (Exception e) {
            logger.error("Failed to switch to iframe: {}", e.getMessage());
            throw e;
        }
    }

    public static void switchToDefaultContent() {
        try {
            getDriver().switchTo().defaultContent();
            logger.info("Returned to default content");
        } catch (Exception e) {
            logger.error("Failed to switch to default content: {}", e.getMessage());
            throw e;
        }
    }
}
