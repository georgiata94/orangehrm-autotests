package com.demoproject.navigatorpages.pages.admin.job;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.openqa.selenium.By;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseJobPage<T extends BaseJobPage<T>> {

    private static final Logger logger = LogManager.getLogger(BaseJobPage.class);

    protected void clickAddButtonBase() {
        By addBtn = ButtonManager.get("common.button.add.xpath");
        ActionHelper.waitForVisibility(addBtn);
        ActionHelper.click(addBtn);
        logger.info("Clicked 'Add' button.");
    }

    @SuppressWarnings("unchecked")
    public T enableCheckBoxAll() {
        By checkBoxAll = ButtonManager.get("common.checkbox.all.xpath");
        toggleCheckbox(checkBoxAll, ActionHelper.CheckboxState.ENABLE);
        logger.info("Enabled 'Select All' checkbox.");
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T disableCheckBoxAll() {
        By checkBoxAll = ButtonManager.get("common.checkbox.all.xpath");
        toggleCheckbox(checkBoxAll, ActionHelper.CheckboxState.DISABLE);
        logger.info("Disabled 'Select All' checkbox.");
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T enableCheckBoxByName(String name) {
        By checkBoxByName = ButtonManager.get("common.checkbox.user.xpath", name);
        toggleCheckbox(checkBoxByName, ActionHelper.CheckboxState.ENABLE);
        logger.info("Enabled checkbox for user: {}", name);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T disableCheckBoxByName(String name) {
        By checkBoxByName = ButtonManager.get("common.checkbox.user.xpath", name);
        toggleCheckbox(checkBoxByName, ActionHelper.CheckboxState.DISABLE);
        logger.info("Disabled checkbox for user: {}", name);
        return (T) this;
    }

    private void toggleCheckbox(By locator, ActionHelper.CheckboxState state) {
        ActionHelper.waitForVisibility(locator);
        boolean shouldBeChecked = (state == ActionHelper.CheckboxState.ENABLE);
        ActionHelper.setCheckbox(locator, shouldBeChecked);

        if (shouldBeChecked) {
            ActionHelper.waitForCheckboxToBeEnabled(locator);
            logger.info("Checkbox enabled.");
        } else {
            ActionHelper.waitForCheckboxToBeDisabled(locator);
            logger.info("Checkbox disabled.");
        }
    }

    @SuppressWarnings("unchecked")
    public T deleteByName(String name) {
        By deleteIcon = ButtonManager.get("jobPage.userActionsDeleteIcon.xpath", name);
        ActionHelper.waitForVisibility(deleteIcon);
        ActionHelper.click(deleteIcon);
        logger.info("Clicked delete icon for user: {}", name);

        By confirmDeleteBtn = ButtonManager.get("common.delete.confirm.xpath");
        ActionHelper.waitForVisibility(confirmDeleteBtn);
        ActionHelper.click(confirmDeleteBtn);
        logger.info("Confirmed deletion for user: {}", name);

        By successToast = ButtonManager.get("common.toast.success.xpath");
        ActionHelper.waitForVisibility(successToast);
        logger.info("Successfully deleted user: {}", name);

        return (T) this;
    }

    protected void editByNameBase(String name) {
        By editIcon = ButtonManager.get("jobPage.userActionsEditIcon.xpath", name);
        ActionHelper.waitForVisibility(editIcon);
        ActionHelper.click(editIcon);
        logger.info("Clicked edit icon for user: {}", name);

        By editUserTitle = ButtonManager.get("common.actions.edit.xpath");
        ActionHelper.waitForVisibility(editUserTitle);
        logger.info("Navigated to edit page for user: {}", name);
    }

    @SuppressWarnings("unchecked")
    public T deleteSelected() {
        By deleteSelectedButton = ButtonManager.get("common.actions.delete.xpath");
        ActionHelper.waitForVisibility(deleteSelectedButton);
        ActionHelper.click(deleteSelectedButton);
        logger.info("Clicked 'Delete Selected' button.");

        By confirmDeleteBtn = ButtonManager.get("common.delete.confirm.xpath");
        ActionHelper.waitForVisibility(confirmDeleteBtn);
        ActionHelper.click(confirmDeleteBtn);
        logger.info("Confirmed 'Delete Selected' action.");

        By successToast = ButtonManager.get("common.toast.success.xpath");
        ActionHelper.waitForVisibility(successToast);
        logger.info("Successfully deleted selected users.");

        return (T) this;
    }
}
