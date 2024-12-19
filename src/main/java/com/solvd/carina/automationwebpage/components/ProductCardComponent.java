package com.solvd.carina.automationwebpage.components;

import com.solvd.carina.automationwebpage.pages.ProductDetailsPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductCardComponent extends AbstractUIObject {

    @FindBy(xpath = ".//a[text()='View Product']")
    private ExtendedWebElement viewProductDetailsButton;

    @FindBy(css = "p")
    private ExtendedWebElement productName;

    @FindBy(xpath = ".//a[text()='Add to cart']")
    private ExtendedWebElement addToCartButton;

    public ProductCardComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ProductDetailsPage openProductDetails() {
        viewProductDetailsButton.scrollTo();
        viewProductDetailsButton.click();
        return new ProductDetailsPage(getDriver());
    }

    public String getProductName() {
        return productName.getText();
    }

}
