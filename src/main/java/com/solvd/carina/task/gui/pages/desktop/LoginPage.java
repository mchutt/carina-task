package com.solvd.carina.task.gui.pages.desktop;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(css = "#auth-error-message-box")
    private ExtendedWebElement errorMessage;

    @FindBy(css = "#ap_email")
    private ExtendedWebElement emailInput;

    @FindBy(css = "#continue")
    private ExtendedWebElement continueButton;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void typeEmail(String email){
        emailInput.type(email);
    }
    public void clickOnContinueButton(){
        continueButton.click();
    }
    public boolean isErrorMessagePresent(){
        return errorMessage.isElementPresent();
    }
}
