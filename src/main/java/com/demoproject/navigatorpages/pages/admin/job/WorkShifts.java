package com.demoproject.navigatorpages.pages.admin.job;

import com.demoproject.navigatorpages.pages.admin.job.workshifts.AddWorkShift;
import com.demoproject.utils.ActionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class WorkShifts extends BaseJobPage<WorkShifts> {

    private static final Logger logger = LogManager.getLogger(WorkShifts.class);

    public AddWorkShift clickAddButton() {
        logger.info("Clicking 'Add' button in Work Shifts.");
        clickAddButtonBase();
        return new AddWorkShift();
    }

    public AddWorkShift editUserByUserName(String userName) {
        logger.info("Editing Work Shift for user: {}", userName);
        editByNameBase(userName);
        return new AddWorkShift();
    }

    public void validateWorkShift(String workshiftName) {

        final By workshiftLocator = By.xpath("//div[@class='oxd-table-card']//div[text()='" + workshiftName + "']");
        final int maxRetries = 3;
        final int retryDelayMs = 1000;

        logger.info("Starting validation of WorkShift: '{}'", workshiftName);

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                logger.debug("Validation attempt {} of {} for WorkShift '{}'", attempt, maxRetries, workshiftName);

                ActionHelper.waitForVisibility(workshiftLocator);
                logger.info("Successfully validated presence of WorkShift '{}'", workshiftName);
                return;

            } catch (TimeoutException e) {
                logger.warn("Attempt {} failed - WorkShift '{}' not immediately visible: {}",
                        attempt, workshiftName, e.getMessage());

                if (attempt < maxRetries) {
                    logger.debug("Waiting {}ms before retry...", retryDelayMs);
                    try {
                        Thread.sleep(retryDelayMs);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        logger.error("Validation thread interrupted while waiting for retry");
                        throw new RuntimeException("WorkShift validation interrupted", ie);
                    }

                    logger.debug("Refreshing the view before retry...");
                    ActionHelper.waitForPageLoad();
                }
            } catch (Exception e) {
                logger.error("Unexpected error during WorkShift validation: {}", e.getMessage());
                throw new RuntimeException("Failed to validate WorkShift '" + workshiftName + "'", e);
            }
        }

        if (!ActionHelper.isVisible(workshiftLocator)) {
            String errorMsg = String.format("WorkShift '%s' not found after %d attempts", workshiftName, maxRetries);
            logger.error(errorMsg);
            throw new AssertionError(errorMsg);
        }

        logger.info("WorkShift validation completed successfully for '{}'", workshiftName);
    }
}
