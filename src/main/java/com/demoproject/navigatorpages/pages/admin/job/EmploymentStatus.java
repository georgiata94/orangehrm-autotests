package com.demoproject.navigatorpages.pages.admin.job;

import com.demoproject.navigatorpages.pages.admin.job.employmentstatus.AddEmploymentStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmploymentStatus extends BaseJobPage<EmploymentStatus> {

    private static final Logger logger = LogManager.getLogger(EmploymentStatus.class);

    public AddEmploymentStatus clickAddButton() {
        logger.info("Clicking 'Add' button in Employment Status.");
        clickAddButtonBase();
        return new AddEmploymentStatus();
    }

    public AddEmploymentStatus editUserByUserName(String userName) {
        logger.info("Editing Employment Status for user: {}", userName);
        editByNameBase(userName);
        return new AddEmploymentStatus();
    }
}
