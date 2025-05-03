package com.demoproject.navigatorpages.pages.admin.usermanagement;

import com.demoproject.navigatorpages.pages.admin.usermanagement.users.AddUserCard;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.openqa.selenium.By;

public class Users {

    public Users fillUserNameField(String userName){
        ActionHelper.waitForPresence(ButtonManager.get("users.userNameField.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("users.userNameField.xpath"));
        ActionHelper.type(ButtonManager.get("users.userNameField.xpath"), userName);
        return this;
    }

    public Users selectUserRoleByText(String userRole) {
        return selectDropdownOption(ButtonManager.get("users.userRoleDropDownBtn.xpath"), userRole);
    }

    public Users selectStatusByText(String status) {
        return selectDropdownOption(ButtonManager.get("users.statusDropDownBtn.xpath"), status);
    }

    private Users selectDropdownOption(By dropdownButton, String optionText) {
        ActionHelper.waitForVisibility(dropdownButton);
        ActionHelper.click(dropdownButton);

        ActionHelper.waitForVisibility(ButtonManager.get("users.optionList.xpath"));

        By optionLocator = By.xpath("//div[@role='option' and contains(normalize-space(), '" + optionText + "')]");
        ActionHelper.waitForVisibility(optionLocator);
        ActionHelper.click(optionLocator);

        return this;
    }

    public Users fillEmployeeName(String empName){
        ActionHelper.waitForVisibility(ButtonManager.get("users.employeeNameField.xpath"));
        ActionHelper.type(ButtonManager.get("users.employeeNameField.xpath"), empName);
        return this;
    }

    public Users clickResetBtn(){
        ActionHelper.waitForVisibility(ButtonManager.get("common.button.reset.xpath"));
        ActionHelper.click(ButtonManager.get("common.button.reset.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.records.found.xpath"));
        return this;
    }

    public Users clickSearchBtn(){
        ActionHelper.waitForVisibility(ButtonManager.get("common.button.search.xpath"));
        ActionHelper.click(ButtonManager.get("common.button.search.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.records.found.xpath"));
        return this;
    }

    public AddUserCard clickAddBtn(){
        ActionHelper.waitForVisibility(ButtonManager.get("common.button.add.xpath"));
        ActionHelper.click(ButtonManager.get("common.button.add.xpath"));
        return new AddUserCard();
    }

    public Users enableCheckBoxAll() {
        return toggleCheckbox(ButtonManager.get("common.checkbox.all.xpath"), ActionHelper.CheckboxState.ENABLE);
    }

    public Users disableCheckBoxAll() {
        return toggleCheckbox(ButtonManager.get("common.checkbox.all.xpath"), ActionHelper.CheckboxState.DISABLE);
    }

    public Users enableCheckBoxByUserName(String userName) {
        return toggleCheckbox(getUserCheckboxLocator(userName), ActionHelper.CheckboxState.ENABLE);
    }

    public Users disableCheckBoxByUserName(String userName) {
        return toggleCheckbox(getUserCheckboxLocator(userName), ActionHelper.CheckboxState.DISABLE);
    }

    private By getUserCheckboxLocator(String userName) {
        return ButtonManager.get("users.userCheckbox.xpath", userName);
    }

    private Users toggleCheckbox(By locator, ActionHelper.CheckboxState state) {
        ActionHelper.waitForVisibility(locator);

        boolean shouldBeChecked = (state == ActionHelper.CheckboxState.ENABLE);
        ActionHelper.setCheckbox(locator, shouldBeChecked);

        if (state == ActionHelper.CheckboxState.ENABLE) {
            ActionHelper.waitForCheckboxToBeEnabled(locator);
        } else {
            ActionHelper.waitForCheckboxToBeDisabled(locator);
        }
        return this;
    }

    public Users deleteUserByUserName(String userName) {
        By deleteIcon = ButtonManager.get("users.userActionsXpath.xpath", userName + "/../following-sibling::div//i[1]");
        ActionHelper.waitForVisibility(deleteIcon);
        ActionHelper.click(deleteIcon);

        ActionHelper.waitForVisibility(ButtonManager.get("common.delete.confirm.xpath"));
        ActionHelper.click(ButtonManager.get("common.delete.confirm.xpath"));

        ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));
        return this;
    }

    public AddUserCard editUserByUserName(String userName) {
        By editIcon = ButtonManager.get("users.userActionsXpath.xpath", userName + "/../following-sibling::div//i[2]");
        ActionHelper.waitForPresence(editIcon);
        ActionHelper.actionClick(editIcon);

        ActionHelper.waitForVisibility(ButtonManager.get("common.actions.edit.xpath"));
        return new AddUserCard();
    }

    public Users deleteSelectedUser(){
        ActionHelper.waitForVisibility(ButtonManager.get("common.actions.delete.xpath"));
        ActionHelper.click(ButtonManager.get("common.actions.delete.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.delete.confirm.xpath"));
        ActionHelper.click(ButtonManager.get("common.delete.confirm.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));
        return this;
    }
}
