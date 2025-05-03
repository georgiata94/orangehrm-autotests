package com.demoproject.navigatorpages.pages.admin.qualifications.memberships;

import com.demoproject.navigatorpages.pages.admin.qualifications.BaseAddPage;
import com.demoproject.navigatorpages.pages.admin.qualifications.Memberships;

public class AddMembership extends BaseAddPage<AddMembership> {

    @Override
    protected AddMembership self() {
        return this;
    }

    public Memberships clickSaveButton() {
        return super.clickSaveButton(Memberships::new);
    }

}
