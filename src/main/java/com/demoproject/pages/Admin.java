package com.demoproject.pages;

import com.demoproject.pages.admin.UserManagement;
import com.demoproject.utils.ActionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class Admin {

    protected final Logger logger = LogManager.getLogger(getClass());

    private static final By userManagementButton = By.xpath("//text()[normalize-space()='User Management']/..");
    private static final By jobButton = By.xpath("(//text()[normalize-space()='Job']/..)[2]");
    private static final By organizationButton = By.xpath("//text()[normalize-space()='Organization']/..");
    private static final By qualificationsButton = By.xpath("//text()[normalize-space()='Qualifications']/..");
    private static final By nationalitiesButton = By.xpath("//text()[normalize-space()='Nationalities']/..");
    private static final By corporateBrandingButton = By.xpath("//text()[normalize-space()='Corporate Branding']/..");
    private static final By configurationButton = By.xpath("//text()[normalize-space()='Configuration']/..");

    public UserManagement getUserManagement() {
        if (!isOnUserManagementPage()) {
            ActionHelper.waitForVisibility(userManagementButton);
            ActionHelper.click(userManagementButton);
        } else {
            logger.info("You are already on the User Management page.");
        }
        return new UserManagement();
    }

    private boolean isOnUserManagementPage() {
        return ActionHelper.isCurrentUrlContains("admin/viewSystemUsers");
    }

    public void getJob() {
        if (!isOnJobPage()) {
            ActionHelper.waitForVisibility(jobButton);
            ActionHelper.click(jobButton);
        } else {
            logger.info("You are already on the Job page.");
        }
    }

    private boolean isOnJobPage() {
        return ActionHelper.isCurrentUrlContains("admin/viewJobTitleList");
    }

    public void getOrganization() {
        if (!isOnOrganizationPage()) {
            ActionHelper.waitForVisibility(organizationButton);
            ActionHelper.click(organizationButton);
        } else {
            logger.info("You are already on the Organization page.");
        }
    }

    private boolean isOnOrganizationPage() {
        return ActionHelper.isCurrentUrlContains("admin/viewOrganizationGeneralInformation");
    }

    public void getQualifications() {
        if (!isOnQualificationsPage()) {
            ActionHelper.waitForVisibility(qualificationsButton);
            ActionHelper.click(qualificationsButton);
        } else {
            logger.info("You are already on the Qualifications page.");
        }
    }

    private boolean isOnQualificationsPage() {
        return ActionHelper.isCurrentUrlContains("admin/viewQualifications");
    }

    public void getNationalities() {
        if (!isOnNationalitiesPage()) {
            ActionHelper.waitForVisibility(nationalitiesButton);
            ActionHelper.click(nationalitiesButton);
        } else {
            logger.info("You are already on the Nationalities page.");
        }
    }

    private boolean isOnNationalitiesPage() {
        return ActionHelper.isCurrentUrlContains("admin/nationality");
    }

    public void getCorporateBranding() {
        if (!isOnCorporateBrandingPage()) {
            ActionHelper.waitForVisibility(corporateBrandingButton);
            ActionHelper.click(corporateBrandingButton);
        } else {
            logger.info("You are already on the Corporate Branding page.");
        }
    }

    private boolean isOnCorporateBrandingPage() {
        return ActionHelper.isCurrentUrlContains("admin/corporateBranding");
    }

    public void getConfiguration() {
        if (!isOnConfigurationPage()) {
            ActionHelper.waitForVisibility(configurationButton);
            ActionHelper.click(configurationButton);
        } else {
            logger.info("You are already on the Configuration page.");
        }
    }

    private boolean isOnConfigurationPage() {
        return ActionHelper.isCurrentUrlContains("admin/viewEmailConfiguration");
    }
}
