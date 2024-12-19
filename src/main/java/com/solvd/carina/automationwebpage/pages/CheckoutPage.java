package com.solvd.carina.automationwebpage.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends AbstractPage {

    @FindBy(xpath = "//a[text()='Place Order']")
    private ExtendedWebElement placeOrderButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public PaymentPage clickOnPlaceOrderButton() {
        placeOrderButton.scrollTo();
        placeOrderButton.click();
        return new PaymentPage(driver);
    }
}
