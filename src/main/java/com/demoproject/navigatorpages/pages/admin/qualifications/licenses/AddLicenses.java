package com.demoproject.navigatorpages.pages.admin.qualifications.licenses;

import com.demoproject.navigatorpages.pages.admin.qualifications.BaseAddPage;
import com.demoproject.navigatorpages.pages.admin.qualifications.Licenses;

public class AddLicenses extends BaseAddPage<AddLicenses> {

    @Override
    protected AddLicenses self() {
        return this;
    }

    public Licenses clickSaveButton() {
        return super.clickSaveButton(Licenses::new);
    }
}
