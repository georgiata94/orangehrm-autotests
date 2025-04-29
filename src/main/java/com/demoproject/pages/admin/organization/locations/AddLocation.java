package com.demoproject.pages.admin.organization.locations;

import com.demoproject.pages.admin.organization.Locations;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.openqa.selenium.By;

public class AddLocation {

    public AddLocation fillNameField(String text){
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath:Name"), text);
        return this;
    }

    public Locations clickSaveButton(){
        ActionHelper.click(ButtonManager.get("common.button.save.xpath"));
        return new Locations();
    }

    public Locations clickCancelButton(){
        ActionHelper.click(ButtonManager.get("common.button.cancel.xpath"));
        return new Locations();
    }

    public AddLocation fillAddressField(String text){
        ActionHelper.type(ButtonManager.get("addLocation.addressField.xpath"), text);
        return this;
    }

    public AddLocation fillNotesField(String text){
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath:Notes"), text);
        return this;
    }

    public AddLocation fillStateProvinceField(String text){
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath:State/Province"), text);
        return this;
    }

    public AddLocation fillZipPostalCodeField(String text){
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath:Zip/Postal Code"), text);
        return this;
    }

    public AddLocation fillFaxField(String text){
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath:Fax"), text);
        return this;
    }

    public AddLocation fillPhoneNumberField(String text){
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath:Phone"), text);
        return this;
    }

    public AddLocation fillCityField(String text){
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath:City"), text);
        return this;
    }

    public AddLocation selectCountryByText(String country){
        ActionHelper.click(ButtonManager.get("common.dropdown.generic.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.dropdown.option.xpath"));
        ActionHelper.jsScrollClick(ButtonManager.get("common.dropdown.option.xpath", country));
        return this;
    }
}
