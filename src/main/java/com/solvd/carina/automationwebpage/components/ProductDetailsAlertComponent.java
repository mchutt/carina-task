package com.solvd.carina.automationwebpage.components;

import com.solvd.carina.automationwebpage.pages.CartPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsAlertComponent extends AbstractUIObject {

    @FindBy(xpath = ".//u[text()='View Cart']")
    private ExtendedWebElement viewCartButton;

    public ProductDetailsAlertComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public CartPage openCartPage() {
        viewCartButton.isElementPresent();
        viewCartButton.scrollTo();
        viewCartButton.click();
        return new CartPage(getDriver());
    }
}
