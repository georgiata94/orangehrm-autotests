package com.demoproject.pages;

import com.demoproject.pages.admin.Job;
import com.demoproject.pages.admin.UserManagement;
import com.demoproject.utils.ActionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class Admin {

    protected final Logger logger = LogManager.getLogger(getClass());

    private static final By userManagementButton = By.xpath("//text()[normalize-space()='User Management']/ancestor::span[@class='oxd-topbar-body-nav-tab-item']");
    private static final By jobButton = By.xpath("(//text()[normalize-space()='Job']/..)[2]");
    private static final By organizationButton = By.xpath("//text()[normalize-space()='Organization']/..");
    private static final By qualificationsButton = By.xpath("//text()[normalize-space()='Qualifications']/..");
    private static final By nationalitiesButton = By.xpath("//text()[normalize-space()='Nationalities']/..");
    private static final By corporateBrandingButton = By.xpath("//text()[normalize-space()='Corporate Branding']/..");
    private static final By configurationButton = By.xpath("//text()[normalize-space()='Configuration']/..");

    public UserManagement getUserManagement() {
        ActionHelper.waitForVisibility(userManagementButton);
        ActionHelper.click(userManagementButton);
        return new UserManagement();
    }

    public UserManagement getUserManagement(boolean performNavigation) {
        if (!performNavigation) {
            return new UserManagement();
        }
        return getUserManagement();
    }

    public Job getJob() {

        ActionHelper.waitForVisibility(jobButton);
        ActionHelper.click(jobButton);
        return new Job();
    }

    public Job getJob(boolean performNavigation) {
        if (!performNavigation) {
            return new Job();
        }
        return getJob();
    }

    public void getOrganization() {

        ActionHelper.waitForVisibility(organizationButton);
        ActionHelper.click(organizationButton);
    }

    public void getOrganization(boolean performNavigation) {
        if (performNavigation) {
            getOrganization();
        }
    }

    public void getQualifications() {

        ActionHelper.waitForVisibility(qualificationsButton);
        ActionHelper.click(qualificationsButton);

    }

    public void getQualifications(boolean performNavigation) {
        if (performNavigation) {
            getQualifications();
        }
    }

    public void getNationalities() {

        ActionHelper.waitForVisibility(nationalitiesButton);
        ActionHelper.click(nationalitiesButton);

    }

    public void getNationalities(boolean performNavigation) {
        if (performNavigation) {
            getNationalities();
        }
    }

    public void getCorporateBranding() {

        ActionHelper.waitForVisibility(corporateBrandingButton);
        ActionHelper.click(corporateBrandingButton);

    }

    public void getCorporateBranding(boolean performNavigation) {
        if (performNavigation) {
            getCorporateBranding();
        }
    }

    public void getConfiguration() {

        ActionHelper.waitForVisibility(configurationButton);
        ActionHelper.click(configurationButton);

    }

    public void getConfiguration(boolean performNavigation) {
        if (performNavigation) {
            getConfiguration();
        }
    }
}