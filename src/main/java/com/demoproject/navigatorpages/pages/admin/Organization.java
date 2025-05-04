package com.demoproject.navigatorpages.pages.admin;

import com.demoproject.navigatorpages.pages.admin.organization.GeneralInformation;
import com.demoproject.navigatorpages.pages.admin.organization.Locations;
import com.demoproject.navigatorpages.pages.admin.organization.Structure;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class Organization {

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

    private <T> T navigateTo(String key, boolean performNavigation, Supplier<T> pageSupplier) {
        if (performNavigation) {
            By locator = ButtonManager.get(key);
            ActionHelper.waitForVisibility(locator);
            ActionHelper.click(locator);
            logger.info("Navigation performed.");
        }
        return pageSupplier.get();
    }
}
