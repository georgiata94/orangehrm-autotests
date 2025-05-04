package com.demoproject.navigatorpages.pages.admin.job.workshifts;

import com.demoproject.navigatorpages.pages.admin.job.WorkShifts;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddWorkShift {

    private static final Logger logger = LogManager.getLogger(AddWorkShift.class);

    public AddWorkShift fillShiftName(String text){
        logger.info("Filling Shift Name field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Shift Name"), text);
        return new AddWorkShift();
    }

    public AddWorkShift fillTimeFrom(String timeFrom){
        logger.info("Filling 'From' time field with time: {}", timeFrom);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "From"), timeFrom);
        return new AddWorkShift();
    }

    public AddWorkShift fillTimeTo(String timeTo){
        logger.info("Filling 'To' time field with time: {}", timeTo);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "To"), timeTo);
        return new AddWorkShift();
    }

    public AddWorkShift fillAssignedEmployees(String text){
        logger.info("Filling Assigned Employees field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Assigned Employees"), text);
        return new AddWorkShift();
    }

    public WorkShifts clickSaveButton(){
        logger.info("Clicking 'Save' button for Work Shift.");
        ActionHelper.click(ButtonManager.get("common.button.save.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));
        return new WorkShifts();
    }

    public WorkShifts clickCancelButton(){
        logger.info("Clicking 'Cancel' button for Work Shift.");
        ActionHelper.click(ButtonManager.get("common.button.cancel.xpath"));
        return new WorkShifts();
    }
}
