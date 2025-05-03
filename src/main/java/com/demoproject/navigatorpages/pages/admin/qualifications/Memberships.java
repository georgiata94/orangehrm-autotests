package com.demoproject.navigatorpages.pages.admin.qualifications;

import com.demoproject.navigatorpages.pages.admin.qualifications.memberships.AddMembership;

public class Memberships extends BaseQualifications<Memberships>{

    @Override
    protected Memberships self() {
        return this;
    }

    public AddMembership clickAddButton() {
        return super.clickAddButton(AddMembership::new);
    }
}
