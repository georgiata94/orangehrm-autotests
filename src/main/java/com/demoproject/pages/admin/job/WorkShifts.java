package com.demoproject.pages.admin.job;

import com.demoproject.pages.admin.job.jobtitles.AddWorkShift;

public class WorkShifts extends BaseJobPage<WorkShifts> {

    public AddWorkShift clickAddButton() {
        clickAddButtonBase();
        return new AddWorkShift();
    }

    public AddWorkShift editUserByUserName(String userName) {
        editByNameBase(userName);
        return new AddWorkShift();
    }
}
