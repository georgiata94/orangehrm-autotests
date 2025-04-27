package com.demoproject.pages;

import com.demoproject.utils.ActionHelper;
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
    }

    private static final By dashboardTab = By.xpath("//span[text()='Dashboard']");
    private static final By adminTab = By.xpath("//span[text()='Admin']");
    private static final By buzzTab = By.xpath("//span[text()='Buzz']");
    private static final By claimTab = By.xpath("//span[text()='Claim']");
    private static final By directoryTab = By.xpath("//span[text()='Directory']");
    private static final By leaveTab = By.xpath("//span[text()='Leave']");
    private static final By maintenanceTab = By.xpath("//span[text()='Maintenance']");
    private static final By myInfoTab = By.xpath("//span[text()='My Info']");
    private static final By performanceTab = By.xpath("//span[text()='Performance']");
    private static final By pimTab = By.xpath("//span[text()='PIM']");
    private static final By recruitmentTab = By.xpath("//span[text()='Recruitment']");
    private static final By timeTab = By.xpath("//span[text()='Time']");
    private static final By userInfoDropdown = By.xpath("//p[@class='oxd-userdropdown-name']");
    private static final By logoutButton = By.xpath("//a[text()='Logout']");
    private static final By navBarButton = By.xpath("//button//i[@class='oxd-icon bi-chevron-left']");
    private static final By navBarSearchButton = By.xpath("//button//i[@class='oxd-icon bi-chevron-left']");

    private void clickTab(By locator, WebDriver driver) {
        ActionHelper.setDriver(driver);
        ActionHelper.click(locator);
    }

    public LoginPage clickLogout() {
        ActionHelper.setDriver(driver);
        ActionHelper.click(userInfoDropdown);
        ActionHelper.click(logoutButton);
        return new LoginPage(driver);
    }

    public BasePage openNavBar(){
        if(!driver.findElement(By.xpath("//input[@placeholder='Search']")).isDisplayed()){
            ActionHelper.click(navBarButton);
        } else {
            logger.info("Navigation bar is already opened.");
        }
        return this;
    }

    public void closeNavBar(){
        if(driver.findElement(By.xpath("//input[@placeholder='Search']")).isDisplayed()){
            ActionHelper.click(navBarButton);
        } else {
            logger.info("Navigation bar is already closed.");
        }
    }

    public Dashboard getDashboard(WebDriver driver) {
        clickTab(dashboardTab, driver);
        return new Dashboard(driver);
    }

    public Dashboard getDashboard(WebDriver driver, boolean performNavigation) {
        if (!performNavigation) {
            return new Dashboard(driver);
        }
        return getDashboard(driver);
    }

    public Admin getAdmin() {
        clickTab(adminTab, driver);
        return new Admin();
    }

    public Admin getAdmin(boolean performNavigation) {
        if (!performNavigation) {
            return new Admin();
        }
        return getAdmin();
    }

    public Buzz getBuzz(WebDriver driver) {
        clickTab(buzzTab, driver);
        return new Buzz(driver);
    }

    public Buzz getBuzz(WebDriver driver, boolean performNavigation) {
        if (!performNavigation) {
            return new Buzz(driver);
        }
        return getBuzz(driver);
    }

    public Claim getClaim(WebDriver driver) {
        clickTab(claimTab, driver);
        return new Claim(driver);
    }

    public Claim getClaim(WebDriver driver, boolean performNavigation) {
        if (!performNavigation) {
            return new Claim(driver);
        }
        return getClaim(driver);
    }

    public Directory getDirectory(WebDriver driver) {
        clickTab(directoryTab, driver);
        return new Directory(driver);
    }

    public Directory getDirectory(WebDriver driver, boolean performNavigation) {
        if (!performNavigation) {
            return new Directory(driver);
        }
        return getDirectory(driver);
    }

    public Leave getLeave(WebDriver driver) {
        clickTab(leaveTab, driver);
        return new Leave(driver);
    }

    public Leave getLeave(WebDriver driver, boolean performNavigation) {
        if (!performNavigation) {
            return new Leave(driver);
        }
        return getLeave(driver);
    }

    public Maintenance getMaintenance(WebDriver driver) {
        clickTab(maintenanceTab, driver);
        return new Maintenance(driver);
    }

    public Maintenance getMaintenance(WebDriver driver, boolean performNavigation) {
        if (!performNavigation) {
            return new Maintenance(driver);
        }
        return getMaintenance(driver);
    }

    public MyInfo getMyInfo(WebDriver driver) {
        clickTab(myInfoTab, driver);
        return new MyInfo(driver);
    }

    public MyInfo getMyInfo(WebDriver driver, boolean performNavigation) {
        if (!performNavigation) {
            return new MyInfo(driver);
        }
        return getMyInfo(driver);
    }

    public Performance getPerformance(WebDriver driver) {
        clickTab(performanceTab, driver);
        return new Performance(driver);
    }

    public Performance getPerformance(WebDriver driver, boolean performNavigation) {
        if (!performNavigation) {
            return new Performance(driver);
        }
        return getPerformance(driver);
    }

    public PIM getPIM(WebDriver driver) {
        clickTab(pimTab, driver);
        return new PIM(driver);
    }

    public PIM getPIM(WebDriver driver, boolean performNavigation) {
        if (!performNavigation) {
            return new PIM(driver);
        }
        return getPIM(driver);
    }

    public Recruitment getRecruitment(WebDriver driver) {
        clickTab(recruitmentTab, driver);
        return new Recruitment(driver);
    }

    public Recruitment getRecruitment(WebDriver driver, boolean performNavigation) {
        if (!performNavigation) {
            return new Recruitment(driver);
        }
        return getRecruitment(driver);
    }

    public Time getTime(WebDriver driver) {
        clickTab(timeTab, driver);
        return new Time(driver);
    }

    public Time getTime(WebDriver driver, boolean performNavigation) {
        if (!performNavigation) {
            return new Time(driver);
        }
        return getTime(driver);
    }
}