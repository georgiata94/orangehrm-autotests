package com.demoproject.navigatorpages.pages.admin.organization;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GeneralInformation {

    private static final Logger logger = LogManager.getLogger(GeneralInformation.class);

    public GeneralInformation clickRadioBtn() {
        logger.info("Clicking on Radio Button.");
        ActionHelper.click(ButtonManager.get("generalInformation.radioBtn.xpath"));
        return new GeneralInformation();
    }

    public GeneralInformation fillOrganizationNameField(String text) {
        logger.info("Filling 'Organization Name' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Organization Name"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillRegistrationNumberField(String text) {
        logger.info("Filling 'Registration Number' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Registration Number"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillPhoneNumberField(String text) {
        logger.info("Filling 'Phone' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Phone"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillTaxIDField(String text) {
        logger.info("Filling 'Tax ID' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Tax ID"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillFaxField(String text) {
        logger.info("Filling 'Fax' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Fax"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillEmailField(String text) {
        logger.info("Filling 'Email' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Email"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillAddressStreet1Field(String text) {
        logger.info("Filling 'Address Street 1' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Address Street 1"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillAddressStreet2Field(String text) {
        logger.info("Filling 'Address Street 2' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "Address Street 2"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillCityField(String text) {
        logger.info("Filling 'City' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "City"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillStateProvinceField(String text) {
        logger.info("Filling 'State/Province' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath", "State/Province"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillZipPostalCodeField(String text) {
        logger.info("Filling 'Zip/Postal Code' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("Create new scratch file from selection"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillNotesField(String text) {
        logger.info("Filling 'Notes' field with text: {}", text);
        ActionHelper.type(ButtonManager.get("generalInformation.notesField.xpath"), text);
        return new GeneralInformation();
    }

    public GeneralInformation clickSaveButton() {
        logger.info("Clicking 'Save' button.");
        ActionHelper.click(ButtonManager.get("common.button.save.xpath"));
        return new GeneralInformation();
    }

    public GeneralInformation selectCountryByText(String country) {
        logger.info("Selecting country from dropdown: {}", country);
        ActionHelper.click(ButtonManager.get("common.dropdown.generic.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.dropdown.option.xpath"));
        ActionHelper.jsScrollClick(ButtonManager.get("common.dropdown.option.xpath", country));
        return this;
    }
}
