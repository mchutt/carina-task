package com.solvd.carina.automationwebpage.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AccountDeletedPage extends AbstractPage {

    @FindBy(css = "h2[data-qa='account-deleted']")
    private ExtendedWebElement accountDeletedMessage;

    @FindBy(css = "a[data-qa='continue-button']")
    private ExtendedWebElement continueButton;

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountDeletedMessageVisible() {
        return accountDeletedMessage.isVisible();
    }

    public void clickOnContinueButton() {
        continueButton.click();
    }
}
