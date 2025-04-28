package com.demoproject.pages.admin.organization;

import com.demoproject.pages.admin.organization.locations.AddLocation;
import com.demoproject.utils.ActionHelper;
import org.openqa.selenium.By;

public class Locations {

    protected static final By addBtn = By.xpath("//text()[contains(., 'Add')]/ancestor::button");
    protected static final By checkBoxAll = By.xpath("//*[text()='Username']/preceding-sibling::div//input[@type='checkbox']");
    protected static final String userActionsXpath = "//div[text()='%s']/../following-sibling::div//i";
    protected static final By confirmDeleteBtn = By.xpath("//div[@class='orangehrm-modal-footer']//button//i");
    protected static final By successToast = By.xpath("//div[@class='oxd-toast-start']");
    protected static final By editUserTitle = By.xpath("//h6[text()='Edit User']");
    protected static final By deleteSelectedButton = By.xpath("//i[@class='oxd-icon bi-trash-fill oxd-button-icon']");
    protected static final By resetBtn = By.xpath("//text()[contains(., 'Reset')]/ancestor::button");
    protected static final By searchBtn = By.xpath("//text()[contains(., 'Search')]/ancestor::button");
    protected static final By recordsFoundText = By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//span");
    protected static final By nameField = By.xpath("//label[text()='Name']/../following-sibling::div//input");
    protected static final By cityField = By.xpath("//label[text()='City']/../following-sibling::div//input");
    protected static final By countryDropdown = By.xpath("//div[@class='oxd-select-wrapper']");


    public Locations fillNameField(String text){
        ActionHelper.type(nameField,text);
        return this;
    }

    public Locations fillCityField(String text){
        ActionHelper.type(cityField,text);
        return this;
    }

    public Locations selectCountryByText(String country){
        ActionHelper.click(countryDropdown);
        ActionHelper.waitForVisibility(By.xpath("//div[@role='option']"));
        ActionHelper.jsScrollClick(By.xpath("//div[@role='option']//span[text()='"+country+"']"));
        return this;
    }

    public AddLocation clickAddButton() {
        ActionHelper.waitForVisibility(addBtn);
        ActionHelper.click(addBtn);
        return new AddLocation();
    }

    public Locations clickResetBtn(){
        ActionHelper.waitForVisibility(resetBtn);
        ActionHelper.click(resetBtn);
        ActionHelper.waitForVisibility(recordsFoundText);
        return this;
    }

    public Locations clickSearchBtn(){
        ActionHelper.waitForVisibility(searchBtn);
        ActionHelper.click(searchBtn);
        ActionHelper.waitForVisibility(recordsFoundText);
        return this;
    }

    public Locations enableCheckBoxAll() {
        return toggleCheckbox(checkBoxAll, ActionHelper.CheckboxState.ENABLE);
    }

    public Locations disableCheckBoxAll() {
        return toggleCheckbox(checkBoxAll, ActionHelper.CheckboxState.DISABLE);
    }

    public Locations enableCheckBoxByUserName(String userName) {
        return toggleCheckbox(getUserCheckboxLocator(userName), ActionHelper.CheckboxState.ENABLE);
    }

    public Locations disableCheckBoxByUserName(String userName) {
        return toggleCheckbox(getUserCheckboxLocator(userName), ActionHelper.CheckboxState.DISABLE);
    }

    private By getUserCheckboxLocator(String userName) {
        return By.xpath("//div[text()='" + userName + "']/../preceding-sibling::div//input[@type='checkbox']");
    }

    private Locations toggleCheckbox(By locator, ActionHelper.CheckboxState state) {
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

    public Locations deleteLocationByName(String userName) {
        By deleteIcon = By.xpath(String.format(userActionsXpath, userName) + "[1]");

        ActionHelper.waitForVisibility(deleteIcon);
        ActionHelper.click(deleteIcon);

        ActionHelper.waitForVisibility(confirmDeleteBtn);
        ActionHelper.click(confirmDeleteBtn);

        ActionHelper.waitForVisibility(successToast);

        return this;
    }

    public AddLocation editLocationByName(String userName) {
        By editIcon = By.xpath(String.format(userActionsXpath, userName) + "[2]");

        ActionHelper.waitForPresence(editIcon);
        ActionHelper.actionClick(editIcon);

        ActionHelper.waitForVisibility(editUserTitle);

        return new AddLocation();
    }

    public Locations deleteSelectedLocation(){
        ActionHelper.waitForVisibility(deleteSelectedButton);
        ActionHelper.click(deleteSelectedButton);
        ActionHelper.waitForVisibility(confirmDeleteBtn);
        ActionHelper.click(confirmDeleteBtn);
        ActionHelper.waitForVisibility(successToast);
        return this;
    }

}
