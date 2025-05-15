package com.demoproject.tests;

import com.demoproject.navigatorpages.Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class AddNewShiftTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(AddNewShiftTest.class);

    @Test
    public void test() {

        String shiftName = "Middle Shift" + new java.util.Random().nextInt(10000);
        log.info("Create new shift.");
        Navigator.getInstance().getOrange(true)
                .openNavBar()
                .getAdmin(true)
                .getJob(true)
                .getWorkShifts(true)
                .clickAddButton()
                .fillTimeFrom("03:00 AM")
                .fillTimeTo("09:00 AM")
                .fillShiftName(shiftName)
                .clickSaveButton()
                .validateWorkShift(shiftName);

        log.info("The test passed successfully.");
    }
}
