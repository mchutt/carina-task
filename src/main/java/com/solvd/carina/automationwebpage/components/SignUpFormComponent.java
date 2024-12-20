package com.solvd.carina.automationwebpage.components;

import com.solvd.carina.automationwebpage.pages.FullSignUpPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SignUpFormComponent extends AbstractUIObject {

    @FindBy(xpath = ".//input[@name='name']")
    private ExtendedWebElement nameInput;

    @FindBy(xpath = ".//input[@data-qa='signup-email']")
    private ExtendedWebElement emailInput;

    @FindBy(xpath = ".//button[text()='Signup']")
    private ExtendedWebElement signUpButton;

    @FindBy(xpath = "//p[text()='Email Address already exist!']")
    private ExtendedWebElement emailAddressAlreadyExistErrorMessage;

    @FindBy(xpath = "//h2[text()='New User Signup!']")
    private ExtendedWebElement newUserSignupMessage;

    public SignUpFormComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void signUp(String name, String email) {
        typeInNameInput(name);
        typeInEmailInput(email);
        clickOnSignUpButton();
    }

    public FullSignUpPage openFullSignUpPage() {
        return new FullSignUpPage(driver);
    }

    public void typeInNameInput(String name) {
        nameInput.type(name);
    }

    public void typeInEmailInput(String email) {
        emailInput.type(email);
    }

    public void clickOnSignUpButton() {
        signUpButton.click();
    }

    public boolean isErrorMessageVisible() {
        return emailAddressAlreadyExistErrorMessage.isVisible();
    }

    public boolean isNewUserSignupMessageVisible() {
        return newUserSignupMessage.isVisible();
    }
}
