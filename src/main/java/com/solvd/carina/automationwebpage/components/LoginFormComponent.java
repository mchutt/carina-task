package com.solvd.carina.automationwebpage.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    public boolean isErrorMessageVisible() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(emailOrPasswordIncorrectMessage.getElement()));

        return emailOrPasswordIncorrectMessage.isVisible();
    }
}
