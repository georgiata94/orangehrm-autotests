package com.demoproject.navigatorpages.pages.admin.job;

import com.demoproject.navigatorpages.pages.admin.job.paygrades.AddPayGrade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PayGrades extends BaseJobPage<PayGrades> {

    private static final Logger logger = LogManager.getLogger(PayGrades.class);

    public AddPayGrade clickAddButton() {
        logger.info("Clicking 'Add' button in Pay Grades.");
        clickAddButtonBase();
        return new AddPayGrade();
    }

    public AddPayGrade editUserByUserName(String userName) {
        logger.info("Editing Pay Grade for user: {}", userName);
        editByNameBase(userName);
        return new AddPayGrade();
    }
}
