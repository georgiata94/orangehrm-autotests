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
        if (!isOnJobTitlesPage()) {
            ActionHelper.waitForVisibility(jobTitles);
            ActionHelper.click(jobTitles);
        } else {
            logger.info("You are already on the Job Titles page.");
        }
        return new JobTitles();
    }

    public PayGrades getPayGrades() {
        if (!isOnPayGradesPage()) {
            ActionHelper.waitForVisibility(payGrades);
            ActionHelper.click(payGrades);
        } else {
            logger.info("You are already on the Pay Grades page.");
        }
        return new PayGrades();
    }

    public EmploymentStatus getEmploymentStatus() {
        if (!isOnEmploymentStatusPage()) {
            ActionHelper.waitForVisibility(emplStatus);
            ActionHelper.click(emplStatus);
        } else {
            logger.info("You are already on the Employment Status page.");
        }
        return new EmploymentStatus();
    }

    public JobCategories getJobCategories() {
        if (!isOnJobCategoriesPage()) {
            ActionHelper.waitForVisibility(jobCategories);
            ActionHelper.click(jobCategories);
        } else {
            logger.info("You are already on the Job Categories page.");
        }
        return new JobCategories();
    }

    public WorkShifts getWorkShifts() {
        if (!isOnWorkShiftsPage()) {
            ActionHelper.waitForVisibility(workShifts);
            ActionHelper.click(workShifts);
        } else {
            logger.info("You are already on the Work Shifts page.");
        }
        return new WorkShifts();
    }

    private boolean isOnJobTitlesPage() {
        return ActionHelper.isCurrentUrlContains("admin/viewJobTitleList");
    }

    private boolean isOnPayGradesPage() {
        return ActionHelper.isCurrentUrlContains("admin/viewPayGrades");
    }

    private boolean isOnEmploymentStatusPage() {
        return ActionHelper.isCurrentUrlContains("admin/employmentStatus");
    }

    private boolean isOnJobCategoriesPage() {
        return ActionHelper.isCurrentUrlContains("admin/jobCategory");
    }

    private boolean isOnWorkShiftsPage() {
        return ActionHelper.isCurrentUrlContains("admin/workShift");
    }
}
