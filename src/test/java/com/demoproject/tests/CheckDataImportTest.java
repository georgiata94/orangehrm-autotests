package com.demoproject.tests;

import com.demoproject.navigatorpages.Navigator;
import com.demoproject.utils.ActionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CheckDataImportTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(CheckDataImportTest.class);
    private final String filePath = "C:\\Selenium-Demo\\SeleniumDemo\\uploads\\deepseek_csv_20250515_c55327.csv";

    @Test
    public void test() {

        logger.info("Delete all employees in the system.");
        Navigator.getInstance().getOrange(true)
                .getPIM(true)
                .getEmployeeList(true)
                .deleteAllEmployees();

        logger.info("Starting data import test...");

        logger.info("Navigating to Data Import page...");
        Navigator.getInstance().getOrange(false)
                .getPIM(true)
                .getConfiguration(true)
                .getDataImport(true)
                .uploadData(filePath);
        logger.info("Data file uploaded: {}", filePath);

        logger.info("Navigating to Employee List page to verify imported data...");
        Navigator.getInstance().getOrange(false)
                .getPIM(false)
                .getEmployeeList(true)
                .sortEmployeeById("Ascending","Id");

        String[][] employees = {
                {"01234", "Laura Michelle", "Martinez"},
                {"12345", "John Michael", "Smith"},
                {"23456", "Emily Anne", "Johnson"},
                {"34567", "David Robert", "Williams"},
                {"45678", "Sarah Jane", "Brown"},
                {"56789", "Michael James", "Davis"},
                {"67890", "Jessica Lee", "Wilson"},
                {"78901", "Christopher Paul", "Miller"},
                {"89012", "Amanda Rose", "Taylor"},
                {"90123", "Daniel Thomas", "Anderson"}
        };

        logger.info("Verifying imported employee data in the table...");

        Duration originalWait = getDriver().manage().timeouts().getImplicitWaitTimeout();

        int totalRows = getDriver().findElements(By.xpath("//div[@class='oxd-table-card']")).size();
        int employeeIndex = 0;
        boolean adminFoundAndSkipped = false;

        for (int row = 1; row <= totalRows && employeeIndex < employees.length; row++) {
            logger.info("Processing row {}", row);

            if (!adminFoundAndSkipped) {
                getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
                List<WebElement> adminCheckboxes = getDriver().findElements(
                        By.xpath("//div[@class='oxd-table-card']["+row+"]//div[@class='oxd-table-card-cell-hidden']"));

                if (!adminCheckboxes.isEmpty()) {
                    logger.info("Skipping admin at row {}", row);
                    adminFoundAndSkipped = true;
                    continue;
                }
            }

            logger.info("Verifying employee {} at row {}", employeeIndex+1, row);

            for (int col = 1; col <= 3; col++) {
                String actualValue = ActionHelper.getText(
                        By.xpath("(//div[@class='oxd-table-card'][" + row + "]//div[@role='cell'])[" + (col + 1) + "]")
                );
                String expectedValue = employees[employeeIndex][col - 1];

                logger.debug("Expected: '{}', Actual: '{}'", expectedValue, actualValue);
                Assert.assertEquals(actualValue, expectedValue,
                        String.format("Mismatch at employee %d (row %d), column %d",
                                employeeIndex+1, row, col));
            }

            employeeIndex++;
        }


        if (employeeIndex < employees.length) {
            Assert.fail(String.format("Only %d out of %d employees were checked. Missing employees in table.",
                    employeeIndex, employees.length));
        }
        getDriver().manage().timeouts().implicitlyWait(originalWait);

        logger.info("All employee records matched successfully.");
    }
}
