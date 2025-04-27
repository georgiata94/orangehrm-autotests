package com.demoproject.pages.admin.job;

import com.demoproject.utils.ActionHelper;
import org.openqa.selenium.By;

public abstract class BaseJobPage<T extends BaseJobPage<T>> {

    protected static final By addBtn = By.xpath("//text()[contains(., 'Add')]/ancestor::button");
    protected static final By checkBoxAll = By.xpath("//*[text()='Username']/preceding-sibling::div//input[@type='checkbox']");
    protected static final String userActionsXpath = "//div[text()='%s']/../following-sibling::div//i";
    protected static final By confirmDeleteBtn = By.xpath("//div[@class='orangehrm-modal-footer']//button//i");
    protected static final By successToast = By.xpath("//div[@class='oxd-toast-start']");
    protected static final By editUserTitle = By.xpath("//h6[text()='Edit User']");
    protected static final By deleteSelectedButton = By.xpath("//i[@class='oxd-icon bi-trash-fill oxd-button-icon']");

    protected void clickAddButtonBase() {
        ActionHelper.waitForVisibility(addBtn);
        ActionHelper.click(addBtn);
    }

    @SuppressWarnings("unchecked")
    public T enableCheckBoxAll() {
        toggleCheckbox(checkBoxAll, ActionHelper.CheckboxState.ENABLE);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T disableCheckBoxAll() {
        toggleCheckbox(checkBoxAll, ActionHelper.CheckboxState.DISABLE);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T enableCheckBoxByName(String name) {
        toggleCheckbox(getUserCheckboxLocator(name), ActionHelper.CheckboxState.ENABLE);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T disableCheckBoxByName(String name) {
        toggleCheckbox(getUserCheckboxLocator(name), ActionHelper.CheckboxState.DISABLE);
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
        By deleteIcon = By.xpath(String.format(userActionsXpath, name) + "[1]");
        ActionHelper.waitForVisibility(deleteIcon);
        ActionHelper.click(deleteIcon);

        ActionHelper.waitForVisibility(confirmDeleteBtn);
        ActionHelper.click(confirmDeleteBtn);

        ActionHelper.waitForVisibility(successToast);
        return (T) this;
    }

    protected void editByNameBase(String name) {
        By editIcon = By.xpath(String.format(userActionsXpath, name) + "[2]");
        ActionHelper.waitForVisibility(editIcon);
        ActionHelper.click(editIcon);
        ActionHelper.waitForVisibility(editUserTitle);
    }

    @SuppressWarnings("unchecked")
    public T deleteSelected() {
        ActionHelper.waitForVisibility(deleteSelectedButton);
        ActionHelper.click(deleteSelectedButton);

        ActionHelper.waitForVisibility(confirmDeleteBtn);
        ActionHelper.click(confirmDeleteBtn);

        ActionHelper.waitForVisibility(successToast);
        return (T) this;
    }

    // Локатор за чекбокса на конкретен потребител
    private By getUserCheckboxLocator(String userName) {
        return By.xpath("//div[text()='" + userName + "']/../preceding-sibling::div//input[@type='checkbox']");
    }
}
