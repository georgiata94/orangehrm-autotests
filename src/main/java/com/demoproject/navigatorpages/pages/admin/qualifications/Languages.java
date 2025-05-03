package com.demoproject.navigatorpages.pages.admin.qualifications;

import com.demoproject.navigatorpages.pages.admin.qualifications.languages.AddLanguages;

public class Languages extends BaseQualifications<Languages>{

    @Override
    protected Languages self() {
        return this;
    }

    public AddLanguages clickAddButton() {
        return super.clickAddButton(AddLanguages::new);
    }
}
