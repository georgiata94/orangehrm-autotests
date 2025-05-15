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
        try {
            List<WebElement> elements = getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            logger.info("Found {} elements with locator: {}", elements.size(), locator);
            return elements;
        } catch (TimeoutException e) {
            logger.error("Timeout while waiting for elements to be visible with locator: {}", locator);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while finding elements with locator {}: {}", locator, e.getMessage());
            throw e;
        }
    }

    public static void click(By locator) {
        try {
            WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked on element with locator: {}", locator);
        } catch (ElementNotInteractableException e) {
            logger.error("Element not interactable with locator {}: {}", locator, e.getMessage());
            throw e;
        } catch (TimeoutException e) {
            logger.error("Timeout while waiting for element to be clickable with locator: {}", locator);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while clicking element with locator {}: {}", locator, e.getMessage());
            throw e;
        }
    }

    public static WebElement findElement(By locator) {
        try {
            WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Found element with locator: {}", locator);
            return element;
        } catch (TimeoutException e) {
            logger.error("Timeout while waiting for element to be visible with locator: {}", locator);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while finding element with locator {}: {}", locator, e.getMessage());
            throw e;
        }
    }

    public static void type(By locator, String text) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));

        try {
            element.clear();
            element.sendKeys(text);
            if (!Objects.equals(element.getDomProperty("value"), text)) {
                throw new Exception("Standard typing method failed");
            }
            logger.info("Typed text '{}' into element with locator: {}", text, locator);
        } catch (Exception e) {
            logger.warn("Standard typing method failed for locator {}, trying alternative method: {}", locator, e.getMessage());
            try {
                String script = "var elem = arguments[0];" +
                        "elem.value = '';" +
                        "elem.dispatchEvent(new Event('change'));" +
                        "elem.dispatchEvent(new Event('input'));";
                ((JavascriptExecutor)getDriver()).executeScript(script, element);
                element.sendKeys(text);
                logger.info("Used alternative method to type text '{}' into element with locator: {}", text, locator);
            } catch (Exception ex) {
                logger.error("Failed to type text into element with locator {}: {}", locator, ex.getMessage());
                throw ex;
            }
        }
    }

    public static boolean isVisible(By locator) {
        try {
            boolean visible = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
            logger.info("Element with locator {} visibility status: {}", locator, visible);
            return visible;
        } catch (TimeoutException e) {
            logger.info("Element with locator {} is not visible within timeout", locator);
            return false;
        } catch (Exception e) {
            logger.error("Unexpected error checking visibility of element with locator {}: {}", locator, e.getMessage());
            throw e;
        }
    }

    public static void waitForVisibility(By locator) {
        try {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element with locator {} became visible", locator);
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for element to be visible with locator: {}", locator);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error waiting for visibility of element with locator {}: {}", locator, e.getMessage());
            throw e;
        }
    }

    public static void waitForPresence(By locator) {
        try {
            getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
            logger.info("Element with locator {} is present in DOM", locator);
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for element to be present with locator: {}", locator);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error waiting for presence of element with locator {}: {}", locator, e.getMessage());
            throw e;
        }
    }

    public static void waitForPageLoad() {
        try {
            getWait().until(driver ->
                    Objects.equals(((JavascriptExecutor) driver).executeScript("return document.readyState"), "complete"));
            logger.info("Page load completed");
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for page to load");
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error waiting for page load: {}", e.getMessage());
            throw e;
        }
    }

    public static void waitForUrlToContain(String text) {
        try {
            getWait().until(ExpectedConditions.urlContains(text));
            logger.info("URL now contains: {}", text);
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for URL to contain '{}'", text);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error waiting for URL to contain '{}': {}", text, e.getMessage());
            throw e;
        }
    }

    public static String getText(By locator) {
        try {
            WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            String text = element.getText();
            logger.info("Retrieved text '{}' from element with locator: {}", text, locator);
            return text;
        } catch (TimeoutException e) {
            logger.error("Timeout waiting to get text from element with locator: {}", locator);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error getting text from element with locator {}: {}", locator, e.getMessage());
            throw e;
        }
    }

    public static void scrollToElement(By locator) {
        try {
            WebElement element = getDriver().findElement(locator);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element with locator: {}", locator);
        } catch (NoSuchElementException e) {
            logger.error("Element not found for scrolling with locator: {}", locator);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error scrolling to element with locator {}: {}", locator, e.getMessage());
            throw e;
        }
    }

    public static void selectByVisibleText(By dropdownLocator, String text) {
        try {
            WebElement dropdown = getDriver().findElement(dropdownLocator);
            new Select(dropdown).selectByVisibleText(text);
            logger.info("Selected option '{}' from dropdown with locator: {}", text, dropdownLocator);
        } catch (NoSuchElementException e) {
            logger.error("Dropdown or option not found with locator {} and text '{}'", dropdownLocator, text);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error selecting from dropdown with locator {}: {}", dropdownLocator, e.getMessage());
            throw e;
        }
    }

    public static void actionClick(By locator) {
        try {
            WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
            new Actions(getDriver()).moveToElement(element).click().perform();
            logger.info("Performed action click on element with locator: {}", locator);
        } catch (ElementNotInteractableException e) {
            logger.error("Element not interactable for action click with locator {}: {}", locator, e.getMessage());
            throw e;
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for element to be clickable with locator: {}", locator);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error performing action click with locator {}: {}", locator, e.getMessage());
            throw e;
        }
    }

    public static void jsClick(By locator) {
        try {
            WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
            logger.info("Performed JavaScript click on element with locator: {}", locator);
        } catch (NoSuchElementException e) {
            logger.error("Element not found for JavaScript click with locator: {}", locator);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error performing JavaScript click with locator {}: {}", locator, e.getMessage());
            throw e;
        }
    }

    public static void jsScrollClick(By locator) {
        try {
            WebElement element = getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
            logger.info("Performed JavaScript scroll and click on element with locator: {}", locator);
        } catch (NoSuchElementException e) {
            logger.error("Element not found for JavaScript scroll-click with locator: {}", locator);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error performing JavaScript scroll-click with locator {}: {}", locator, e.getMessage());
            throw e;
        }
    }

    public static void waitForCheckboxToBeEnabled(By locator) {
        try {
            getWait().until(d -> d.findElement(locator).isEnabled());
            logger.info("Checkbox with locator {} is now enabled", locator);
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for checkbox to be enabled with locator: {}", locator);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error waiting for checkbox to be enabled with locator {}: {}", locator, e.getMessage());
            throw e;
        }
    }

    public static void waitForCheckboxToBeDisabled(By locator) {
        try {
            getWait().until(d -> !d.findElement(locator).isEnabled());
            logger.info("Checkbox with locator {} is now disabled", locator);
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for checkbox to be disabled with locator: {}", locator);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error waiting for checkbox to be disabled with locator {}: {}", locator, e.getMessage());
            throw e;
        }
    }

    public static void setCheckbox(By locator, boolean shouldBeChecked) {
        try {
            WebElement checkbox = getWait().until(ExpectedConditions.elementToBeClickable(locator));
            boolean isChecked = checkbox.isSelected();

            if (isChecked != shouldBeChecked) {
                checkbox.click();
                logger.info("Checkbox at {} set to {}", locator, shouldBeChecked ? "checked" : "unchecked");
            } else {
                logger.info("Checkbox at {} already in desired state: {}", locator, shouldBeChecked ? "checked" : "unchecked");
            }
        } catch (ElementNotInteractableException e) {
            logger.error("Checkbox not interactable with locator {}: {}", locator, e.getMessage());
            throw e;
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for checkbox to be clickable with locator: {}", locator);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error setting checkbox state with locator {}: {}", locator, e.getMessage());
            throw e;
        }
    }

    public enum CheckboxState {
        ENABLE,
        DISABLE
    }

    public static boolean isCurrentUrlContains(String expectedPartialUrl) {
        try {
            String currentUrl = Objects.requireNonNull(getDriver().getCurrentUrl());
            boolean contains = currentUrl.contains(expectedPartialUrl);
            logger.info("Current URL '{}' contains '{}': {}", currentUrl, expectedPartialUrl, contains);
            return contains;
        } catch (Exception e) {
            logger.error("Error checking if current URL contains '{}': {}", expectedPartialUrl, e.getMessage());
            throw e;
        }
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
        try {
            List<String> windows = new ArrayList<>(getDriver().getWindowHandles());
            getDriver().switchTo().window(windows.getLast());
            logger.info("Switched to the newest window");
        } catch (NoSuchWindowException e) {
            logger.error("No windows available to switch to: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error switching to newest window: {}", e.getMessage());
            throw e;
        }
    }

    public static void closeWindowByIndex(int windowIndex) {
        try {
            List<String> windows = new ArrayList<>(getDriver().getWindowHandles());
            if (windowIndex >= 0 && windowIndex < windows.size()) {
                getDriver().switchTo().window(windows.get(windowIndex));
                getDriver().close();
                logger.info("Closed window with index: {}", windowIndex);
            } else {
                throw new IndexOutOfBoundsException("Window index " + windowIndex + " is out of bounds. Total windows: " + windows.size());
            }
        } catch (Exception e) {
            logger.error("Failed to close window by index {}: {}", windowIndex, e.getMessage());
            throw e;
        }
    }

    public static void switchToDefaultWindow() {
        try {
            List<String> windows = new ArrayList<>(getDriver().getWindowHandles());
            if (!windows.isEmpty()) {
                getDriver().switchTo().window(windows.getFirst());
                logger.info("Switched back to the default window");
            }
        } catch (NoSuchWindowException e) {
            logger.error("Default window not available: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error switching to default window: {}", e.getMessage());
            throw e;
        }
    }

    public static void switchToIframe(By iframeLocator) {
        try {
            WebElement iframe = getWait().until(ExpectedConditions.visibilityOfElementLocated(iframeLocator));
            getDriver().switchTo().frame(iframe);
            logger.info("Switched to iframe located by: {}", iframeLocator);
        } catch (NoSuchFrameException e) {
            logger.error("Iframe not found with locator {}: {}", iframeLocator, e.getMessage());
            throw e;
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for iframe to be visible with locator: {}", iframeLocator);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error switching to iframe: {}", e.getMessage());
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

    public static void uploadFile(By locator, String filePath) {
        try {
            WebElement fileInput = getDriver().findElement(locator);

            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.display='block';", fileInput);
            fileInput.sendKeys(filePath);

            logger.info("Successfully uploaded file '{}' using hidden input with locator: {}", filePath, locator);
        } catch (Exception e) {
            logger.error("Failed to upload file '{}' using locator {}: {}", filePath, locator, e.getMessage());
            throw new RuntimeException("File upload failed", e);
        }
    }
}