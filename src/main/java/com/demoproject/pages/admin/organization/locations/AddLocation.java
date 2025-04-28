package com.demoproject.pages.admin.organization.locations;

import com.demoproject.pages.admin.organization.Locations;
import com.demoproject.utils.ActionHelper;
import org.openqa.selenium.By;

public class AddLocation {

    private static final By nameField = By.xpath("//label[text()='Name']/../following-sibling::div//input");
    private static final By phoneNumberField = By.xpath("//label[text()='Phone']/../following-sibling::div//input");
    private static final By faxField = By.xpath("//label[text()='Fax']/../following-sibling::div//input");
    private static final By cityField = By.xpath("//label[text()='City']/../following-sibling::div//input");
    private static final By stateProvinceField= By.xpath("//label[text()='State/Province']/../following-sibling::div//input");
    private static final By zipPostalCodeField = By.xpath("//label[text()='Zip/Postal Code']/../following-sibling::div//input");
    private static final By countryDropDown = By.xpath("//div[@class='oxd-select-wrapper']");
    private static final By notesField = By.xpath("//label[text()='Notes']/../following-sibling::div//textarea");
    private static final By addressField = By.xpath("//label[text()='Address']/../following-sibling::div//textarea");
    private static final By saveBtn = By.xpath("//button[text()=' Save ']");
    private static final By cancelBtn = By.xpath("//button[text()=' Cancel ']");

    public AddLocation fillNameField(String text){
        ActionHelper.type(nameField,text);
        return this;
    }

    public Locations clickSaveButton(){
        ActionHelper.click(saveBtn);
        return new Locations();
    }

    public Locations clickCancelButton(){
        ActionHelper.click(cancelBtn);
        return new Locations();
    }

    public AddLocation fillAddressField(String text){
        ActionHelper.type(addressField,text);
        return new AddLocation();
    }

    public AddLocation fillNotesField(String text){
        ActionHelper.type(notesField,text);
        return new AddLocation();
    }

    public AddLocation fillStateProvinceField(String text){
        ActionHelper.type(stateProvinceField,text);
        return new AddLocation();
    }

    public AddLocation fillZipPostalCodeField(String text){
        ActionHelper.type(zipPostalCodeField,text);
        return new AddLocation();
    }

    public AddLocation fillFaxField(String text){
        ActionHelper.type(faxField,text);
        return new AddLocation();
    }

    public AddLocation fillPhoneNumberField(String text){
        ActionHelper.type(phoneNumberField,text);
        return this;
    }

    public AddLocation fillCityField(String text){
        ActionHelper.type(cityField,text);
        return this;
    }

    public AddLocation selectCountryByText(String country){
        ActionHelper.click(countryDropDown);
        ActionHelper.waitForVisibility(By.xpath("//div[@role='option']"));
        ActionHelper.jsScrollClick(By.xpath("//div[@role='option']//span[text()='"+country+"']"));
        return this;
    }
}
