package com.demoproject.navigatorpages.pages.pim;

import com.demoproject.navigatorpages.pages.PIM;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddEmployee {

    private static final Logger logger = LogManager.getLogger(AddEmployee.class);

    public AddEmployee fillFirstName(String firstName){
        logger.info("Filling in first name: {}", firstName);
        ActionHelper.type(ButtonManager.get("addEmployee.firstName.xpath"), firstName);
        return this;
    }

    public AddEmployee fillMiddleName(String middleName){
        logger.info("Filling in middle name: {}", middleName);
        ActionHelper.type(ButtonManager.get("addEmployee.middleName.xpath"), middleName);
        return this;
    }

    public AddEmployee fillLastName(String lastName){
        logger.info("Filling in last name: {}", lastName);
        ActionHelper.type(ButtonManager.get("addEmployee.lastName.xpath"), lastName);
        return this;
    }

    public AddEmployee clickRadioButton(){
        logger.info("Clicking on 'Create Login Details' radio button.");
        ActionHelper.click(ButtonManager.get("addEmployee.radioButton.xpath"));
        return this;
    }

    public AddEmployee fillUserName(String uname){
        logger.info("Filling in username: {}", uname);
        ActionHelper.type(ButtonManager.get("addEmployee.username.xpath"), uname);
        return this;
    }

    public AddEmployee fillPassword(String psw){
        logger.info("Filling in password.");
        ActionHelper.type(ButtonManager.get("addEmployee.password.xpath"), psw);
        return this;
    }

    public AddEmployee fillConfirmPassword(String confirmPsw){
        logger.info("Filling in confirm password.");
        ActionHelper.type(ButtonManager.get("addEmployee.confirmPassword.xpath"), confirmPsw);
        return this;
    }

    public AddEmployee fillEmployeeId(String employeeId){
        logger.info("Filling in employee ID: {}", employeeId);
        ActionHelper.type(ButtonManager.get("addEmployee.employeeId.xpath"), employeeId);
        return this;
    }

    public PIM clickCancelButton(){
        logger.info("Clicking 'Cancel' button.");
        ActionHelper.click(ButtonManager.get("common.button.cancel.xpath"));
        return new PIM();
    }

    public PIM clickSaveButton(){
        logger.info("Clicking 'Save' button.");
        ActionHelper.click(ButtonManager.get("common.button.save.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));
        ActionHelper.waitForPageLoad();
        logger.info("Employee successfully saved.");
        return new PIM();
    }

    public AddEmployee clickEnabled(){
        logger.info("Clicking 'Enabled' toggle.");
        ActionHelper.click(ButtonManager.get("addEmployee.enabledButton.xpath"));
        return this;
    }

    public AddEmployee clickDisabled(){
        logger.info("Clicking 'Disabled' toggle.");
        ActionHelper.click(ButtonManager.get("addEmployee.disabledButton.xpath"));
        return this;
    }
}
