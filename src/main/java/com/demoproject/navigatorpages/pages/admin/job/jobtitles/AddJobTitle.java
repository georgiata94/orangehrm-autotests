package com.demoproject.navigatorpages.pages.admin.job.jobtitles;

import com.demoproject.navigatorpages.pages.admin.job.JobTitles;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class AddJobTitle {

    private static final Logger logger = LogManager.getLogger(AddJobTitle.class);

    public AddJobTitle fillJobTitleField(String text){
        logger.info("Filling Job Title field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Job Title"), text);
        return this;
    }

    public AddJobTitle fillJobDescriptionField(String text){
        logger.info("Filling Job Description field with text: {}", text);
        ActionHelper.type(ButtonManager.get("jobPage.jobDescriptionField.xpath"), text);
        return this;
    }

    public AddJobTitle setFilePath(String filePath){
        logger.info("Setting file path for file upload: {}", filePath);
        ActionHelper.type(By.xpath("jobPage.inputUploadFile.xpath"), filePath);
        return this;
    }

    public AddJobTitle fillNoteField(String text){
        logger.info("Filling Note field with text: {}", text);
        ActionHelper.type(By.xpath("jobPage.addNoteField.xpath"), text);
        return this;
    }

    public JobTitles clickCancelButton(){
        logger.info("Clicking 'Cancel' button.");
        ActionHelper.click(By.xpath("common.button.cancel.xpath"));
        return new JobTitles();
    }

    public JobTitles clickSaveButton(){
        logger.info("Clicking 'Save' button.");
        ActionHelper.click(ButtonManager.get("common.button.save.xpath"));
        return new JobTitles();
    }
}
