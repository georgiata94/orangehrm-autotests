package com.demoproject.navigatorpages.pages.admin.qualifications;

import com.demoproject.navigatorpages.pages.admin.qualifications.education.AddEducation;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.util.function.Supplier;

public abstract class BaseQualifications<T extends BaseQualifications<T>> {

    protected abstract T self();

    public <R> R clickAddButton(Supplier<R> nextPageSupplier) {
        ActionHelper.click(ButtonManager.get("common.button.add.xpath"));
        return nextPageSupplier.get();
    }

    public String getRecordsFound() {
        return ActionHelper.getText(ButtonManager.get("common.records.found.xpath"));
    }

    public int getResultsCount() {
        By tableRowsLocator = By.xpath("//div[@class='oxd-table-body']/div");
        try {
            ActionHelper.waitForVisibility(tableRowsLocator);
            return ActionHelper.findElements(tableRowsLocator).size();
        } catch (TimeoutException e) {
            return 0;
        }
    }

    public T enableCheckBoxAll() {
        By checkBoxAll = ButtonManager.get("common.checkbox.all.xpath");
        toggleCheckbox(checkBoxAll, ActionHelper.CheckboxState.ENABLE);
        return self();
    }

    public T disableCheckBoxAll() {
        By checkBoxAll = ButtonManager.get("common.checkbox.all.xpath");
        toggleCheckbox(checkBoxAll, ActionHelper.CheckboxState.DISABLE);
        return self();
    }

    public T enableCheckBoxByName(String name) {
        By checkBoxByName = ButtonManager.get("common.checkbox.user.xpath", name);
        toggleCheckbox(checkBoxByName, ActionHelper.CheckboxState.ENABLE);
        return self();
    }

    public T disableCheckBoxByName(String name) {
        By checkBoxByName = ButtonManager.get("common.checkbox.user.xpath", name);
        toggleCheckbox(checkBoxByName, ActionHelper.CheckboxState.DISABLE);
        return self();
    }

    public T deleteByName(String name) {
        By deleteIcon = ButtonManager.get("jobPage.userActionsDeleteIcon.xpath", name);
        ActionHelper.waitForVisibility(deleteIcon);
        ActionHelper.click(deleteIcon);

        By confirmDeleteBtn = ButtonManager.get("common.delete.confirm.xpath");
        ActionHelper.waitForVisibility(confirmDeleteBtn);
        ActionHelper.click(confirmDeleteBtn);

        By successToast = ButtonManager.get("common.toast.success.xpath");
        ActionHelper.waitForVisibility(successToast);
        return self();
    }

    protected void editByNameBase(String name) {
        By editIcon = ButtonManager.get("jobPage.userActionsEditIcon.xpath", name);
        ActionHelper.waitForVisibility(editIcon);
        ActionHelper.click(editIcon);

        By editUserTitle = ButtonManager.get("common.actions.edit.xpath");
        ActionHelper.waitForVisibility(editUserTitle);
    }

    public T deleteSelected() {
        By deleteSelectedButton = ButtonManager.get("common.actions.delete.xpath");
        ActionHelper.waitForVisibility(deleteSelectedButton);
        ActionHelper.click(deleteSelectedButton);

        By confirmDeleteBtn = ButtonManager.get("common.delete.confirm.xpath");
        ActionHelper.waitForVisibility(confirmDeleteBtn);
        ActionHelper.click(confirmDeleteBtn);

        By successToast = ButtonManager.get("common.toast.success.xpath");
        ActionHelper.waitForVisibility(successToast);
        return self();
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
}
