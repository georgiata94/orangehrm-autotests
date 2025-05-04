package com.demoproject.tests;

import com.demoproject.utils.ActionHelper;
import com.demoproject.navigatorpages.Navigator;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class EditUserTest extends BaseTest{

    private static final Logger log = LoggerFactory.getLogger(EditUserTest.class);

    @Test
    public void test(){

        String username = new Random().ints(6, 'a', 'z' + 1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String employeeName = new Random().ints(6, 'a', 'z' + 1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String employeeFullName = employeeName + " " + employeeName + " " + employeeName;

        String editedUserName = username + "_edited";

        log.info("Create new employee");

        Navigator.getInstance().getOrange(true)
                .getPIM(true)
                .getAddEmployee(true)
                .fillFirstName(employeeName)
                .fillMiddleName(employeeName)
                .fillLastName(employeeName)
                .clickSaveButton();

        log.info("Create new user and edit it.");

        Navigator.getInstance().getOrange(false)
                .openNavBar()
                .getAdmin(true)
                .getUserManagement(true)
                .getUsers(true)
                .clickAddBtn()
                .fillEmployeeName(employeeName,employeeFullName)
                .fillUserNameField(username)
                .selectStatusByText("Enabled")
                .selectUserRoleByText("ESS")
                .fillPasswordField("Random123")
                .fillConfirmPasswordField("Random123")
                .clickSaveButton();

        Navigator.getInstance().getOrange(false)
                .getAdmin(true)
                .getUserManagement(true)
                .getUsers(true)
                .fillUserNameField(username)
                .clickSearchBtn()
                .editUserByUserName(username)
                .fillUserNameField(editedUserName)
                .clickSaveButton();

        log.info("Search for edited user.");

        Navigator.getInstance().getOrange(false)
                .getAdmin(true)
                .getUserManagement(true)
                .getUsers(true)
                .fillUserNameField(editedUserName)
                .clickSearchBtn();

        ActionHelper.waitForVisibility(By.xpath("//hr[@role='separator']/following-sibling::div//span"));
        String expectedResult = "(1) Record Found";
        String actualResult = ActionHelper.getText(By.xpath("//hr[@role='separator']/following-sibling::div//span"));

        Assert.assertEquals(actualResult, expectedResult);

        log.info("User has been edited successfully.");
        log.info("The test passed successfully.");
    }
}
