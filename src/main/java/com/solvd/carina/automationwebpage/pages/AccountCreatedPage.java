package com.solvd.carina.automationwebpage.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AccountCreatedPage extends AbstractPage {

    @FindBy(css = "h2[data-qa='account-created']")
    private ExtendedWebElement accountCreatedMessage;

    @FindBy(css = "a[data-qa='continue-button']")
    private ExtendedWebElement continueButton;

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAccountCreatedMessageVisible(){
        return accountCreatedMessage.isVisible();
    }

    public void clickOnContinueButton(){
        continueButton.click();
    }

}
