package com.solvd.carina.task.gui.components;

import com.solvd.carina.task.gui.pages.desktop.CartPage;
import com.solvd.carina.task.gui.pages.desktop.ChangeLanguagePage;
import com.solvd.carina.task.gui.pages.desktop.LoginPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class HeaderComponent extends AbstractUIObject {

    private static final Logger logger = LoggerFactory.getLogger(HeaderComponent.class);

    @FindBy(css = "#icp-nav-flyout")
    private ExtendedWebElement changeLanguageButton;

    @FindBy(css = "#nav-cart")
    private ExtendedWebElement cartButton;

    @FindBy(css = "#nav-link-accountList")
    private ExtendedWebElement loginButton;


    public HeaderComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ChangeLanguagePage clickOnChangeLanguageButton(){
        changeLanguageButton.click();
        return new ChangeLanguagePage(getDriver());
    }
    public CartPage clickOnCartButton(){
        cartButton.click();
        return new CartPage(getDriver());
    }
    public LoginPage clickOnLoginButton(){
        loginButton.click();
        return new LoginPage(getDriver());
    }
    public boolean isSentencePresent(String sentence){
        logger.debug("Checking that sentence '{}' is present", sentence);
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.attributeToBe(changeLanguageButton.getElement(), "aria-label", sentence));
            logger.debug("Sentence '{}' is present in attribute", sentence);
            return true;
        }catch (Exception e){
         logger.error("Error while checking the sentence existence '{}'", sentence);
         return false;
        }
    }
}
