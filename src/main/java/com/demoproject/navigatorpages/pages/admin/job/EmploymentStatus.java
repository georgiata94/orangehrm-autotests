package com.demoproject.navigatorpages.pages.admin.job;

import com.demoproject.navigatorpages.pages.admin.job.employmentstatus.AddEmploymentStatus;


public class EmploymentStatus extends BaseJobPage<EmploymentStatus> {

    public AddEmploymentStatus clickAddButton() {
        clickAddButtonBase();
        return new AddEmploymentStatus();
    }

    public AddEmploymentStatus editUserByUserName(String userName) {
        editByNameBase(userName);
        return new AddEmploymentStatus();
    }
}
