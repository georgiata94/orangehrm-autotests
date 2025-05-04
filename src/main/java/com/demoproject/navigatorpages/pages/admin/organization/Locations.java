package com.demoproject.navigatorpages.pages.admin.organization;

import com.demoproject.navigatorpages.pages.admin.organization.locations.AddLocation;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.openqa.selenium.By;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Locations {

    private static final Logger logger = LogManager.getLogger(Locations.class);

    public Locations fillNameField(String text) {
        logger.info("Filling 'Name' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Name"), text);
        return this;
    }

    public Locations fillCityField(String text) {
        logger.info("Filling 'City' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "City"), text);
        return this;
    }

    public Locations selectCountryByText(String country) {
        logger.info("Selecting country from dropdown: {}", country);
        ActionHelper.click(ButtonManager.get("common.dropdown.generic.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.dropdown.option.xpath"));
        ActionHelper.jsScrollClick(ButtonManager.get("common.dropdown.option.xpath", country));
        return this;
    }

    public AddLocation clickAddButton() {
        logger.info("Clicking 'Add' button.");
        ActionHelper.waitForVisibility(ButtonManager.get("common.button.add.xpath"));
        ActionHelper.click(ButtonManager.get("common.button.add.xpath"));
        return new AddLocation();
    }

    public Locations clickResetBtn() {
        logger.info("Clicking 'Reset' button.");
        ActionHelper.waitForVisibility(ButtonManager.get("common.button.reset.xpath"));
        ActionHelper.click(ButtonManager.get("common.button.reset.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.records.found.xpath"));
        return this;
    }

    public Locations clickSearchBtn() {
        logger.info("Clicking 'Search' button.");
        ActionHelper.waitForVisibility(ButtonManager.get("common.button.search.xpath"));
        ActionHelper.click(ButtonManager.get("common.button.search.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.records.found.xpath"));
        return this;
    }

    public Locations enableCheckBoxAll() {
        logger.info("Enabling 'Select All' checkbox.");
        return toggleCheckbox(ButtonManager.get("common.checkbox.all.xpath"), ActionHelper.CheckboxState.ENABLE);
    }

    public Locations disableCheckBoxAll() {
        logger.info("Disabling 'Select All' checkbox.");
        return toggleCheckbox(ButtonManager.get("common.checkbox.all.xpath"), ActionHelper.CheckboxState.DISABLE);
    }

    public Locations enableCheckBoxByUserName(String userName) {
        logger.info("Enabling checkbox for user: {}", userName);
        return toggleCheckbox(ButtonManager.get("common.checkbox.user.xpath", userName), ActionHelper.CheckboxState.ENABLE);
    }

    public Locations disableCheckBoxByUserName(String userName) {
        logger.info("Disabling checkbox for user: {}", userName);
        return toggleCheckbox(ButtonManager.get("common.checkbox.user.xpath", userName), ActionHelper.CheckboxState.DISABLE);
    }

    private Locations toggleCheckbox(By locator, ActionHelper.CheckboxState state) {
        ActionHelper.waitForVisibility(locator);

        boolean shouldBeChecked = (state == ActionHelper.CheckboxState.ENABLE);
        ActionHelper.setCheckbox(locator, shouldBeChecked);

        if (state == ActionHelper.CheckboxState.ENABLE) {
            logger.info("Checkbox enabled at locator: {}", locator);
            ActionHelper.waitForCheckboxToBeEnabled(locator);
        } else {
            logger.info("Checkbox disabled at locator: {}", locator);
            ActionHelper.waitForCheckboxToBeDisabled(locator);
        }
        return this;
    }

    public Locations deleteLocationByName(String userName) {
        logger.info("Attempting to delete location by name: {}", userName);
        By deleteIcon = ButtonManager.get("locations.userActionsXpath.xpath", userName, 1);

        ActionHelper.waitForVisibility(deleteIcon);
        ActionHelper.click(deleteIcon);

        ActionHelper.waitForVisibility(ButtonManager.get("common.delete.confirm.xpath"));
        ActionHelper.click(ButtonManager.get("common.delete.confirm.xpath"));

        ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));
        logger.info("Location deleted successfully.");
        return this;
    }

    public AddLocation editLocationByName(String userName) {
        logger.info("Editing location by name: {}", userName);
        By editIcon = ButtonManager.get("locations.userActionsXpath.xpath", userName, 2);

        ActionHelper.waitForPresence(editIcon);
        ActionHelper.actionClick(editIcon);

        ActionHelper.waitForVisibility(ButtonManager.get("common.actions.edit.xpath"));
        logger.info("Editing location: {}", userName);
        return new AddLocation();
    }

    public Locations deleteSelectedLocation() {
        logger.info("Attempting to delete selected location.");
        ActionHelper.waitForVisibility(ButtonManager.get("common.actions.delete.xpath"));
        ActionHelper.click(ButtonManager.get("common.actions.delete.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.delete.confirm.xpath"));
        ActionHelper.click(ButtonManager.get("common.delete.confirm.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));
        logger.info("Selected location deleted successfully.");
        return this;
    }
}
