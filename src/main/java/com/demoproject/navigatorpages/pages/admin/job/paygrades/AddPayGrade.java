package com.demoproject.navigatorpages.pages.admin.job.paygrades;

import com.demoproject.navigatorpages.pages.admin.job.BaseJobAddPage;
import com.demoproject.navigatorpages.pages.admin.job.PayGrades;

public class AddPayGrade extends BaseJobAddPage<AddPayGrade, PayGrades> {

    public AddPayGrade() {
        super(PayGrades::new);
    }

    @Override
    protected AddPayGrade self() {
        return this;
    }
}
