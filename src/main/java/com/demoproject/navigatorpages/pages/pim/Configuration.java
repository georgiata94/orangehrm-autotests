package com.demoproject.navigatorpages.pages.pim;

import com.demoproject.navigatorpages.pages.pim.configuration.*;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class Configuration {

    protected final Logger logger = LogManager.getLogger(getClass());

    public OptionalFields getOptionalFields(boolean performNavigation) {
        return navigateTo("pim.configuration.optionalFields.xpath",performNavigation, OptionalFields::new);
    }

    public CustomFields getCustomFields(boolean performNavigation) {
        return navigateTo("pim.configuration.customFields.xpath", performNavigation, CustomFields::new);
    }

    public DataImport getDataImport(boolean performNavigation) {
        return navigateTo("pim.configuration.dataImport.xpath", performNavigation, DataImport::new);
    }

    public ReportingMethods getReportingMethods(boolean performNavigation) {
        return navigateTo("pim.configuration.reportingMethods.xpath", performNavigation, ReportingMethods::new);
    }

    public TerminationReasons getTerminationReasons(boolean performNavigation) {
        return navigateTo("pim.configuration.terminationReasons.xpath", performNavigation, TerminationReasons::new);
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
