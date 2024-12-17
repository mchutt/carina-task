package com.solvd.carina.task.gui.components;

import com.solvd.carina.task.gui.pages.desktop.ProductDetailsPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ProductCardComponent extends AbstractUIObject {

    protected static Logger logger = LoggerFactory.getLogger(ProductCardComponent.class);

    @FindBy(xpath = ".//a")
    private ExtendedWebElement productDetailsButton;
    @FindBy(xpath = ".//span[@class='a-color-base']")
    private ExtendedWebElement price; //US$144.95 format
    @FindBy(xpath = ".//button")
    private ExtendedWebElement addToCartButton;


    public ProductCardComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ProductDetailsPage clickOnProductDetailsButton(){
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(productDetailsButton.getElement()));
        productDetailsButton.click();
        return new ProductDetailsPage(getDriver());
    }

    public void clickOnAddToCartButton(){
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton.getElement()));
        addToCartButton.isElementPresent();
        addToCartButton.click();
        wait.until(driver -> addToCartButton.isClickable());
    }

    public double getClearPrice(){
        double clearPrice=-1.0;
        try {
            clearPrice = Double.parseDouble(price.getText().substring(1));
        }catch (NoSuchElementException ex){
            logger.warn("Price not found for this product card");
        }catch ( NumberFormatException ex){
            logger.warn("Something went wrong while formating price {}", ex.getMessage());
        }
        return clearPrice;

    }

}
