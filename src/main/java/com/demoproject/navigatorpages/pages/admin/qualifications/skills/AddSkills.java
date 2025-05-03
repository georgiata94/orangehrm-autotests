package com.demoproject.navigatorpages.pages.admin.qualifications.skills;

import com.demoproject.navigatorpages.pages.admin.qualifications.BaseAddPage;
import com.demoproject.navigatorpages.pages.admin.qualifications.Skills;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;

public class AddSkills extends BaseAddPage<AddSkills> {
    @Override
    protected AddSkills self() {
        return this;
    }

    public AddSkills fillDescriptionField(String text) {
        ActionHelper.type(ButtonManager.get("jobPage.jobDescriptionField.xpath"), text);
        return self();
    }

    public Skills clickSaveButton() {
        return super.clickSaveButton(Skills::new);
    }

}
