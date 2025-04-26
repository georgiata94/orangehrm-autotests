package com.demoproject.pages.admin;

import com.demoproject.pages.admin.usermanagement.Users;
import com.demoproject.utils.ActionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class UserManagement {


    protected final Logger logger = LogManager.getLogger(getClass());


    private static final By users = By.xpath("//ul[@class='oxd-dropdown-menu']//li//a");

    public Users getUsers() {
        if (!isOnUsersPage()) {
            ActionHelper.waitForVisibility(users);
            ActionHelper.click(users);
        } else {
            logger.info("You are already on the Users page.");
        }
        return new Users();
    }

    private boolean isOnUsersPage() {
        return ActionHelper.isCurrentUrlContains("admin/viewSystemUsers");
    }


}
