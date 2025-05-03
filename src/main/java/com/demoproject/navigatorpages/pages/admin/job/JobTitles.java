package com.demoproject.navigatorpages.pages.admin.job;

import com.demoproject.navigatorpages.pages.admin.job.jobtitles.AddJobTitle;

public class JobTitles extends BaseJobPage<JobTitles> {

    public AddJobTitle clickAddButton() {
        clickAddButtonBase();
        return new AddJobTitle();
    }

    public AddJobTitle editUserByUserName(String userName) {
        editByNameBase(userName);
        return new AddJobTitle();
    }
}
