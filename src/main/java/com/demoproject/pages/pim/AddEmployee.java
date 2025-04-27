package com.demoproject.pages.pim;

import com.demoproject.pages.PIM;
import com.demoproject.pages.admin.usermanagement.Users;
import com.demoproject.utils.ActionHelper;
import org.openqa.selenium.By;

import javax.swing.*;

public class AddEmployee {

    private static final By employeeFirstName = By.xpath("//input[@name='firstName']");
    private static final By employeeMiddleName = By.xpath("//input[@name='middleName']");
    private static final By employeeLastName = By.xpath("//input[@name='lastName']");
    private static final By radioButton = By.xpath("//input[@type='checkbox']");
    private static final By username = By.xpath("//label[text()='Username']/../following-sibling::div//input[@autocomplete='off']");
    private static final By password = By.xpath("//label[text()='Password']/../following-sibling::div//input[@autocomplete='off']");
    private static final By confirmPassword = By.xpath("//label[text()='Confirm Password']/../following-sibling::div//input[@autocomplete='off']");
    private static final By enabledButton = By.xpath("//input[@type='radio' and @value='1']");
    private static final By disabledButton = By.xpath("//input[@type='radio' and @value='2']");
    private static final By cancelButton = By.xpath("//button[text()=' Cancel ']");
    private static final By saveButton = By.xpath("//button[text()=' Save ']");
    private static final By successToast = By.xpath("//div[@class='oxd-toast-start']");

    public AddEmployee fillFirstName(String firstName){

        ActionHelper.type(employeeFirstName,firstName);
        return this;
    }

    public AddEmployee fillMiddleName(String middleName){

        ActionHelper.type(employeeMiddleName,middleName);
        return this;
    }

    public AddEmployee fillLastName(String lastName){

        ActionHelper.type(employeeLastName,lastName);
        return this;
    }

    public AddEmployee clickRadioButton(){

        ActionHelper.click(radioButton);
        return this;
    }

    public AddEmployee fillUserName(String uname){

        ActionHelper.type(username,uname);
        return this;
    }

    public AddEmployee fillPassword(String psw){

        ActionHelper.type(password,psw);
        return this;
    }


    public AddEmployee fillConfirmPassword(String confirmPsw){

        ActionHelper.type(confirmPassword,confirmPsw);
        return this;
    }

    public PIM clickCancelButton(){
        ActionHelper.click(cancelButton);
        return new PIM();
    }

    public PIM clickSaveButton(){
        ActionHelper.click(saveButton);
        ActionHelper.waitForVisibility(successToast);
        ActionHelper.waitForPageLoad();
        return new PIM();
    }

    public AddEmployee clickEnabled(){
        ActionHelper.click(enabledButton);
        return new AddEmployee();
    }

    public AddEmployee clickDisabled(){
        ActionHelper.click(disabledButton);
        return new AddEmployee();
    }
}
