package com.demoproject.navigatorpages.pages.admin.qualifications;

import com.demoproject.navigatorpages.pages.admin.qualifications.skills.AddSkills;

public class Skills extends BaseQualifications<Skills>{
    @Override
    protected Skills  self() {
        return this;
    }

    public AddSkills clickAddButton() {
        return super.clickAddButton(AddSkills::new);
    }
}
