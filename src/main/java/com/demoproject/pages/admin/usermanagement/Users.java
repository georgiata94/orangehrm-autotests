package com.demoproject.pages.admin.usermanagement;

import com.demoproject.pages.admin.usermanagement.users.AddUserCard;
import com.demoproject.pages.admin.usermanagement.users.EditUserCard;
import com.demoproject.utils.ActionHelper;
import org.openqa.selenium.By;

public class Users {



    private static final By userNameField = By.xpath("//label[text()='Username']/..//..//div//input[@class='oxd-input oxd-input--active']");
    private static final By userRoleDropDownBtn = By.xpath("//label[text()='User Role']/../..//i");
    private static final By optionList = By.xpath("//div[@role='listbox']");
    private static final By statusDropDownBtn = By.xpath("//label[text()='Status']/../..//i");
    private static final By employeeNameField = By.xpath("//input[@placeholder='Type for hints...']");
    private static final By resetBtn = By.xpath("//text()[contains(., 'Reset')]/ancestor::button");
    private static final By searchBtn = By.xpath("//text()[contains(., 'Search')]/ancestor::button");
    private static final By recordsFoundText = By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//span");
    private static final By addBtn = By.xpath("//text()[contains(., 'Add')]/ancestor::button");
    private static final By checkBoxAll = By.xpath("//*[text()='Username']/preceding-sibling::div//input[@type='checkbox']");
    private static final String userActionsXpath = "//div[text()='%s']/../following-sibling::div//i";
    private static final By confirmDeleteBtn = By.xpath("//div[@class='orangehrm-modal-footer']//button//i");
    private static final By successToast = By.xpath("//div[@class='oxd-toast-start']");
    private static final By editUserTitle = By.xpath("//h6[text()='Edit User']");
    private static final By deleteSelectedButton = By.xpath("//i[@class='oxd-icon bi-trash-fill oxd-button-icon']");

    public Users fillUserNameField(String userName){
        ActionHelper.waitForVisibility(userNameField);
        ActionHelper.type(userNameField,userName);
        return this;
    }

    public Users selectUserRoleByText(String userRole) {
        return selectDropdownOption(userRoleDropDownBtn, userRole);
    }

    public Users selectStatusByText(String status) {
        return selectDropdownOption(statusDropDownBtn, status);
    }

    private Users selectDropdownOption(By dropdownButton, String optionText) {
        ActionHelper.waitForVisibility(dropdownButton);
        ActionHelper.click(dropdownButton);

        ActionHelper.waitForVisibility(optionList);

        By optionLocator = By.xpath("//div[@role='option' and contains(normalize-space(), '" + optionText + "')]");
        ActionHelper.waitForVisibility(optionLocator);
        ActionHelper.click(optionLocator);

        return this;
    }

    public Users fillEmployeeName(String empName){
        ActionHelper.waitForVisibility(employeeNameField);
        ActionHelper.type(employeeNameField,empName);
        return this;
    }

    public Users clickResetBtn(){
        ActionHelper.waitForVisibility(resetBtn);
        ActionHelper.click(resetBtn);
        ActionHelper.waitForVisibility(recordsFoundText);
        return this;
    }

    public Users clickSearchBtn(){
        ActionHelper.waitForVisibility(searchBtn);
        ActionHelper.click(searchBtn);
        ActionHelper.waitForVisibility(recordsFoundText);
        return this;
    }

    public AddUserCard clickAddBtn(){
        ActionHelper.waitForVisibility(addBtn);
        ActionHelper.click(addBtn);
        return new AddUserCard();
    }

    public Users enableCheckBoxAll() {
        return toggleCheckbox(checkBoxAll, ActionHelper.CheckboxState.ENABLE);
    }

    public Users disableCheckBoxAll() {
        return toggleCheckbox(checkBoxAll, ActionHelper.CheckboxState.DISABLE);
    }

    public Users enableCheckBoxByUserName(String userName) {
        return toggleCheckbox(getUserCheckboxLocator(userName), ActionHelper.CheckboxState.ENABLE);
    }

    public Users disableCheckBoxByUserName(String userName) {
        return toggleCheckbox(getUserCheckboxLocator(userName), ActionHelper.CheckboxState.DISABLE);
    }

    private By getUserCheckboxLocator(String userName) {
        return By.xpath("//div[text()='" + userName + "']/../preceding-sibling::div//input[@type='checkbox']");
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
        By deleteIcon = By.xpath(String.format(userActionsXpath, userName) + "[1]");

        ActionHelper.waitForVisibility(deleteIcon);
        ActionHelper.click(deleteIcon);

        ActionHelper.waitForVisibility(confirmDeleteBtn);
        ActionHelper.click(confirmDeleteBtn);

        ActionHelper.waitForVisibility(successToast);

        return this;
    }

    public EditUserCard editUserByUserName(String userName) {
        By editIcon = By.xpath(String.format(userActionsXpath, userName) + "[2]");

        ActionHelper.waitForVisibility(editIcon);
        ActionHelper.click(editIcon);

        ActionHelper.waitForVisibility(editUserTitle);

        return new EditUserCard();
    }

    public Users deleteSelectedUser(){
        ActionHelper.waitForVisibility(deleteSelectedButton);
        ActionHelper.click(deleteSelectedButton);
        ActionHelper.waitForVisibility(confirmDeleteBtn);
        ActionHelper.click(confirmDeleteBtn);
        ActionHelper.waitForVisibility(successToast);
        return this;
    }
}
