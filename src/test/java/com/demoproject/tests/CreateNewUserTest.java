package com.demoproject.tests;

import com.demoproject.navigatorpages.Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Random;

public class CreateNewUserTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(CreateNewUserTest.class);

    @Test
    public void test(){

        String username = new Random().ints(6, 'a', 'z' + 1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String employeeName = new Random().ints(6, 'a', 'z' + 1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String employeeFullName = employeeName + " " + employeeName + " " + employeeName;
        String employeeId = String.valueOf(1000 + new Random().nextInt(9000));

        log.info("Create new employee");

        Navigator.getInstance().getOrange(true)
                .getPIM(true)
                .getAddEmployee(true)
                .fillFirstName(employeeName)
                .fillMiddleName(employeeName)
                .fillLastName(employeeName)
                .fillEmployeeId(employeeId)
                .clickSaveButton();

        log.info("Create new user");

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

        log.info("User has been created successfully.");
        log.info("The test passed successfully.");

    }
}
