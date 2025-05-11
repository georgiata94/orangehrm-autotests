package com.demoproject.tests;

import com.demoproject.navigatorpages.Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Random;

public class LoginWithNewlyCreatedUserTest extends BaseTest{

    private static final Logger log = LoggerFactory.getLogger(LoginWithNewlyCreatedUserTest.class);

    @Test
    public void test() {

        String password = "Random123";
        String emplLastName = "LastName";

        String employeeId = String.valueOf(1000 + new java.util.Random().nextInt(9000));

        String username = new Random().ints(6, 'a', 'z' + 1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String employeeName = new Random().ints(6, 'a', 'z' + 1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String employeeFullName = employeeName + " " + employeeName + " " + emplLastName;

        log.info("Create new employee");

        Navigator.getInstance().getOrange(true)
                .getPIM(true)
                .getAddEmployee(true)
                .fillFirstName(employeeName)
                .fillMiddleName(employeeName)
                .fillLastName(emplLastName)
                .fillEmployeeId(employeeId)
                .clickSaveButton();

        log.info("Create new user");

        Navigator.getInstance().getOrange(false)
                .openNavBar()
                .getAdmin(true)
                .getUserManagement(true)
                .getUsers(true)
                .clickAddBtn()
                .fillEmployeeName(employeeName, employeeFullName)
                .fillUserNameField(username)
                .selectStatusByText("Enabled")
                .selectUserRoleByText("ESS")
                .fillPasswordField(password)
                .fillConfirmPasswordField(password)
                .clickSaveButton();

        log.info("User has been created successfully.");

        Navigator.getInstance().getOrange(false)
                .clickLogout();

        log.info("User log out successfully.");

        Navigator.getInstance().getOrange(username,password)
                .getMyInfo(true)
                .validateUser(employeeName,emplLastName);

        log.info("Newly created user has logged in successfully.");
        log.info("The test passed successfully.");
    }
}
