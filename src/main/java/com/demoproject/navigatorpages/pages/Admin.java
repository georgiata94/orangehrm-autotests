package com.demoproject.navigatorpages.pages;

import com.demoproject.navigatorpages.pages.admin.Job;
import com.demoproject.navigatorpages.pages.admin.Qualifications;
import com.demoproject.navigatorpages.pages.admin.UserManagement;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static com.demoproject.utils.ActionHelper.*;

public class Admin {
    protected final Logger logger = LogManager.getLogger(getClass());

    public UserManagement getUserManagement(boolean performNavigation) {
        logger.info("Accessing User Management.");
        if (performNavigation) {
            logger.info("Navigating to User Management page.");
            navigateTo("admin.usermanagement.xpath");
        }
        return new UserManagement();
    }

    public Job getJob(boolean performNavigation) {
        logger.info("Accessing Job page.");
        if (performNavigation) {
            logger.info("Navigating to Job page.");
            navigateTo("admin.job.xpath");
        }
        return new Job();
    }

    public void getOrganization(boolean performNavigation) {
        logger.info("Accessing Organization page.");
        if (performNavigation) {
            logger.info("Navigating to Organization page.");
            navigateTo("admin.organization.xpath");
        }
    }

    public Qualifications getQualifications(boolean performNavigation) {
        logger.info("Accessing Qualifications page.");
        if (performNavigation) {
            logger.info("Navigating to Qualifications page.");
            navigateTo("admin.qualifications.xpath");
        }
        return new Qualifications();
    }

    public void getNationalities(boolean performNavigation) {
        logger.info("Accessing Nationalities page.");
        if (performNavigation) {
            logger.info("Navigating to Nationalities page.");
            navigateTo("admin.nationalities.xpath");
        }
    }

    public void getCorporateBranding(boolean performNavigation) {
        logger.info("Accessing Corporate Branding page.");
        if (performNavigation) {
            logger.info("Navigating to Corporate Branding page.");
            navigateTo("admin.branding.xpath");
        }
    }

    public void getConfiguration(boolean performNavigation) {
        logger.info("Accessing Configuration page.");
        if (performNavigation) {
            logger.info("Navigating to Configuration page.");
            navigateTo("admin.configuration.xpath");
        }
    }

    private void navigateTo(String locatorKey) {
        By locator = ButtonManager.get(locatorKey);
        waitForVisibility(locator);
        click(locator);
        logger.info("Navigation performed.");
    }
}
