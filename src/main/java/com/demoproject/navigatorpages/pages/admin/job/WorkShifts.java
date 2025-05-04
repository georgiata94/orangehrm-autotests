package com.demoproject.navigatorpages.pages.admin.job;

import com.demoproject.navigatorpages.pages.admin.job.workshifts.AddWorkShift;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorkShifts extends BaseJobPage<WorkShifts> {

    private static final Logger logger = LogManager.getLogger(WorkShifts.class);

    public AddWorkShift clickAddButton() {
        logger.info("Clicking 'Add' button in Work Shifts.");
        clickAddButtonBase();
        return new AddWorkShift();
    }

    public AddWorkShift editUserByUserName(String userName) {
        logger.info("Editing Work Shift for user: {}", userName);
        editByNameBase(userName);
        return new AddWorkShift();
    }
}
