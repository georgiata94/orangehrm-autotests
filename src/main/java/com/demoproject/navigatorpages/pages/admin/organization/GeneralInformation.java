package com.demoproject.navigatorpages.pages.admin.organization;

import com.demoproject.utils.ActionHelper;
import com.demoproject.utils.ButtonManager;

public class GeneralInformation {

    public GeneralInformation clickRadioBtn() {
        ActionHelper.click(ButtonManager.get("generalInformation.radioBtn.xpath"));
        return new GeneralInformation();
    }

    public GeneralInformation fillOrganizationNameField(String text) {
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Organization Name"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillRegistrationNumberField(String text) {
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Registration Number"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillPhoneNumberField(String text) {
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Phone"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillTaxIDField(String text) {
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Tax ID"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillFaxField(String text) {
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Fax"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillEmailField(String text) {
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Email"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillAddressStreet1Field(String text) {
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Address Street 1"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillAddressStreet2Field(String text) {
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","Address Street 2"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillCityField(String text) {
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","City"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillStateProvinceField(String text) {
        ActionHelper.type(ButtonManager.get("common.input.generic.xpath","State/Province"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillZipPostalCodeField(String text) {
        ActionHelper.type(ButtonManager.get("Create new scratch file from selection"), text);
        return new GeneralInformation();
    }

    public GeneralInformation fillNotesField(String text) {
        ActionHelper.type(ButtonManager.get("generalInformation.notesField.xpath"), text);
        return new GeneralInformation();
    }

    public GeneralInformation clickSaveButton() {
        ActionHelper.click(ButtonManager.get("common.button.save.xpath"));
        return new GeneralInformation();
    }

    public GeneralInformation selectCountryByText(String country) {
        ActionHelper.click(ButtonManager.get("common.dropdown.generic.xpath"));
        ActionHelper.waitForVisibility(ButtonManager.get("common.dropdown.option.xpath"));
        ActionHelper.jsScrollClick(ButtonManager.get("common.dropdown.option.xpath", country));
        return this;
    }
}
