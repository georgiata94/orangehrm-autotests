package com.demoproject.navigatorpages.pages.admin.qualifications;

import com.demoproject.navigatorpages.pages.admin.qualifications.education.AddEducation;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public abstract class BaseQualifications<T extends BaseQualifications<T>> {

    private static final Logger logger = LogManager.getLogger(BaseQualifications.class);

    protected abstract T self();

    public <R> R clickAddButton(Supplier<R> nextPageSupplier) {
        logger.info("Clicking 'Add' button to navigate to next page.");
        ActionHelper.click(ButtonManager.get("common.button.add.xpath"));
        return nextPageSupplier.get();
    }

    public String getRecordsFound() {
        String recordsFound = ActionHelper.getText(ButtonManager.get("common.records.found.xpath"));
        logger.info("Records found: {}", recordsFound);
        return recordsFound;
    }

    public int getResultsCount() {
        By tableRowsLocator = By.xpath("//div[@class='oxd-table-body']/div");
        try {
            ActionHelper.waitForVisibility(tableRowsLocator);
            int resultsCount = ActionHelper.findElements(tableRowsLocator).size();
            logger.info("Number of results found: {}", resultsCount);
            return resultsCount;
        } catch (TimeoutException e) {
            logger.warn("Timeout occurred while fetching results count.");
            return 0;
        }
    }

    public T enableCheckBoxAll() {
        logger.info("Enabling 'Select All' checkbox.");
        By checkBoxAll = ButtonManager.get("common.checkbox.all.xpath","Username");
        toggleCheckbox(checkBoxAll, ActionHelper.CheckboxState.ENABLE);
        return self();
    }

    public T disableCheckBoxAll() {
        logger.info("Disabling 'Select All' checkbox.");
        By checkBoxAll = ButtonManager.get("common.checkbox.all.xpath","Username");
        toggleCheckbox(checkBoxAll, ActionHelper.CheckboxState.DISABLE);
        return self();
    }

    public T enableCheckBoxByName(String name) {
        logger.info("Enabling checkbox for user: {}", name);
        By checkBoxByName = ButtonManager.get("common.checkbox.user.xpath", name);
        toggleCheckbox(checkBoxByName, ActionHelper.CheckboxState.ENABLE);
        return self();
    }

    public T disableCheckBoxByName(String name) {
        logger.info("Disabling checkbox for user: {}", name);
        By checkBoxByName = ButtonManager.get("common.checkbox.user.xpath", name);
        toggleCheckbox(checkBoxByName, ActionHelper.CheckboxState.DISABLE);
        return self();
    }

    public T deleteByName(String name) {
        logger.info("Attempting to delete user: {}", name);
        By deleteIcon = ButtonManager.get("jobPage.userActionsDeleteIcon.xpath", name);
        ActionHelper.waitForVisibility(deleteIcon);
        ActionHelper.click(deleteIcon);

        By confirmDeleteBtn = ButtonManager.get("common.delete.confirm.xpath");
        ActionHelper.waitForVisibility(confirmDeleteBtn);
        ActionHelper.click(confirmDeleteBtn);

        By successToast = ButtonManager.get("common.toast.success.xpath");
        ActionHelper.waitForVisibility(successToast);
        logger.info("User {} successfully deleted.", name);
        return self();
    }

    protected void editByNameBase(String name) {
        logger.info("Editing user: {}", name);
        By editIcon = ButtonManager.get("jobPage.userActionsEditIcon.xpath", name);
        ActionHelper.waitForVisibility(editIcon);
        ActionHelper.click(editIcon);

        By editUserTitle = ButtonManager.get("common.actions.edit.xpath");
        ActionHelper.waitForVisibility(editUserTitle);
        logger.info("Edit page for user {} is now visible.", name);
    }

    public T deleteSelected() {
        logger.info("Deleting selected users.");
        By deleteSelectedButton = ButtonManager.get("common.actions.delete.xpath");
        ActionHelper.waitForVisibility(deleteSelectedButton);
        ActionHelper.click(deleteSelectedButton);

        By confirmDeleteBtn = ButtonManager.get("common.delete.confirm.xpath");
        ActionHelper.waitForVisibility(confirmDeleteBtn);
        ActionHelper.click(confirmDeleteBtn);

        By successToast = ButtonManager.get("common.toast.success.xpath");
        ActionHelper.waitForVisibility(successToast);
        logger.info("Selected users have been successfully deleted.");
        return self();
    }

    private void toggleCheckbox(By locator, ActionHelper.CheckboxState state) {
        logger.info("Toggling checkbox at locator {} to state: {}", locator, state);
        ActionHelper.waitForVisibility(locator);
        boolean shouldBeChecked = (state == ActionHelper.CheckboxState.ENABLE);
        ActionHelper.setCheckbox(locator, shouldBeChecked);

        if (shouldBeChecked) {
            ActionHelper.waitForCheckboxToBeEnabled(locator);
            logger.info("Checkbox is now enabled.");
        } else {
            ActionHelper.waitForCheckboxToBeDisabled(locator);
            logger.info("Checkbox is now disabled.");
        }
    }
}
