package com.demoproject.tests;

import com.demoproject.utils.Navigator;
import org.testng.annotations.Test;

public class CheckNewPropFileTest extends BaseTest{

    @Test
    public void test(){
        Navigator.getInstance().getOrange()
                .getAdmin(true)
                .getUserManagement(true)
                .getUsers(true)
                .clickAddBtn()
                .fillUserNameField("asd")
                .fillPasswordField("asd");
    }

}
