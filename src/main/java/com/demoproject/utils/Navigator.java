package com.demoproject.utils;

import com.demoproject.pages.*;
import com.demoproject.pages.admin.usermanagement.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class Navigator {

    private static Navigator instance;
    private final WebDriver driver;
    private boolean isLoggedIn = false;
    protected final Logger logger = LogManager.getLogger(getClass());

    private Navigator(WebDriver driver) {
        this.driver = driver;
    }

    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator(MyWebDriverManager.getDriver());
        }
        return instance;
    }

    public Admin goToAdmin() {
        if (!isOnAdminPage()) {
            return new BasePage(driver).getAdmin();
        } else {
            logger.info("You are already on the Admin page.");
            return new Admin();
        }
    }

    private boolean isOnAdminPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("admin");
    }

    public Buzz goToBuzz() {
        if (!isOnBuzzPage()) {
            return new BasePage(driver).getBuzz(driver);
        } else {
            logger.info("You are already on the Buzz page.");
            return new Buzz(driver);
        }
    }

    private boolean isOnBuzzPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("buzz");
    }

    public Claim goToClaim() {
        if (!isOnClaimPage()) {
            return new BasePage(driver).getClaim(driver);
        } else {
            logger.info("You are already on the Claim page.");
            return new Claim(driver);
        }
    }

    private boolean isOnClaimPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("claim");
    }

    public Dashboard goToDashboard() {
        if (!isOnDashboardPage()) {
            return new BasePage(driver).getDashboard(driver);
        } else {
            logger.info("You are already on the Dashboard page.");
            return new Dashboard(driver);
        }
    }

    private boolean isOnDashboardPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("dashboard");
    }

    public Directory goToDirectory() {
        if (!isOnDirectoryPage()) {
            return new BasePage(driver).getDirectory(driver);
        } else {
            logger.info("You are already on the Directory page.");
            return new Directory(driver);
        }
    }

    private boolean isOnDirectoryPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("directory");
    }

    public Leave goToLeave() {
        if (!isOnLeavePage()) {
            return new BasePage(driver).getLeave(driver);
        } else {
            logger.info("You are already on the Leave page.");
            return new Leave(driver);
        }
    }

    private boolean isOnLeavePage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("leave");
    }

    public Maintenance goToMaintenance() {
        if (!isOnMaintenancePage()) {
            return new BasePage(driver).getMaintenance(driver);
        } else {
            logger.info("You are already on the Maintenance page.");
            return new Maintenance(driver);
        }
    }

    private boolean isOnMaintenancePage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("maintenance");
    }

    public MyInfo goToMyInfo() {
        if (!isOnMyInfoPage()) {
            return new BasePage(driver).getMyInfo(driver);
        } else {
            logger.info("You are already on the My Info page.");
            return new MyInfo(driver);
        }
    }

    private boolean isOnMyInfoPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("myinfo");
    }

    public Performance goToPerformance() {
        if (!isOnPerformancePage()) {
            return new BasePage(driver).getPerformance(driver);
        } else {
            logger.info("You are already on the Performance page.");
            return new Performance(driver);
        }
    }

    private boolean isOnPerformancePage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("performance");
    }

    public PIM goToPIM() {
        if (!isOnPIMPage()) {
            return new BasePage(driver).getPIM(driver);
        } else {
            logger.info("You are already on the PIM page.");
            return new PIM(driver);
        }
    }

    private boolean isOnPIMPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("pim");
    }

    public Recruitment goToRecruitment() {
        if (!isOnRecruitmentPage()) {
            return new BasePage(driver).getRecruitment(driver);
        } else {
            logger.info("You are already on the Recruitment page.");
            return new Recruitment(driver);
        }
    }

    private boolean isOnRecruitmentPage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("recruitment");
    }

    public Time goToTime() {
        if (!isOnTimePage()) {
            return new BasePage(driver).getTime(driver);
        } else {
            logger.info("You are already on the Time page.");
            return new Time(driver);
        }
    }

    private boolean isOnTimePage() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains("time");
    }

    public BasePage getOrange() {
        if (!isLoggedIn) {
            LoginPage loginPage = new LoginPage(driver);
            BasePage basePage = loginPage.login();
            isLoggedIn = true;
            return basePage;
        } else {
            logger.info("Already logged in. You're on the base page.");
            return new BasePage(driver);
        }
    }

}
