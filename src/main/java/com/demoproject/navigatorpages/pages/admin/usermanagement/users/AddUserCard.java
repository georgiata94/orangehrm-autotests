package com.demoproject.navigatorpages.pages.admin.usermanagement.users;

import com.demoproject.navigatorpages.pages.admin.usermanagement.Users;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.openqa.selenium.By;

public class AddUserCard {

    public AddUserCard fillUserNameField(String userName){
        ActionHelper.waitForVisibility(ButtonManager.get("common.input.generic.xpath","Username"));
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Username"), userName);
        return this;
    }

    public AddUserCard fillPasswordField(String password){
        ActionHelper.waitForVisibility(ButtonManager.get("common.input.generic.xpath","Password"));
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Password"), password);
        return this;
    }

    public AddUserCard fillConfirmPasswordField(String confirmPassword){
        ActionHelper.waitForVisibility(ButtonManager.get("common.input.generic.xpath","Confirm Password"));
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Confirm Password"), confirmPassword);
        return this;
    }

    public AddUserCard selectUserRoleByText(String userRole) {
        return selectDropdownOption(ButtonManager.get("addUserCard.userRoleDropDown.xpath"), userRole);
    }

    public AddUserCard selectStatusByText(String status) {
        return selectDropdownOption(ButtonManager.get("addUserCard.statusDropDown.xpath"), status);
    }

    private AddUserCard selectDropdownOption(By dropdownButton, String optionText) {
        ActionHelper.waitForVisibility(dropdownButton);
        ActionHelper.click(dropdownButton);

        ActionHelper.waitForVisibility(ButtonManager.get("addUserCard.optionList.xpath"));

        By optionLocator = By.xpath("//div[@role='option' and contains(normalize-space(), '" + optionText + "')]");
        ActionHelper.waitForVisibility(optionLocator);
        ActionHelper.click(optionLocator);

        return this;
    }

    public AddUserCard fillEmployeeName(String emplName, String fullName){
        ActionHelper.waitForVisibility(ButtonManager.get("addUserCard.emplNameField.xpath"));
        ActionHelper.type(ButtonManager.get("addUserCard.emplNameField.xpath"), emplName);
        ActionHelper.waitForVisibility(By.xpath("//div[@role='option']//span[text()='"+fullName+"']"));
        ActionHelper.click(By.xpath("//div[@role='option']//span[text()='"+fullName+"']"));
        return this;
    }

    public Users clickCancelButton(){
        ActionHelper.click(ButtonManager.get("common.button.cancel.xpath"));
        return new Users();
    }

    public Users clickSaveButton(){
        ActionHelper.click(ButtonManager.get("common.button.save.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));
        ActionHelper.waitForPageLoad();
        return new Users();
    }
}
