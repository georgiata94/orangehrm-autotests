package com.demoproject.navigatorpages.pages.admin.qualifications;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;

import java.util.function.Supplier;

public abstract class BaseAddPage <T extends BaseAddPage<T>>{

    protected abstract T self();

    public T fillField(String text) {
        ActionHelper.type(ButtonManager.get("admin.organization.add.xpath"), text);
        return self();
    }

    public T clickCancelButton() {
        ActionHelper.click(ButtonManager.get("common.button.cancel.xpath"));
        return self();
    }

    public <R> R clickSaveButton(Supplier<R> nextPageSupplier) {
        ActionHelper.click(ButtonManager.get("common.button.save.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.toast.success.xpath"));
        return nextPageSupplier.get();
    }

}
