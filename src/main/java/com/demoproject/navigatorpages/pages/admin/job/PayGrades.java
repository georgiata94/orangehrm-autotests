package com.demoproject.navigatorpages.pages.admin.job;

import com.demoproject.navigatorpages.pages.admin.job.jobtitles.AddPayGrade;

public class PayGrades extends BaseJobPage<PayGrades> {

    public AddPayGrade clickAddButton() {
        clickAddButtonBase();
        return new AddPayGrade();
    }

    public AddPayGrade editUserByUserName(String userName) {
        editByNameBase(userName);
        return new AddPayGrade();
    }
}
