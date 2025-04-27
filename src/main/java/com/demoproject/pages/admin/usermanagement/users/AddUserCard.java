package com.demoproject.pages.admin.usermanagement.users;

import com.demoproject.pages.admin.usermanagement.Users;
import com.demoproject.utils.ActionHelper;
import org.openqa.selenium.AcceptedW3CCapabilityKeys;
import org.openqa.selenium.By;

import javax.swing.*;

public class AddUserCard {

    private static final By userRoleDropDown = By.xpath("//label[text()='User Role']/../..//div[text()='-- Select --']");
    private static final By emplNameField = By.xpath("//input[@placeholder='Type for hints...']");
    private static final By statusDropDown = By.xpath("//label[text()='Status']/../..//div[text()='-- Select --']");
    private static final By usernameField = By.xpath("//label[text()='Username']/../following-sibling::div//input");
    private static final By passwordField = By.xpath("//label[text()='Password']/../following-sibling::div//input");
    private static final By confirmPasswordField = By.xpath("//label[text()='Confirm Password']/../following-sibling::div//input");
    private static final By cancelButton = By.xpath("//button[text()=' Cancel ']");
    private static final By saveButton = By.xpath("//button[text()=' Save ']");
    private static final By optionList = By.xpath("//div[@role='listbox']");
    private static final By successToast = By.xpath("//div[@class='oxd-toast-start']");

    public AddUserCard fillUserNameField(String userName){
        ActionHelper.waitForVisibility(usernameField);
        ActionHelper.type(usernameField,userName);
        return this;
    }

    public AddUserCard fillPasswordField(String password){
        ActionHelper.waitForVisibility(passwordField);
        ActionHelper.type(passwordField,password);
        return this;
    }

    public AddUserCard fillConfirmPasswordField(String confirmPassword){
        ActionHelper.waitForVisibility(confirmPasswordField);
        ActionHelper.type(confirmPasswordField,confirmPassword);
        return this;
    }

    public AddUserCard selectUserRoleByText(String userRole) {
        return selectDropdownOption(userRoleDropDown, userRole);
    }

    public AddUserCard selectStatusByText(String status) {
        return selectDropdownOption(statusDropDown, status);
    }

    private AddUserCard selectDropdownOption(By dropdownButton, String optionText) {
        ActionHelper.waitForVisibility(dropdownButton);
        ActionHelper.click(dropdownButton);

        ActionHelper.waitForVisibility(optionList);

        By optionLocator = By.xpath("//div[@role='option' and contains(normalize-space(), '" + optionText + "')]");
        ActionHelper.waitForVisibility(optionLocator);
        ActionHelper.click(optionLocator);

        return this;
    }

    public AddUserCard fillEmployeeName(){
        ActionHelper.waitForVisibility(emplNameField);
        ActionHelper.type(emplNameField,"a");
        ActionHelper.waitForVisibility(By.xpath("//div[@role='option' and contains(normalize-space(), 'A8DCo 4Ys 010Z')]"));
        ActionHelper.click(By.xpath("//div[@role='option' and contains(normalize-space(), 'A8DCo 4Ys 010Z')]"));
        return this;
    }

    public Users clickCancelButton(){
        ActionHelper.click(cancelButton);
        return new Users();
    }

    public Users clickSaveButton(){
        ActionHelper.click(saveButton);
        ActionHelper.waitForVisibility(successToast);
        return new Users();
    }
}
