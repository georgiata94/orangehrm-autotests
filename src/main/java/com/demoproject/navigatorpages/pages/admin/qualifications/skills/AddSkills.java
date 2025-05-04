package com.demoproject.navigatorpages.pages.admin.qualifications.skills;

import com.demoproject.navigatorpages.pages.admin.qualifications.BaseAddQualificationPage;
import com.demoproject.navigatorpages.pages.admin.qualifications.Skills;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddSkills extends BaseAddQualificationPage<AddSkills> {

    private static final Logger logger = LogManager.getLogger(AddSkills.class);

    @Override
    protected AddSkills self() {
        return this;
    }

    public AddSkills fillDescriptionField(String text) {
        logger.info("Filling the description field with text: {}", text);
        ActionHelper.type(ButtonManager.get("jobPage.jobDescriptionField.xpath"), text);
        return self();
    }

    public Skills clickSaveButton() {
        logger.info("Clicking 'Save' button to save skill.");
        return super.clickSaveButton(Skills::new);
    }
}
