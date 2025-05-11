package com.demoproject.tests;

import com.demoproject.navigatorpages.Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewUserAndChangeThePasswordTest extends BaseTest{

    private static final Logger log = LoggerFactory.getLogger(CreateEmployeeWithLoginDetailsAndLogInTest.class);

    @Test
    public void test() {

        String userName = new Random().ints(6, 'a', 'z' + 1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        String password = "Random123";
        String newPassword = "NewPassword123";
        String employeeId = String.valueOf(1000 + new Random().nextInt(9000));
        String emplFirstName = "FirstName";
        String emplMiddleName = "MiddleName";
        String emplLastName = "LastName";

        log.info("🧪 Starting employee creation process...");
        log.info("Generated username: {}", userName);
        log.info("Generated employee ID: {}", employeeId);

        Navigator.getInstance().getOrange(true)
                .getPIM(true)
                .getAddEmployee(true)
                .fillFirstName(emplFirstName)
                .fillLastName(emplLastName)
                .fillMiddleName(emplMiddleName)
                .fillEmployeeId(employeeId)
                .clickRadioButton()
                .fillUserName(userName)
                .fillPassword(password)
                .fillConfirmPassword(password)
                .clickEnabled()
                .clickSaveButton();

        log.info("✅ Employee created successfully. Proceeding to logout...");

        Navigator.getInstance().getOrange(false)
                .clickLogout();

        log.info("🔐 Logging in with newly created user and change the password");

        Navigator.getInstance().getOrange(userName, password)
                .changePassword(password,newPassword);

        log.info("🚪 Logging out after password change...");
        Navigator.getInstance().getOrange(false)
                .clickLogout();

        log.info("🔓 Logging in with the new password to verify login and user validation...");
        Navigator.getInstance().getOrange(userName,newPassword)
                .getMyInfo(true)
                .validateUser(emplFirstName,emplLastName);

        log.info("✅ Password change and re-login verification completed successfully.");
        log.info("✅ The test passed successfully.");
    }
}
