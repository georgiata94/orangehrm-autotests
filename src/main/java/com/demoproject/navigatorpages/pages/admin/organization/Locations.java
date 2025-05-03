package com.demoproject.navigatorpages.pages.admin.organization;

import com.demoproject.navigatorpages.pages.admin.organization.locations.AddLocation;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.openqa.selenium.By;

public class Locations {

    public Locations fillNameField(String text){
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Name"), text);
        return this;
    }

    public Locations fillCityField(String text){
        ActionHelper.type(ButtonManager.get("Customize Toolbar…"), text);
        return this;
    }

    public Locations selectCountryByText(String country){
        ActionHelper.click(ButtonManager.get("common.dropdown.generic.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.dropdown.option.xpath"));
        ActionHelper.jsScrollClick(ButtonManager.get("common.dropdown.option.xpath", country));
        return this;
    }

    public AddLocation clickAddButton() {
        ActionHelper.waitForVisibility(ButtonManager.get("common.button.add.xpath"));
        ActionHelper.click(ButtonManager.get("common.button.add.xpath"));
        return new AddLocation();
    }

    public Locations clickResetBtn(){
        ActionHelper.waitForVisibility(ButtonManager.get("common.button.reset.xpath"));
        ActionHelper.click(ButtonManager.get("common.button.reset.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.records.found.xpath"));
        return this;
    }

    public Locations clickSearchBtn(){
        ActionHelper.waitForVisibility(ButtonManager.get("common.button.search.xpath"));
        ActionHelper.click(ButtonManager.get("common.button.search.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.records.found.xpath"));
        return this;
    }

    public Locations enableCheckBoxAll() {
        return toggleCheckbox(ButtonManager.get("common.checkbox.all.xpath"), ActionHelper.CheckboxState.ENABLE);
    }

    public Locations disableCheckBoxAll() {
        return toggleCheckbox(ButtonManager.get("common.checkbox.all.xpath"), ActionHelper.CheckboxState.DISABLE);
    }

    public Locations enableCheckBoxByUserName(String userName) {
        return toggleCheckbox(ButtonManager.get("common.checkbox.user.xpath", userName), ActionHelper.CheckboxState.ENABLE);
    }

    public Locations disableCheckBoxByUserName(String userName) {
        return toggleCheckbox(ButtonManager.get("common.checkbox.user.xpath", userName), ActionHelper.CheckboxState.DISABLE);
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
        By deleteIcon = ButtonManager.get("locations.userActionsXpath.xpath", userName, 1);

        ActionHelper.waitForVisibility(deleteIcon);
        ActionHelper.click(deleteIcon);

        ActionHelper.waitForVisibility(ButtonManager.get("common.delete.confirm.xpath"));
        ActionHelper.click(ButtonManager.get("common.delete.confirm.xpath"));

        ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));

        return this;
    }

    public AddLocation editLocationByName(String userName) {
        By editIcon = ButtonManager.get("locations.userActionsXpath.xpath", userName, 2);

        ActionHelper.waitForPresence(editIcon);
        ActionHelper.actionClick(editIcon);

        ActionHelper.waitForVisibility(ButtonManager.get("common.actions.edit.xpath"));

        return new AddLocation();
    }

    public Locations deleteSelectedLocation(){
        ActionHelper.waitForVisibility(ButtonManager.get("common.actions.delete.xpath"));
        ActionHelper.click(ButtonManager.get("common.actions.delete.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.delete.confirm.xpath"));
        ActionHelper.click(ButtonManager.get("common.delete.confirm.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));
        return this;
    }
}
