package com.demoproject.tests;

import com.demoproject.navigatorpages.Navigator;
import com.demoproject.utils.MyWebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckThatProfilePictureExistsTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(CheckThatProfilePictureExistsTest.class);

    @Test
    public void test() {

        Navigator.getInstance().getOrange(true);

        boolean isProfilePictureExists = getDriver().findElement(By.xpath("//img[@alt='profile picture']")).isDisplayed();
        Assert.assertTrue(isProfilePictureExists);

        log.info("The test passed successfully.");
    }
}
