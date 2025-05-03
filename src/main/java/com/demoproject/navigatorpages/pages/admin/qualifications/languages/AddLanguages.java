package com.demoproject.navigatorpages.pages.admin.qualifications.languages;

import com.demoproject.navigatorpages.pages.admin.qualifications.BaseAddPage;
import com.demoproject.navigatorpages.pages.admin.qualifications.Languages;

public class AddLanguages extends BaseAddPage<AddLanguages> {

    @Override
    protected AddLanguages self() {
        return this;
    }

    public Languages clickSaveButton() {
        return super.clickSaveButton(Languages::new);
    }
}
