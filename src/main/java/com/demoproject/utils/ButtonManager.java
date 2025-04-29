package com.demoproject.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.Properties;
import java.io.InputStream;

public class ButtonManager {
    private static final Properties locatorProperties = new Properties();
    private static final Logger logger = LogManager.getLogger(ButtonManager.class);

    static {
        try {
            loadProperties();
            validateLocators();
            logger.info("ButtonManager initialized successfully");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize ButtonManager", e);
        }
    }

    private static void loadProperties() throws Exception {
        try (InputStream input = ButtonManager.class.getClassLoader()
                .getResourceAsStream("locators/button-locators.properties")) {

            if (input == null) {
                throw new Exception("button-locators.properties not found in classpath");
            }
            locatorProperties.load(input);
        }
    }

    private static void validateLocators() throws Exception {
        for (String key : locatorProperties.stringPropertyNames()) {
            String value = locatorProperties.getProperty(key);
            if (value == null || value.trim().isEmpty()) {
                throw new Exception("Empty locator value for key: " + key);
            }

            if (key.endsWith(".xpath")) {
                if (!value.startsWith("//") && !value.startsWith(".//") && !value.startsWith("(")) {
                    logger.warn("Potentially invalid XPath for {}: {}", key, value);
                }
            }
        }
    }

    public static By get(String locatorKey) {
        String locatorValue = locatorProperties.getProperty(locatorKey);
        if (locatorValue == null) {
            throw new IllegalArgumentException("Locator key '" + locatorKey + "' not found");
        }
        return parseLocator(locatorKey, locatorValue);
    }

    public static By get(String locatorKey, Object... args) {
        String pattern = locatorProperties.getProperty(locatorKey);
        if (pattern == null) {
            throw new IllegalArgumentException("Locator key '" + locatorKey + "' not found");
        }


        logger.debug("[DEBUG] Pattern before: {}", pattern);
        logger.debug("[DEBUG] Arguments: {}", Arrays.toString(args));

        pattern = resolveReferences(pattern);

        String formattedLocator = pattern;
        if (args != null && args.length > 0) {
            formattedLocator = pattern.replace("'{0}'", "'" + args[0] + "'");
        }

        logger.debug("[DEBUG] Formatted locator: {}", formattedLocator);
        return parseLocator(locatorKey, formattedLocator);
    }

    private static String resolveReferences(String pattern) {
        String resolved = pattern;
        int maxAttempts = 10;

        while (resolved.contains("${") && maxAttempts-- > 0) {
            int start = resolved.indexOf("${");
            int end = resolved.indexOf("}", start);
            if (start == -1 || end == -1) break;

            String expression = resolved.substring(start + 2, end);

            String key;
            String arg = null;

            if (expression.contains(":")) {
                String[] parts = expression.split(":", 2);
                key = parts[0];
                arg = parts[1];
            } else {
                key = expression;
            }

            String value = locatorProperties.getProperty(key);
            if (value == null) {
                throw new IllegalArgumentException("Missing property for key: " + key);
            }


            String resolvedValue = resolveReferences(value);
            String finalValue = (arg != null) ? String.format(resolvedValue, arg) : resolvedValue;

            resolved = resolved.substring(0, start) + finalValue + resolved.substring(end + 1);
        }

        if (resolved.contains("${")) {
            throw new IllegalStateException("Unresolved reference in: " + resolved);
        }

        return resolved;
    }



    private static By parseLocator(String locatorKey, String locatorValue) {
        if (!locatorKey.matches(".*\\.(id|css|xpath|name|className)$")) {
            return By.xpath(locatorValue);
        }

        if (locatorKey.endsWith(".id")) {
            return By.id(locatorValue);
        } else if (locatorKey.endsWith(".css")) {
            return By.cssSelector(locatorValue);
        } else if (locatorKey.endsWith(".xpath")) {
            return By.xpath(locatorValue);
        } else if (locatorKey.endsWith(".name")) {
            return By.name(locatorValue);
        } else if (locatorKey.endsWith(".className")) {
            return By.className(locatorValue);
        }
        throw new IllegalArgumentException("Unknown locator type for key: " + locatorKey);
    }

}
