package com.demoproject.tests;

import com.demoproject.utils.Navigator;
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

        Navigator.getInstance().getOrange()
                .openNavBar()
                .getAdmin()
                .getUserManagement()
                .getUsers()
                .clickAddBtn()
                .fillEmployeeName()
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
