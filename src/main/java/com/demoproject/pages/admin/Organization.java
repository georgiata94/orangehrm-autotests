package com.demoproject.pages.admin;


import com.demoproject.pages.admin.organization.GeneralInformation;
import com.demoproject.pages.admin.organization.Locations;
import com.demoproject.pages.admin.organization.Structure;
import com.demoproject.utils.ActionHelper;
import org.openqa.selenium.By;

public class Organization {

    public static final By generalInformationPage = By.xpath("//ul[@role='menu']//a[text()='General Information']");
    public static final By locationsPage = By.xpath("//ul[@role='menu']//a[text()='Locations']");
    public static final By structurePage = By.xpath("//ul[@role='menu']//a[text()='Structure']");

    public GeneralInformation getGeneralInformation() {

        ActionHelper.waitForVisibility(generalInformationPage);
        ActionHelper.click(generalInformationPage);

        return new GeneralInformation();
    }

    public GeneralInformation getOrganization(boolean performNavigation) {
        if (!performNavigation) {
            return new GeneralInformation();
        }
        return getGeneralInformation();
    }

    public Locations getLocations() {

        ActionHelper.waitForVisibility(locationsPage);
        ActionHelper.click(locationsPage);

        return new Locations();
    }

    public Locations getLocations(boolean performNavigation) {
        if (!performNavigation) {
            return new Locations();
        }
        return getLocations();
    }

    public Structure getStructure() {

        ActionHelper.waitForVisibility(structurePage);
        ActionHelper.click(structurePage);

        return new Structure();
    }

    public Structure getStructure(boolean performNavigation) {
        if (!performNavigation) {
            return new Structure();
        }
        return getStructure();
    }
}
