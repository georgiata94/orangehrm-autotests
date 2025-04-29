package com.demoproject.pages.admin.job;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.openqa.selenium.By;

public abstract class BaseJobPage<T extends BaseJobPage<T>> {

    protected void clickAddButtonBase() {
        By addBtn = ButtonManager.get("common.button.add.xpath");
        ActionHelper.waitForVisibility(addBtn);
        ActionHelper.click(addBtn);
    }

    @SuppressWarnings("unchecked")
    public T enableCheckBoxAll() {
        By checkBoxAll = ButtonManager.get("common.checkbox.all.xpath");
        toggleCheckbox(checkBoxAll, ActionHelper.CheckboxState.ENABLE);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T disableCheckBoxAll() {
        By checkBoxAll = ButtonManager.get("common.checkbox.all.xpath");
        toggleCheckbox(checkBoxAll, ActionHelper.CheckboxState.DISABLE);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T enableCheckBoxByName(String name) {
        By checkBoxByName = ButtonManager.get("common.checkbox.user.xpath", name);
        toggleCheckbox(checkBoxByName, ActionHelper.CheckboxState.ENABLE);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T disableCheckBoxByName(String name) {
        By checkBoxByName = ButtonManager.get("common.checkbox.user.xpath", name);
        toggleCheckbox(checkBoxByName, ActionHelper.CheckboxState.DISABLE);
        return (T) this;
    }

    private void toggleCheckbox(By locator, ActionHelper.CheckboxState state) {
        ActionHelper.waitForVisibility(locator);
        boolean shouldBeChecked = (state == ActionHelper.CheckboxState.ENABLE);
        ActionHelper.setCheckbox(locator, shouldBeChecked);

        if (shouldBeChecked) {
            ActionHelper.waitForCheckboxToBeEnabled(locator);
        } else {
            ActionHelper.waitForCheckboxToBeDisabled(locator);
        }
    }

    @SuppressWarnings("unchecked")
    public T deleteByName(String name) {
        By deleteIcon = ButtonManager.get("jobPage.userActionsDeleteIcon.xpath", name);
        ActionHelper.waitForVisibility(deleteIcon);
        ActionHelper.click(deleteIcon);

        By confirmDeleteBtn = ButtonManager.get("common.delete.confirm.xpath");
        ActionHelper.waitForVisibility(confirmDeleteBtn);
        ActionHelper.click(confirmDeleteBtn);

        By successToast = ButtonManager.get("common.toast.success.xpath");
        ActionHelper.waitForVisibility(successToast);
        return (T) this;
    }

    protected void editByNameBase(String name) {
        By editIcon = ButtonManager.get("jobPage.userActionsEditIcon.xpath", name);
        ActionHelper.waitForVisibility(editIcon);
        ActionHelper.click(editIcon);

        By editUserTitle = ButtonManager.get("common.actions.edit.xpath");
        ActionHelper.waitForVisibility(editUserTitle);
    }

    @SuppressWarnings("unchecked")
    public T deleteSelected() {
        By deleteSelectedButton = ButtonManager.get("common.actions.delete.xpath");
        ActionHelper.waitForVisibility(deleteSelectedButton);
        ActionHelper.click(deleteSelectedButton);

        By confirmDeleteBtn = ButtonManager.get("common.delete.confirm.xpath");
        ActionHelper.waitForVisibility(confirmDeleteBtn);
        ActionHelper.click(confirmDeleteBtn);

        By successToast = ButtonManager.get("common.toast.success.xpath");
        ActionHelper.waitForVisibility(successToast);
        return (T) this;
    }
}
