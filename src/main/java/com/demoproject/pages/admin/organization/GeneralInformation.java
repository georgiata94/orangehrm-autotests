package com.demoproject.pages.admin.organization;

import com.demoproject.utils.ActionHelper;
import org.openqa.selenium.By;

public class GeneralInformation {

    private static final By radioBtn = By.xpath("//input[@type='checkbox']");
    private static final By organizationNameField = By.xpath("//label[text()='Organization Name']/../following-sibling::div//input");
    private static final By registrationNumberField = By.xpath("//label[text()='Registration Number']/../following-sibling::div//input");
    private static final By phoneNumberField = By.xpath("//label[text()='Phone']/../following-sibling::div//input");
    private static final By taxIDField= By.xpath("//label[text()='Tax ID']/../following-sibling::div//input");
    private static final By faxField = By.xpath("//label[text()='Fax']/../following-sibling::div//input");
    private static final By emailField = By.xpath("//label[text()='Email']/../following-sibling::div//input");
    private static final By addressStreet1Field = By.xpath("//label[text()='Address Street 1']/../following-sibling::div//input");
    private static final By addressStreet2Field = By.xpath("//label[text()='Address Street 2']/../following-sibling::div//input");
    private static final By cityField = By.xpath("//label[text()='City']/../following-sibling::div//input");
    private static final By stateProvinceField= By.xpath("//label[text()='State/Province']/../following-sibling::div//input");
    private static final By zipPostalCodeField = By.xpath("//label[text()='Zip/Postal Code']/../following-sibling::div//input");
    private static final By countryDropDown = By.xpath("//div[@class='oxd-select-wrapper']");
    private static final By notesField = By.xpath("//textarea");
    private static final By saveBtn = By.xpath("//button[text()=' Save ']");
    private static final By numberOfEmployees = By.xpath("//div//p[@class='oxd-text oxd-text--p no-of-employees-value']");

    public GeneralInformation clickRadioBtn(){
        ActionHelper.click(radioBtn);
        return new GeneralInformation();
    }

    public GeneralInformation fillOrganizationNameField(String text){
        ActionHelper.type(organizationNameField,text);
        return new GeneralInformation();
    }

    public GeneralInformation fillRegistrationNumberField(String text){
        ActionHelper.type(registrationNumberField,text);
        return new GeneralInformation();
    }

    public GeneralInformation fillPhoneNumberField(String text){
        ActionHelper.type(phoneNumberField,text);
        return new GeneralInformation();
    }

    public GeneralInformation fillTaxIDField(String text){
        ActionHelper.type(taxIDField,text);
        return new GeneralInformation();
    }

    public GeneralInformation fillFaxField(String text){
        ActionHelper.type(faxField,text);
        return new GeneralInformation();
    }

    public GeneralInformation fillEmailField(String text){
        ActionHelper.type(emailField,text);
        return new GeneralInformation();
    }

    public GeneralInformation fillAddressStreet1Field(String text){
        ActionHelper.type(addressStreet1Field,text);
        return new GeneralInformation();
    }

    public GeneralInformation fillAddressStreet2Field(String text){
        ActionHelper.type(addressStreet2Field,text);
        return new GeneralInformation();
    }

    public GeneralInformation fillCityField(String text){
        ActionHelper.type(cityField,text);
        return new GeneralInformation();
    }

    public GeneralInformation fillStateProvinceField(String text){
        ActionHelper.type(stateProvinceField,text);
        return new GeneralInformation();
    }

    public GeneralInformation fillZipPostalCodeField(String text){
        ActionHelper.type(zipPostalCodeField,text);
        return new GeneralInformation();
    }

    public GeneralInformation fillNotesField(String text){
        ActionHelper.type(notesField,text);
        return new GeneralInformation();
    }

    public GeneralInformation clickSaveButton(){
        ActionHelper.click(saveBtn);
        return new GeneralInformation();
    }

    public GeneralInformation selectCountryByText(String country){
        ActionHelper.click(countryDropDown);
        ActionHelper.waitForVisibility(By.xpath("//div[@role='option']"));
        ActionHelper.jsScrollClick(By.xpath("//div[@role='option']//span[text()='"+country+"']"));
        return this;
    }
}
