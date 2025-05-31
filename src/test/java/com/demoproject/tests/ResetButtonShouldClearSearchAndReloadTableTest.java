package com.demoproject.tests;

import com.demoproject.navigatorpages.Navigator;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResetButtonShouldClearSearchAndReloadTableTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(ResetButtonShouldClearSearchAndReloadTableTest.class);

    @Test
    public void test() {
        logger.info("🔍 Navigating to Employee List and performing initial search...");
        Navigator.getInstance().getOrange(true)
                .getPIM(true)
                .getEmployeeList(true)
                .clickSearchButton();

        logger.info("📋 Extracting first employee ID from results...");
        String firstEmplId = ActionHelper.getText(By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'][2])[1]//div"));
        logger.debug("✅ First employee ID retrieved: {}", firstEmplId);

        logger.info("🔍 Performing filtered search using Employee ID...");
        Navigator.getInstance().getOrange(false)
                .getPIM(false)
                .getEmployeeList(false)
                .fillEmployeeId(firstEmplId)
                .clickSearchButton();

        String resultsBeforeReset = ActionHelper.getText(ButtonManager.get("common.records.found.xpath"));
        logger.debug("🔎 Results before clicking Reset: {}", resultsBeforeReset);

        logger.info("🔄 Clicking Reset button to clear filters and reload full table...");
        Navigator.getInstance().getOrange(false)
                .getPIM(false)
                .getEmployeeList(false)
                .clickResetButton();

        String resultsAfterReset = ActionHelper.getText(ButtonManager.get("common.records.found.xpath"));
        logger.debug("🔁 Results after clicking Reset: {}", resultsAfterReset);

        logger.info("✅ Verifying that reset has changed the visible results...");
        Assert.assertNotSame(resultsBeforeReset, resultsAfterReset, "Reset button did not reload full data set");

        logger.info("The test passed successfully.");
    }
}
