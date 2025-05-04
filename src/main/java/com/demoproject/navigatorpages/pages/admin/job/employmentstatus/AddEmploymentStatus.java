package com.demoproject.navigatorpages.pages.admin.job.employmentstatus;

import com.demoproject.navigatorpages.pages.admin.job.BaseJobAddPage;
import com.demoproject.navigatorpages.pages.admin.job.EmploymentStatus;

public class AddEmploymentStatus extends BaseJobAddPage<AddEmploymentStatus, EmploymentStatus> {

    public AddEmploymentStatus() {
        super(EmploymentStatus::new);
    }

    @Override
    protected AddEmploymentStatus self() {
        return this;
    }
}

