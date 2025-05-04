package com.demoproject.navigatorpages.pages.admin.job;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public abstract class BaseJobAddPage<T extends BaseJobAddPage<T, R>, R> {

    private static final Logger logger = LogManager.getLogger(BaseJobAddPage.class);
    private final Supplier<R> previousPageSupplier;

    protected BaseJobAddPage(Supplier<R> previousPageSupplier) {
        this.previousPageSupplier = previousPageSupplier;
        logger.info("Initialized BaseJobAddPage.");
    }

    protected abstract T self();

    public T fillField(String text) {
        logger.info("Filling field with text: {}", text);
        ActionHelper.type(ButtonManager.get("admin.organization.add.xpath"), text);
        return self();
    }

    public R clickCancelButton() {
        logger.info("Clicking Cancel button.");
        ActionHelper.click(ButtonManager.get("common.button.cancel.xpath"));
        return previousPageSupplier.get();
    }

    public R clickSaveButton() {
        logger.info("Clicking Save button.");
        ActionHelper.click(ButtonManager.get("common.button.save.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));
        logger.info("Save operation completed successfully.");
        return previousPageSupplier.get();
    }
}
