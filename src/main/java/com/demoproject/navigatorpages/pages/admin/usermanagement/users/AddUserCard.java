package com.demoproject.navigatorpages.pages.admin.usermanagement.users;

import com.demoproject.navigatorpages.pages.admin.usermanagement.Users;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class AddUserCard {

    private static final Logger logger = LogManager.getLogger(AddUserCard.class);

    public AddUserCard fillUserNameField(String userName){
        logger.info("Filling username field with: {}", userName);
        ActionHelper.waitForVisibility(ButtonManager.get("common.input.generic.xpath","Username"));
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Username"), userName);
        return this;
    }

    public AddUserCard fillPasswordField(String password){
        logger.info("Filling password field with: {}", password);
        ActionHelper.waitForVisibility(ButtonManager.get("common.input.generic.xpath","Password"));
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Password"), password);
        return this;
    }

    public AddUserCard fillConfirmPasswordField(String confirmPassword){
        logger.info("Filling confirm password field with: {}", confirmPassword);
        ActionHelper.waitForVisibility(ButtonManager.get("common.input.generic.xpath","Confirm Password"));
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Confirm Password"), confirmPassword);
        return this;
    }

    public AddUserCard selectUserRoleByText(String userRole) {
        logger.info("Selecting user role: {}", userRole);
        return selectDropdownOption(ButtonManager.get("addUserCard.userRoleDropDown.xpath"), userRole);
    }

    public AddUserCard selectStatusByText(String status) {
        logger.info("Selecting user status: {}", status);
        return selectDropdownOption(ButtonManager.get("addUserCard.statusDropDown.xpath"), status);
    }

    private AddUserCard selectDropdownOption(By dropdownButton, String optionText) {
        logger.info("Clicking dropdown button to select option: {}", optionText);
        ActionHelper.waitForVisibility(dropdownButton);
        ActionHelper.click(dropdownButton);

        ActionHelper.waitForVisibility(ButtonManager.get("addUserCard.optionList.xpath"));

        By optionLocator = By.xpath("//div[@role='option' and contains(normalize-space(), '" + optionText + "')]");
        ActionHelper.waitForVisibility(optionLocator);
        ActionHelper.click(optionLocator);

        return this;
    }

    public AddUserCard fillEmployeeName(String emplName, String fullName){
        logger.info("Filling employee name field with: {}", emplName);
        ActionHelper.waitForVisibility(ButtonManager.get("addUserCard.emplNameField.xpath"));
        ActionHelper.type(ButtonManager.get("addUserCard.emplNameField.xpath"), emplName);
        ActionHelper.waitForVisibility(By.xpath("//div[@role='option']//span[text()='"+fullName+"']"));
        ActionHelper.click(By.xpath("//div[@role='option']//span[text()='"+fullName+"']"));
        return this;
    }

    public Users clickCancelButton(){
        logger.info("Clicking Cancel button.");
        ActionHelper.click(ButtonManager.get("common.button.cancel.xpath"));
        return new Users();
    }

    public Users clickSaveButton(){
        logger.info("Clicking Save button.");
        ActionHelper.click(ButtonManager.get("common.button.save.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));
        ActionHelper.waitForPageLoad();
        return new Users();
    }
}
