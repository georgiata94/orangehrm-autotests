package com.demoproject.navigatorpages.pages;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class MyInfo {

    protected final Logger logger = LogManager.getLogger(getClass());

    public void validateUser(String employeeName, String emplLastName) {
        String expectedUserName = (employeeName + " " + emplLastName).trim();
        logger.info("Expected employee name: '{}'", expectedUserName);

        int maxAttempts = 2;
        int attempt = 0;
        boolean isValidated = false;

        while (attempt < maxAttempts && !isValidated) {
            try {
                ActionHelper.waitForVisibility(ButtonManager.get("my.info.employeeName.xpath"));
                String actualUserName = ActionHelper.getText(ButtonManager.get("my.info.employeeName.xpath")).trim();
                logger.info("Attempt {}: Actual employee name from UI: '{}'", attempt + 1, actualUserName);

                Assert.assertEquals(actualUserName, expectedUserName, "❌ Employee name does not match!");
                logger.info("✅ Employee name matched successfully.");
                isValidated = true;

            } catch (AssertionError | RuntimeException e) {
                attempt++;
                logger.warn("Validation attempt {} failed. Retrying...", attempt);
                if (attempt >= maxAttempts) {
                    logger.error("❌ All validation attempts failed. Expected: '{}'", expectedUserName, e);
                    throw e;
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
