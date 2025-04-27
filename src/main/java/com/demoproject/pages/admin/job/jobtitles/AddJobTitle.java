package com.demoproject.pages.admin.job.jobtitles;

import com.demoproject.pages.admin.job.JobTitles;
import com.demoproject.utils.ActionHelper;
import org.openqa.selenium.By;

public class AddJobTitle {

    private static final By jobTitleField = By.xpath("//div[@class='oxd-form-row']//input[@class='oxd-input oxd-input--active']");
    private static final By jobDescriptionField = By.xpath("//textarea[@placeholder='Type description here']");
    private static final By inputUploadFile = By.xpath("//input[@type='file']");
    private static final By addNoteField = By.xpath("//textarea[@placeholder='Add note']");
    private static final By cancelButton = By.xpath("//button[text()=' Cancel ']");
    private static final By saveButton = By.xpath("//button[text()=' Save ']");


    public AddJobTitle fillJobTitleField(String text){
        ActionHelper.type(jobTitleField,text);
        return this;
    }

    public AddJobTitle fillJobDescriptionField(String text){
        ActionHelper.type(jobDescriptionField,text);
        return this;
    }

    public AddJobTitle setFilePath(String filePath){
        ActionHelper.type(inputUploadFile,filePath);
        return this;
    }

    public AddJobTitle fillNotField(String text){
        ActionHelper.type(addNoteField,text);
        return this;
    }

    public JobTitles clickCancelButton(){
        ActionHelper.click(cancelButton);
        return new JobTitles();
    }

    public JobTitles clickSaveButton(){
        ActionHelper.click(saveButton);
        return new JobTitles();
    }
}
