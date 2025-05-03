package com.demoproject.navigatorpages.pages.admin.qualifications;

import com.demoproject.navigatorpages.pages.admin.qualifications.education.AddEducation;

public class Education extends BaseQualifications<Education>{

    @Override
    protected Education self() {
        return this;
    }

    public AddEducation clickAddButton() {
        return super.clickAddButton(AddEducation::new);
    }
}
