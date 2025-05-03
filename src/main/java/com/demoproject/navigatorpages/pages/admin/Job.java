package com.demoproject.navigatorpages.pages.admin;

import com.demoproject.navigatorpages.pages.admin.job.*;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class Job {

    protected final Logger logger = LogManager.getLogger(getClass());

    public JobTitles getJobTitles(boolean performNavigation) {
        return navigateTo("admin.job.titles.xpath", performNavigation, JobTitles::new);
    }

    public PayGrades getPayGrades(boolean performNavigation) {
        return navigateTo("admin.job.paygrades.xpath", performNavigation, PayGrades::new);
    }

    public EmploymentStatus getEmploymentStatus(boolean performNavigation) {
        return navigateTo("admin.job.employmentstatus.xpath", performNavigation, EmploymentStatus::new);
    }

    public JobCategories getJobCategories(boolean performNavigation) {
        return navigateTo("admin.job.categories.xpath", performNavigation, JobCategories::new);
    }

    public WorkShifts getWorkShifts(boolean performNavigation) {
        return navigateTo("admin.job.workshifts.xpath", performNavigation, WorkShifts::new);
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
