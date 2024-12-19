package com.solvd.carina.automationwebpage.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends AbstractPage {

    @FindBy(xpath = "//input[@name='name_on_card']")
    private ExtendedWebElement nameOnCardInput;

    @FindBy(xpath = "//input[@name='card_number']")
    private ExtendedWebElement cardNumberInput;

    @FindBy(xpath = "//input[@name='cvc']")
    private ExtendedWebElement cvcInput;

    @FindBy(xpath = "//input[@name='expiry_month']")
    private ExtendedWebElement expirationMonthInput;

    @FindBy(xpath = "//input[@name='expiry_year']")
    private ExtendedWebElement expirationYearInput;

    @FindBy(css = "#submit")
    private ExtendedWebElement confirmOrderbutton;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public void typeInNameOnCardInput(String text) {
        nameOnCardInput.type(text);
    }

    public void typeInCardNumberInput(String text) {
        cardNumberInput.type(text);
    }

    public void typeInCVCInput(String text) {
        cvcInput.type(text);
    }

    public void typeInExpirationMonthInput(String text) {
        expirationMonthInput.type(text);
    }

    public void typeInExpirationYearInput(String text) {
        expirationYearInput.type(text);
    }

    public PaymentDonePage clickOnConfirmOrderButton() {
        confirmOrderbutton.scrollTo();
        confirmOrderbutton.click();
        return new PaymentDonePage(driver);
    }

}
