package com.demoproject.pages.admin;

import com.demoproject.pages.admin.job.*;
import com.demoproject.utils.ActionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class Job {

    protected final Logger logger = LogManager.getLogger(getClass());

    private static final By jobTitles = By.xpath("//ul[@role='menu']//a[text()='Job Titles']");
    private static final By payGrades = By.xpath("//ul[@role='menu']//a[text()='Pay Grades']");
    private static final By emplStatus = By.xpath("//ul[@role='menu']//a[text()='Employment Status']");
    private static final By jobCategories = By.xpath("//ul[@role='menu']//a[text()='Job Categories']");
    private static final By workShifts = By.xpath("//ul[@role='menu']//a[text()='Work Shifts']");

    public JobTitles getJobTitles() {

        ActionHelper.waitForVisibility(jobTitles);
        ActionHelper.click(jobTitles);

        return new JobTitles();
    }

    public JobTitles getJobTitles(boolean performNavigation) {
        if (!performNavigation) {
            return new JobTitles();
        }
        return getJobTitles();
    }

    public PayGrades getPayGrades() {

        ActionHelper.waitForVisibility(payGrades);
        ActionHelper.click(payGrades);

        return new PayGrades();
    }

    public PayGrades getPayGrades(boolean performNavigation) {
        if (!performNavigation) {
            return new PayGrades();
        }
        return getPayGrades();
    }

    public EmploymentStatus getEmploymentStatus() {

        ActionHelper.waitForVisibility(emplStatus);
        ActionHelper.click(emplStatus);

        return new EmploymentStatus();
    }

    public EmploymentStatus getEmploymentStatus(boolean performNavigation) {
        if (!performNavigation) {
            return new EmploymentStatus();
        }
        return getEmploymentStatus();
    }

    public JobCategories getJobCategories() {

        ActionHelper.waitForVisibility(jobCategories);
        ActionHelper.click(jobCategories);

        return new JobCategories();
    }

    public JobCategories getJobCategories(boolean performNavigation) {
        if (!performNavigation) {
            return new JobCategories();
        }
        return getJobCategories();
    }

    public WorkShifts getWorkShifts() {

        ActionHelper.waitForVisibility(workShifts);
        ActionHelper.click(workShifts);

        return new WorkShifts();
    }

    public WorkShifts getWorkShifts(boolean performNavigation) {
        if (!performNavigation) {
            return new WorkShifts();
        }
        return getWorkShifts();
    }

}
