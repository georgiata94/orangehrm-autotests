package com.demoproject.navigatorpages.pages.admin.job.jobcategories;

import com.demoproject.navigatorpages.pages.admin.job.BaseJobAddPage;
import com.demoproject.navigatorpages.pages.admin.job.JobCategories;

public class AddJobCategory extends BaseJobAddPage<AddJobCategory, JobCategories> {

    public AddJobCategory() {
        super(JobCategories::new);
    }

    @Override
    protected AddJobCategory self() {
        return this;
    }
}
