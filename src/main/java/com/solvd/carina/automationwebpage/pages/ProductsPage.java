package com.solvd.carina.automationwebpage.pages;

import com.solvd.carina.automationwebpage.components.ProductCardComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends AbstractPage {

    @FindBy(css = ".features_items .col-sm-4")
    private List<ProductCardComponent> productList;

    @FindBy(css = "#search_product")
    private ExtendedWebElement searchInput;

    @FindBy(css = "#submit_search")
    private ExtendedWebElement submitSearchButton;

    @FindBy(xpath = "//h2[text()='All Products']")
    private ExtendedWebElement allProductsTitle;

    @FindBy(xpath = "//h2[text()='Searched Products']")
    private ExtendedWebElement searchedProductsTitle;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void typeTextInSearchInput(String text) {
        searchInput.type(text);
    }

    public void clickOnSubmitSearchButton() {
        submitSearchButton.click();
    }

    public boolean isAllProductsTitleDisplayed() {
        return allProductsTitle.isElementPresent();
    }

    public boolean isSearchedProductsTitleDisplayed() {
        return searchedProductsTitle.isElementPresent();
    }

    public List<ProductCardComponent> getProducts() {
        return productList;
    }

}
