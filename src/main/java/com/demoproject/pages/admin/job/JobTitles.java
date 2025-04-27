package com.demoproject.pages.admin.job;

import com.demoproject.pages.admin.job.jobtitles.AddJobTitle;
import org.openqa.selenium.By;

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
