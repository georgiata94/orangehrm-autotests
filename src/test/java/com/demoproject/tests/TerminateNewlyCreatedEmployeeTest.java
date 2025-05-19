package com.demoproject.tests;

import com.demoproject.navigatorpages.Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TerminateNewlyCreatedEmployeeTest extends BaseTest{

    private static final Logger log = LoggerFactory.getLogger(TerminateNewlyCreatedEmployeeTest.class);

    @Test
    public void test(){

        String employeeId = String.valueOf(1000 + new Random().nextInt(9000));
        String emplFirstName = "FirstName";
        String emplMiddleName = "MiddleName";
        String emplLastName = "LastName";

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        log.info("🧪 Starting employee creation process...");
        log.info("Generated employee ID: {}", employeeId);

        Navigator.getInstance().getOrange(true)
                .getPIM(true)
                .getAddEmployee(true)
                .fillFirstName(emplFirstName)
                .fillLastName(emplLastName)
                .fillMiddleName(emplMiddleName)
                .fillEmployeeId(employeeId)
                .clickSaveButton();

        log.info("✅ Employee created successfully.");

        Navigator.getInstance().getOrange(false)
                .getPIM(true)
                .getEmployeeList(true)
                .fillEmployeeId(employeeId)
                .clickSearchButton()
                .editFirstFoundEmployee()
                .getJob()
                .clickTerminateEmployment()
                .fillTerminationDate(formattedDate)
                .selectTerminationReason("Contract Not Renewed")
                .clickSaveButton();

        Navigator.getInstance().getOrange(false)
                .getPIM(false)
                .getEmployeeList(true)
                .fillEmployeeId(employeeId)
                .clickSearchButton()
                .validateRecordExists("No Records Found");

        log.info("✅ Employee terminated successfully.");
        log.info("The test passed successfully.");

    }
}
