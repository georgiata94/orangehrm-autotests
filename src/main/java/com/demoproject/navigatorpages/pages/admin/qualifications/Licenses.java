package com.demoproject.navigatorpages.pages.admin.qualifications;

import com.demoproject.navigatorpages.pages.admin.qualifications.licenses.AddLicenses;

public class Licenses extends BaseQualifications<Licenses>{

    @Override
    protected Licenses self() {
        return this;
    }

    public AddLicenses clickAddButton() {
        return super.clickAddButton(AddLicenses::new);
    }
}
