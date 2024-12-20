package com.solvd.carina.automationwebpage.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class FullSignUpPage extends AbstractPage {


    @FindBy(css = "#password")
    private ExtendedWebElement passwordInput;

    @FindBy(css = "#first_name")
    private ExtendedWebElement firstNameInput;

    @FindBy(css = "#last_name")
    private ExtendedWebElement lastNameInput;

    @FindBy(css = "#address1")
    private ExtendedWebElement addressOneInput;

    @FindBy(css = "#country")
    private ExtendedWebElement countrySelect;

    @FindBy(css = "#state")
    private ExtendedWebElement stateInput;

    @FindBy(css = "#city")
    private ExtendedWebElement cityInput;

    @FindBy(css = "#zipcode")
    private ExtendedWebElement zipcodeInput;

    @FindBy(css = "#mobile_number")
    private ExtendedWebElement mobileNumberInput;

    @FindBy(css = "button[type='submit']")
    private ExtendedWebElement submitButton;

    public FullSignUpPage(WebDriver driver) {
        super(driver);
    }

    public void typePassword(String pass) {
        passwordInput.type(pass);
    }

    public void typeFirstName(String firstName) {
        firstNameInput.type(firstName);
    }

    public void typeLastName(String lastName) {
        lastNameInput.type(lastName);
    }

    public void typeAddress(String address) {
        addressOneInput.type(address);
    }

    public void selectCountry(String country) {
        countrySelect.scrollTo();
        countrySelect.select(country);
    }

    public void typeState(String state) {
        stateInput.type(state);
    }

    public void typeCity(String city) {
        cityInput.type(city);
    }

    public void typeZipCode(String zipCode) {
        zipcodeInput.type(zipCode);
    }

    public void typeMobilePhone(String mobileNumber) {
        mobileNumberInput.type(mobileNumber);
    }

    public AccountCreatedPage clickOnSubmitButton(){
        submitButton.click();
        return new AccountCreatedPage(driver);
    }
}
