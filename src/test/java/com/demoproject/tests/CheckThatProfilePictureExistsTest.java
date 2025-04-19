package com.demoproject.tests;

import com.demoproject.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckThatProfilePictureExistsTest extends BaseTest {

    @Test
    public void test() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();

        boolean isProfilePictureExists = driver.findElement(By.xpath("//img[@alt='profile picture']")).isDisplayed();
        Assert.assertTrue(isProfilePictureExists);

    }
}
