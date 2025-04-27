package com.demoproject.pages.admin.job;

import com.demoproject.pages.admin.job.jobtitles.AddJobCategory;

public class JobCategories extends BaseJobPage<JobCategories> {

    public AddJobCategory clickAddButton() {
        clickAddButtonBase();
        return new AddJobCategory();
    }

    public AddJobCategory editUserByUserName(String userName) {
        editByNameBase(userName);
        return new AddJobCategory();
    }
}
