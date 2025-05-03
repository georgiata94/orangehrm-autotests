package com.demoproject.navigatorpages.pages.admin.job;

import com.demoproject.navigatorpages.pages.admin.job.jobcategories.AddJobCategory;

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
