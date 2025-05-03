package com.demoproject.navigatorpages.pages.admin.job.jobtitles;

import com.demoproject.navigatorpages.pages.admin.job.JobTitles;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.openqa.selenium.By;

public class AddJobTitle {

    public AddJobTitle fillJobTitleField(String text){
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Job Title"), text);
        return this;
    }

    public AddJobTitle fillJobDescriptionField(String text){
        ActionHelper.type(ButtonManager.get("jobPage.jobDescriptionField.xpath"), text);
        return this;
    }

    public AddJobTitle setFilePath(String filePath){
        ActionHelper.type(By.xpath("jobPage.inputUploadFile.xpath"), filePath);
        return this;
    }

    public AddJobTitle fillNoteField(String text){
        ActionHelper.type(By.xpath("jobPage.addNoteField.xpath"), text);
        return this;
    }

    public JobTitles clickCancelButton(){
        ActionHelper.click(By.xpath("common.button.cancel.xpath"));
        return new JobTitles();
    }

    public JobTitles clickSaveButton(){
        ActionHelper.click(ButtonManager.get("common.button.save.xpath"));
        return new JobTitles();
    }
}
