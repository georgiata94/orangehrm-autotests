package com.demoproject.navigatorpages.pages.admin.job;

import com.demoproject.navigatorpages.pages.admin.job.jobtitles.AddJobTitle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobTitles extends BaseJobPage<JobTitles> {

    private static final Logger logger = LogManager.getLogger(JobTitles.class);

    public AddJobTitle clickAddButton() {
        logger.info("Clicking 'Add' button in Job Titles.");
        clickAddButtonBase();
        return new AddJobTitle();
    }

    public AddJobTitle editUserByUserName(String userName) {
        logger.info("Editing Job Title for user: {}", userName);
        editByNameBase(userName);
        return new AddJobTitle();
    }
}
