package com.demoproject.tests;

import com.demoproject.utils.Navigator;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckThatProfilePictureExistsTest extends BaseTest {

    @Test
    public void test() {

        Navigator.getInstance().getOrange()
                .getAdmin()
                .getUserManagement()
                .getUsers();

        boolean isProfilePictureExists = driver.findElement(By.xpath("//img[@alt='profile picture']")).isDisplayed();
        Assert.assertTrue(isProfilePictureExists);

    }
}
