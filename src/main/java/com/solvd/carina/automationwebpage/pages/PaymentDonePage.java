package com.solvd.carina.automationwebpage.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PaymentDonePage extends AbstractPage {

    @FindBy(xpath = "//p[text()='Congratulations! Your order has been confirmed!']")
    private ExtendedWebElement confirmedOrderMessage;

    public PaymentDonePage(WebDriver driver) {
        super(driver);
    }

    public boolean isConfirmedOrderMessageDisplayed() {
        return confirmedOrderMessage.isElementPresent();
    }
}
