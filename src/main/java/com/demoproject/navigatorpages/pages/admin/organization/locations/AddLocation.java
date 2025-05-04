package com.demoproject.navigatorpages.pages.admin.organization.locations;

import com.demoproject.navigatorpages.pages.admin.organization.Locations;
import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddLocation {

    private static final Logger logger = LogManager.getLogger(AddLocation.class);

    public AddLocation fillNameField(String text) {
        logger.info("Filling 'Name' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Name"), text);
        return this;
    }

    public Locations clickSaveButton() {
        logger.info("Clicking 'Save' button.");
        ActionHelper.click(ButtonManager.get("common.button.save.xpath"));
        return new Locations();
    }

    public Locations clickCancelButton() {
        logger.info("Clicking 'Cancel' button.");
        ActionHelper.click(ButtonManager.get("common.button.cancel.xpath"));
        return new Locations();
    }

    public AddLocation fillAddressField(String text) {
        logger.info("Filling 'Address' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("addLocation.addressField.xpath"), text);
        return this;
    }

    public AddLocation fillNotesField(String text) {
        logger.info("Filling 'Notes' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Notes"), text);
        return this;
    }

    public AddLocation fillStateProvinceField(String text) {
        logger.info("Filling 'State/Province' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "State/Province"), text);
        return this;
    }

    public AddLocation fillZipPostalCodeField(String text) {
        logger.info("Filling 'Zip/Postal Code' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Zip/Postal Code"), text);
        return this;
    }

    public AddLocation fillFaxField(String text) {
        logger.info("Filling 'Fax' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Fax"), text);
        return this;
    }

    public AddLocation fillPhoneNumberField(String text) {
        logger.info("Filling 'Phone' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Phone"), text);
        return this;
    }

    public AddLocation fillCityField(String text) {
        logger.info("Filling 'City' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "City"), text);
        return this;
    }

    public AddLocation selectCountryByText(String country) {
        logger.info("Selecting country from dropdown: {}", country);
        ActionHelper.click(ButtonManager.get("common.dropdown.generic.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.dropdown.option.xpath"));
        ActionHelper.jsScrollClick(ButtonManager.get("common.dropdown.option.xpath", country));
        return this;
    }
}
