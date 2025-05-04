package com.demoproject.navigatorpages.pages.admin;

import com.demoproject.navigatorpages.pages.admin.usermanagement.Users;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class UserManagement {

    protected final Logger logger = LogManager.getLogger(getClass());

    public Users getUsers(boolean performNavigation) {
        return navigateTo("admin.usermanagement.users.xpath", performNavigation, Users::new);
    }

    private <T> T navigateTo(String key, boolean performNavigation, Supplier<T> pageSupplier) {
        if (performNavigation) {
            By locator = ButtonManager.get(key);
            ActionHelper.waitForVisibility(locator);
            ActionHelper.click(locator);
            logger.info("Navigation performed.");
        }
        return pageSupplier.get();
    }
}
