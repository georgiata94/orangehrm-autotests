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
        if (performNavigation) {
            navigateTo("admin.usermanagement.xpath");
        }
        return new UserManagement();
    }

    public Job getJob(boolean performNavigation) {
        if (performNavigation) {
            navigateTo("admin.job.xpath");
        }
        return new Job();
    }

    public void getOrganization(boolean performNavigation) {
        if (performNavigation) {
            navigateTo("admin.organization.xpath");
        }
    }

    public Qualifications getQualifications(boolean performNavigation) {
        if (performNavigation) {
            navigateTo("admin.qualifications.xpath");
        }
        return new Qualifications();
    }

    public void getNationalities(boolean performNavigation) {
        if (performNavigation) {
            navigateTo("admin.nationalities.xpath");
        }
    }

    public void getCorporateBranding(boolean performNavigation) {
        if (performNavigation) {
            navigateTo("admin.branding.xpath");
        }
    }

    public void getConfiguration(boolean performNavigation) {
        if (performNavigation) {
            navigateTo("admin.configuration.xpath");
        }
    }

    private void navigateTo(String locatorKey) {
        By locator = ButtonManager.get(locatorKey);
        waitForVisibility(locator);
        click(locator);
        logger.info("Navigated using locator: {}", locatorKey);
    }
}
