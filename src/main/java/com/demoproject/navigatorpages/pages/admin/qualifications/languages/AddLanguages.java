package com.demoproject.navigatorpages.pages.admin.qualifications.languages;

import com.demoproject.navigatorpages.pages.admin.qualifications.BaseAddQualificationPage;
import com.demoproject.navigatorpages.pages.admin.qualifications.Languages;

public class AddLanguages extends BaseAddQualificationPage<AddLanguages> {

    @Override
    protected AddLanguages self() {
        return this;
    }

    public Languages clickSaveButton() {
        return super.clickSaveButton(Languages::new);
    }
}
