package com.demoproject.navigatorpages.pages;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger logger = LogManager.getLogger(getClass());

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
        logger.info("BasePage initialized.");
    }

    private void navigateTo(String locatorKey) {
        ActionHelper.setDriver(driver);
        By locator = ButtonManager.get(locatorKey);
        ActionHelper.waitForVisibility(locator);
        ActionHelper.click(locator);
    }

    public LoginPage clickLogout() {
        logger.info("Logging out...");
        ActionHelper.setDriver(driver);
        ActionHelper.click(ButtonManager.get("header.userdropdown.xpath"));
        ActionHelper.click(ButtonManager.get("header.logout.xpath"));
        
        return new LoginPage(driver);
    }

    public BasePage openNavBar() {
        By searchInput = ButtonManager.get("navbar.search.input");
        if (!driver.findElement(searchInput).isDisplayed()) {
            logger.info("Opening navigation bar.");
            ActionHelper.click(ButtonManager.get("navbar.toggle.button"));
        } else {
            logger.info("Navigation bar already open.");
        }
        return this;
    }

    public void closeNavBar() {
        By searchInput = ButtonManager.get("navbar.search.input");
        if (driver.findElement(searchInput).isDisplayed()) {
            logger.info("Closing navigation bar.");
            ActionHelper.click(ButtonManager.get("navbar.toggle.button"));
        } else {
            logger.info("Navigation bar already closed.");
        }
    }

    public Dashboard getDashboard(boolean performNavigation) {
        if (performNavigation) {
            logger.info("Navigating to Dashboard.");
            navigateTo("menu.dashboard.xpath");
        }
        return new Dashboard(driver);
    }

    public Admin getAdmin(boolean performNavigation) {
        if (performNavigation) {
            logger.info("Navigating to Admin.");
            navigateTo("menu.admin.xpath");
        }
        return new Admin();
    }

    public Buzz getBuzz(boolean performNavigation) {
        if (performNavigation) {
            logger.info("Navigating to Buzz.");
            navigateTo("menu.buzz.xpath");
        }
        return new Buzz(driver);
    }

    public Claim getClaim(boolean performNavigation) {
        if (performNavigation) {
            logger.info("Navigating to Claim.");
            navigateTo("menu.claim.xpath");
        }
        return new Claim(driver);
    }

    public Directory getDirectory(boolean performNavigation) {
        if (performNavigation) {
            logger.info("Navigating to Directory.");
            navigateTo("menu.directory.xpath");
        }
        return new Directory(driver);
    }

    public Leave getLeave(boolean performNavigation) {
        if (performNavigation) {
            logger.info("Navigating to Leave.");
            navigateTo("menu.leave.xpath");
        }
        return new Leave(driver);
    }

    public Maintenance getMaintenance(boolean performNavigation) {
        if (performNavigation) {
            logger.info("Navigating to Maintenance.");
            navigateTo("menu.maintenance.xpath");
        }
        return new Maintenance(driver);
    }

    public MyInfo getMyInfo(boolean performNavigation) {
        if (performNavigation) {
            logger.info("Navigating to MyInfo.");
            navigateTo("menu.myinfo.xpath");
        }
        return new MyInfo(driver);
    }

    public Performance getPerformance(boolean performNavigation) {
        if (performNavigation) {
            logger.info("Navigating to Performance.");
            navigateTo("menu.performance.xpath");
        }
        return new Performance(driver);
    }

    public PIM getPIM(boolean performNavigation) {
        if (performNavigation) {
            logger.info("Navigating to PIM.");
            navigateTo("menu.pim.xpath");
        }
        return new PIM();
    }

    public Recruitment getRecruitment(boolean performNavigation) {
        if (performNavigation) {
            logger.info("Navigating to Recruitment.");
            navigateTo("menu.recruitment.xpath");
        }
        return new Recruitment(driver);
    }

    public Time getTime(boolean performNavigation) {
        if (performNavigation) {
            logger.info("Navigating to Time.");
            navigateTo("menu.time.xpath");
        }
        return new Time(driver);
    }
}
