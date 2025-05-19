package com.demoproject.navigatorpages.pages.pim;

import com.demoproject.navigatorpages.pages.pim.employeelist.EditEmployeePage;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class EmployeeList {

    private static final Logger logger = LogManager.getLogger(EmployeeList.class);

    public EmployeeList deleteAllEmployees() {

        logger.info("Attempting to delete all employees except the main admin.");

        ActionHelper.moveToElement(ButtonManager.get("common.records.found.xpath"));

        try {
            while (!ActionHelper.getText(ButtonManager.get("common.records.found.xpath")).equals("(1) Record Found")) {
                try {
                    logger.info("Selecting all employee records.");
                    ActionHelper.setCheckbox(ButtonManager.get("common.checkbox.all.xpath", "Id"),true);

                    logger.info("Clicking delete button.");
                    ActionHelper.click(ButtonManager.get("common.actions.delete.xpath"));
                    ActionHelper.click(ButtonManager.get("common.delete.confirm.xpath"));

                    logger.info("Waiting for success toast.");
                    ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));

                    logger.info("Waiting for records text to reappear after deletion.");
                    ActionHelper.waitForVisibility(ButtonManager.get("common.records.found.xpath"));
                } catch (Exception e) {
                    logger.warn("Failed during delete iteration: {}", e.getMessage());
                    throw new RuntimeException("Error while deleting employee records.", e);
                }
            }

            logger.info("All employees deleted except the main admin.");
        } catch (Exception e) {
            logger.error("Failed to delete employees: {}", e.getMessage());
            throw new RuntimeException("Error in deleteAllEmployees method.", e);
        }

        return this;
    }

    public EmployeeList sortEmployeeById(String ascendingOrDescending, String columnName) {
        try {
            logger.info("Attempting to sort Employee table by column: '{}' in '{}' order", columnName, ascendingOrDescending);
            ActionHelper.moveToElement(ButtonManager.get("common.sortTableBy.xpath", columnName));
            ActionHelper.click(ButtonManager.get("common.sortTableBy.xpath", columnName));
            logger.debug("Clicked on column header for sorting: {}", columnName);

            ActionHelper.click(ButtonManager.get("common.sortTableBy.ascendingOrDescending.xpath", columnName, ascendingOrDescending));
            logger.debug("Clicked on sort option: {}", ascendingOrDescending);

        } catch (Exception e) {
            logger.error("Failed to sort employee table by '{}' in '{}' order: {}", columnName, ascendingOrDescending, e.getMessage(), e);
            throw new RuntimeException("Error while sorting employee table.", e);
        }

        return this;
    }


    public EmployeeList fillEmployeeId(String employeeId){
        logger.info(("Fill the Employee Id field."));
        ActionHelper.waitForVisibility(ButtonManager.get("common.input.generic.xpath","Employee Id"));
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Employee Id"),employeeId);

        return this;
    }

    public EmployeeList clickSearchButton(){
        logger.info("Clic the search button.");
        ActionHelper.click(ButtonManager.get("common.button.search.xpath"));

        return this;
    }

    public EditEmployeePage editFirstFoundEmployee(){

        logger.info("Click edit button");
        ActionHelper.click(ButtonManager.get("pim.employeeList.editButton"));

        return new EditEmployeePage();
    }

    public void validateRecordExists(String record){

        logger.info("Validate records.");

        ActionHelper.waitForVisibility(ButtonManager.get("common.records.found.xpath"));
        String actualRecord = ActionHelper.getText(ButtonManager.get("common.records.found.xpath"));

        Assert.assertEquals(record,actualRecord);

    }


}
