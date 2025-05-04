package com.demoproject.navigatorpages.pages.admin.qualifications;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public abstract class BaseAddQualificationPage<T extends BaseAddQualificationPage<T>> {

    private static final Logger logger = LogManager.getLogger(BaseAddQualificationPage.class);

    protected abstract T self();

    public T fillField(String text) {
        logger.info("Filling the qualification field with text: {}", text);
        ActionHelper.type(ButtonManager.get("admin.organization.add.xpath"), text);
        return self();
    }

    public T clickCancelButton() {
        logger.info("Clicking 'Cancel' button.");
        ActionHelper.click(ButtonManager.get("common.button.cancel.xpath"));
        return self();
    }

    public <R> R clickSaveButton(Supplier<R> nextPageSupplier) {
        logger.info("Clicking 'Save' button.");
        ActionHelper.click(ButtonManager.get("common.button.save.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));
        logger.info("Save operation completed, navigating to next page.");
        return nextPageSupplier.get();
    }
}
