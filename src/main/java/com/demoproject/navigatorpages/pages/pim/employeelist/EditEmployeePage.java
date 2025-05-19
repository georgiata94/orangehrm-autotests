package com.demoproject.navigatorpages.pages.pim.employeelist;

import com.demoproject.navigatorpages.pages.pim.employeelist.editemployeepage.EditEmployeeJobPage;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditEmployeePage {

    private static final Logger logger = LogManager.getLogger(EditEmployeePage.class);


    public EditEmployeeJobPage getJob(){
        logger.info("Get employee job page.");

        ActionHelper.click(ButtonManager.get("pim.editEmployee.job"));

        return new EditEmployeeJobPage();
    }

}
