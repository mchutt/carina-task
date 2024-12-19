package com.solvd.carina.automationwebpage.pages;

import com.solvd.carina.automationwebpage.components.ProductInCartComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractPage {

    @FindBy(xpath = "//tbody//tr")
    private List<ProductInCartComponent> cartProductList;

    @FindBy(xpath = "//a[text()='Proceed To Checkout']")
    private ExtendedWebElement proceedToCheckoutButton;

    @FindBy(css = "#empty_cart")
    private ExtendedWebElement emptyCartMessage;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductInCartComponent> getAllProducts() {
        return cartProductList;
    }

    public CheckoutPage clickOnCheckoutButton() {
        proceedToCheckoutButton.click();
        return new CheckoutPage(driver);
    }

    public boolean isEmptyCartMessageVisible(){
        return emptyCartMessage.isVisible();
    }

}
