package com.demoproject.navigatorpages.pages.admin.job;

import com.demoproject.navigatorpages.pages.admin.job.jobcategories.AddJobCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobCategories extends BaseJobPage<JobCategories> {

    private static final Logger logger = LogManager.getLogger(JobCategories.class);

    public AddJobCategory clickAddButton() {
        logger.info("Clicking 'Add' button in Job Categories.");
        clickAddButtonBase();
        return new AddJobCategory();
    }

    public AddJobCategory editUserByUserName(String userName) {
        logger.info("Editing Job Category for user: {}", userName);
        editByNameBase(userName);
        return new AddJobCategory();
    }
}
