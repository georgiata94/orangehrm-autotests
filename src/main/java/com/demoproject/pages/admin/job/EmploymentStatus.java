package com.demoproject.pages.admin.job;

import com.demoproject.pages.admin.job.jobtitles.AddEmploymentStatus;


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
