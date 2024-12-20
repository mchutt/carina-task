package com.solvd.carina.automationwebpage.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginFormComponent extends AbstractUIObject {

    @FindBy(xpath = ".//input[@data-qa='login-email']")
    private ExtendedWebElement emailInput;

    @FindBy(xpath = ".//input[@name='password']")
    private ExtendedWebElement passwordInput;

    @FindBy(xpath = ".//button[@data-qa='login-button']")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = ".//p[text()='Your email or password is incorrect!']")
    private ExtendedWebElement emailOrPasswordIncorrectMessage;

    public LoginFormComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void typeInEmailInput(String email) {
        emailInput.type(email);
    }

    public void typeInPasswordInput(String pass) {
        passwordInput.type(pass);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    public void login(String email, String pass){
        typeInEmailInput(email);
        typeInPasswordInput(pass);
        clickOnLoginButton();
    }

    public boolean isErrorMessageVisible() {
        return emailOrPasswordIncorrectMessage.isVisible();
    }
}
