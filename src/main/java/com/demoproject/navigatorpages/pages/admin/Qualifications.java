package com.demoproject.navigatorpages.pages.admin;

import com.demoproject.navigatorpages.pages.admin.organization.*;
import com.demoproject.navigatorpages.pages.admin.qualifications.*;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class Qualifications {

    protected final Logger logger = LogManager.getLogger(getClass());

    public GeneralInformation getGeneralInformation(boolean performNavigation) {
        return navigateTo("admin.organization.generalinfo.xpath", performNavigation, GeneralInformation::new);
    }

    public Locations getLocations(boolean performNavigation) {
        return navigateTo("admin.organization.locations.xpath", performNavigation, Locations::new);
    }

    public Structure getStructure(boolean performNavigation) {
        return navigateTo("admin.organization.structure.xpath", performNavigation, Structure::new);
    }

    public Skills getSkills(boolean performNavigation) {
        return navigateTo("admin.organization.skills.xpath", performNavigation, Skills::new);
    }

    public Education getEducation(boolean performNavigation) {
        return navigateTo("admin.organization.education.xpath", performNavigation, Education::new);
    }

    public Licenses getLicenses(boolean performNavigation) {
        return navigateTo("admin.organization.licenses.xpath", performNavigation, Licenses::new);
    }

    public Languages getLanguages(boolean performNavigation) {
        return navigateTo("admin.organization.languages.xpath", performNavigation, Languages::new);
    }

    public Memberships getMemberships(boolean performNavigation) {
        return navigateTo("admin.organization.memberships.xpath", performNavigation, Memberships::new);
    }

    private <T> T navigateTo(String key, boolean performNavigation, Supplier<T> pageSupplier) {
        if (performNavigation) {
            By locator = ButtonManager.get(key);
            ActionHelper.waitForVisibility(locator);
            ActionHelper.click(locator);
            logger.info("Navigated to element: {}", key);
        }
        return pageSupplier.get();
    }
}
