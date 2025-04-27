package com.demoproject.pages.admin;

import com.demoproject.pages.Claim;
import com.demoproject.pages.admin.usermanagement.Users;
import com.demoproject.utils.ActionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserManagement {


    protected final Logger logger = LogManager.getLogger(getClass());


    private static final By users = By.xpath("//ul[@class='oxd-dropdown-menu']//li//a");

    public Users getUsers() {
        ActionHelper.waitForVisibility(users);
        ActionHelper.click(users);
        return new Users();
    }

    public Users getUsers(boolean performNavigation){
        if (!performNavigation) {
            return new Users();
        }
        return getUsers();
    }


}
