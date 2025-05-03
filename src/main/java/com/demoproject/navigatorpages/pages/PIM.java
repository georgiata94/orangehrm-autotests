package com.demoproject.navigatorpages.pages;

import com.demoproject.navigatorpages.pages.pim.AddEmployee;
import com.demoproject.navigatorpages.pages.pim.Configuration;
import com.demoproject.navigatorpages.pages.pim.EmployeeList;
import com.demoproject.navigatorpages.pages.pim.Reports;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PIM {

    protected final Logger logger = LogManager.getLogger(getClass());

    public Configuration getConfiguration(boolean performNavigation) {
        if (performNavigation) {
            ActionHelper.waitForVisibility(ButtonManager.get("pim.configurationPage.xpath"));
            ActionHelper.click(ButtonManager.get("pim.configurationPage.xpath"));
        }
        return new Configuration();
    }

    public EmployeeList getEmployeeList(boolean performNavigation) {
        if (performNavigation) {
            ActionHelper.waitForVisibility(ButtonManager.get("pim.employeeListPage.xpath"));
            ActionHelper.click(ButtonManager.get("pim.employeeListPage.xpath"));
        }
        return new EmployeeList();
    }

    public AddEmployee getAddEmployee(boolean performNavigation) {
        if (performNavigation) {
            ActionHelper.waitForVisibility(ButtonManager.get("pim.addEmployeePage.xpath"));
            ActionHelper.click(ButtonManager.get("pim.addEmployeePage.xpath"));
        }
        return new AddEmployee();
    }

    public Reports getReports(boolean performNavigation) {
        if (performNavigation) {
            ActionHelper.waitForVisibility(ButtonManager.get("pim.reportsPage.xpath"));
            ActionHelper.click(ButtonManager.get("pim.reportsPage.xpath"));
        }
        return new Reports();
    }
}
