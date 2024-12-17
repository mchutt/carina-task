package com.solvd.carina.task.gui.pages.desktop;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractPage {
    @FindBy(xpath = "//div[@data-csa-c-type='item']")
    private List<ExtendedWebElement> cartItemList;

    public CartPage(WebDriver driver) {
        super(driver);
    }
    public boolean isCartEmpty(){
        return cartItemList.isEmpty();
    }
}
