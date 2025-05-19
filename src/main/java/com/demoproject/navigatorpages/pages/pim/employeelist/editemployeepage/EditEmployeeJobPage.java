package com.demoproject.navigatorpages.pages.pim.employeelist.editemployeepage;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.demoproject.utils.MyWebDriverManager.getDriver;

public class EditEmployeeJobPage {

    private static final Logger logger = LogManager.getLogger(EditEmployeeJobPage.class);

    public EditEmployeeJobPage clickTerminateEmployment(){

        logger.info("Click Terminate Employment");
        ActionHelper.moveToElement(ButtonManager.get("pim.editEmployee.job.terminateButton"));
        ActionHelper.click(ButtonManager.get("pim.editEmployee.job.terminateButton"));
        return this;
    }

    public EditEmployeeJobPage fillTerminationDate(String terminationDate){

        logger.info("Fill termination date");
        ActionHelper.type(ButtonManager.get("pim.editEmployee.job.terminationDateField"),terminationDate);

        return this;
    }

    public EditEmployeeJobPage selectTerminationReason(String terminationReason){

        logger.info("Select termination reason");
        ActionHelper.click(ButtonManager.get("pim.editEmployee.job.terminationReasonDropdown"));
        ActionHelper.waitForVisibility(ButtonManager.get("pim.editEmployee.job.dropDownList"));
        ActionHelper.jsClick(ButtonManager.get("pim.editEmployee.job.dropDownList.option", terminationReason));

        return this;
    }

    public EditEmployeeJobPage clickSaveButton(){

        logger.info("Click save button");

        ActionHelper.jsClick(ButtonManager.get("pim.editEmployee.job.saveButton"));
        ActionHelper.waitForInvisibility(ButtonManager.get("pim.editEmployee.job.modal"));
        ActionHelper.waitForAnimations();

        return this;
    }

}
