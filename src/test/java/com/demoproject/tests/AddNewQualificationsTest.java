package com.demoproject.tests;

import com.demoproject.navigatorpages.Navigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewQualificationsTest extends BaseTest{

    private static final Logger log = LoggerFactory.getLogger(AddNewQualificationsTest.class);
    @Test
    public void test(){

        log.info("Check the skill count before adding new skill.");

        int resultBeforeCreate = Navigator.getInstance().getOrange(true)
                .getAdmin(true)
                .getQualifications(true)
                .getSkills(true)
                .getResultsCount();

        log.info("Create new skill");

        Navigator.getInstance().getOrange(false)
                .getAdmin(true)
                .getQualifications(true)
                .getSkills(true)
                .clickAddButton()
                .fillField("Coding skill"+ new java.util.Random().nextInt(10000))
                .fillDescriptionField("The best coder")
                .clickSaveButton();

        log.info("Check the skill count after creation.");

        int resultAfterCreate = Navigator.getInstance().getOrange(false)
                .getAdmin(true)
                .getQualifications(true)
                .getSkills(true)
                .getResultsCount();

        Assert.assertTrue(resultAfterCreate>resultBeforeCreate);

        log.info("The test passed successfully.");
    }
}
