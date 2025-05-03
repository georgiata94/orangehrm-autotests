package com.demoproject.navigatorpages.pages.pim;

import com.demoproject.navigatorpages.pages.PIM;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;

public class AddEmployee {

    public AddEmployee fillFirstName(String firstName){
        ActionHelper.type(ButtonManager.get("addEmployee.firstName.xpath"), firstName);
        return this;
    }

    public AddEmployee fillMiddleName(String middleName){
        ActionHelper.type(ButtonManager.get("addEmployee.middleName.xpath"), middleName);
        return this;
    }

    public AddEmployee fillLastName(String lastName){
        ActionHelper.type(ButtonManager.get("addEmployee.lastName.xpath"), lastName);
        return this;
    }

    public AddEmployee clickRadioButton(){
        ActionHelper.click(ButtonManager.get("addEmployee.radioButton.xpath"));
        return this;
    }

    public AddEmployee fillUserName(String uname){
        ActionHelper.type(ButtonManager.get("addEmployee.username.xpath"), uname);
        return this;
    }

    public AddEmployee fillPassword(String psw){
        ActionHelper.type(ButtonManager.get("addEmployee.password.xpath"), psw);
        return this;
    }

    public AddEmployee fillConfirmPassword(String confirmPsw){
        ActionHelper.type(ButtonManager.get("addEmployee.confirmPassword.xpath"), confirmPsw);
        return this;
    }

    public PIM clickCancelButton(){
        ActionHelper.click(ButtonManager.get("common.button.cancel.xpath"));
        return new PIM();
    }

    public PIM clickSaveButton(){
        ActionHelper.click(ButtonManager.get("common.button.save.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));
        ActionHelper.waitForPageLoad();
        return new PIM();
    }

    public AddEmployee clickEnabled(){
        ActionHelper.click(ButtonManager.get("addEmployee.enabledButton.xpath"));
        return this;
    }

    public AddEmployee clickDisabled(){
        ActionHelper.click(ButtonManager.get("addEmployee.disabledButton.xpath"));
        return this;
    }
}
