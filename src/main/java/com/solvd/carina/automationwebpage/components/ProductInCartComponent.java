package com.solvd.carina.automationwebpage.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductInCartComponent extends AbstractUIObject {

    @FindBy(xpath = ".//h4")
    private ExtendedWebElement productName;

    @FindBy(css = ".cart_quantity_delete")
    private ExtendedWebElement deleteProduct;

    public ProductInCartComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getName() {
        return productName.getText();
    }

    public void clickOnDeleteProductButton(){
        deleteProduct.click();
    }

}
