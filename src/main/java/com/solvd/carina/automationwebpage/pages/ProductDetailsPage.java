package com.solvd.carina.automationwebpage.pages;

import com.solvd.carina.automationwebpage.components.ProductDetailsAlertComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends AbstractPage {


    @FindBy(xpath = "//button[@type='button']")
    private ExtendedWebElement addToCartButton;

    @FindBy(className = "modal-content")
    private ProductDetailsAlertComponent modal;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailsAlertComponent clickOnAddToCartButton() {
        addToCartButton.click();
        return modal;
    }

}
