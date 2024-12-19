package com.solvd.carina.automationwebpage.pages;

import com.solvd.carina.automationwebpage.components.LoginFormComponent;
import com.solvd.carina.automationwebpage.components.SignUpFormComponent;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(css = ".signup-form")
    private SignUpFormComponent signUpForm;

    @FindBy(css = ".login-form")
    private LoginFormComponent loginForm;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public SignUpFormComponent getSignUpForm() {
        return signUpForm;
    }

    public LoginFormComponent getLoginForm() {
        return loginForm;
    }
}
