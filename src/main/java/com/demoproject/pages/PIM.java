package com.demoproject.pages;


import com.demoproject.pages.pim.AddEmployee;
import com.demoproject.pages.pim.Configuration;
import com.demoproject.pages.pim.EmployeeList;
import com.demoproject.pages.pim.Reports;
import com.demoproject.utils.ActionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class PIM {

    protected final Logger logger = LogManager.getLogger(getClass());

    private static final By configurationPage = By.xpath("//li//text()[normalize-space()='Configuration']/..");
    private static final By employeeListPage = By.xpath("//li//text()[normalize-space()='Employee List']/..");
    private static final By addEmployeePage = By.xpath("//li//text()[normalize-space()='Add Employee']/..");
    private static final By reportsPage = By.xpath("//li//text()[normalize-space()='Reports']/..");

    public Configuration getConfiguration() {
        ActionHelper.waitForVisibility(configurationPage);
        ActionHelper.click(configurationPage);
        return new Configuration();
    }

    public Configuration getConfiguration(boolean performNavigation) {
        if (!performNavigation) {
            return new Configuration();
        }
        return getConfiguration();
    }

    public EmployeeList getEmployeeList() {
        ActionHelper.waitForVisibility(employeeListPage);
        ActionHelper.click(employeeListPage);
        return new EmployeeList();
    }

    public EmployeeList getEmployeeList(boolean performNavigation) {
        if (!performNavigation) {
            return new EmployeeList();
        }
        return getEmployeeList();
    }

    public AddEmployee getAddEmployee() {
        ActionHelper.waitForVisibility(addEmployeePage);
        ActionHelper.click(addEmployeePage);
        return new AddEmployee();
    }

    public AddEmployee getAddEmployee(boolean performNavigation) {
        if (!performNavigation) {
            return new AddEmployee();
        }
        return getAddEmployee();
    }

    public Reports getReports() {
        ActionHelper.waitForVisibility(reportsPage);
        ActionHelper.click(reportsPage);
        return new Reports();
    }

    public Reports getReports(boolean performNavigation) {
        if (!performNavigation) {
            return new Reports();
        }
        return getReports();
    }
}
