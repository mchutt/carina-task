package com.solvd.carina.task.gui.pages.desktop;

import com.solvd.carina.task.gui.components.HeaderComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AmazonHomePage extends AbstractPage {

    @FindBy(css = "#nav-belt")
    private HeaderComponent headerElement;

    @FindBy(css = "#twotabsearchtextbox")
    private ExtendedWebElement searchInput;

    public AmazonHomePage(WebDriver driver) {
        super(driver);
    }

    public void typeOnSearchInput(String text){
        searchInput.type(text);
    }
    public ProductListPage pressEnter(){
        searchInput.sendKeys(Keys.ENTER);
        return new ProductListPage(driver);
    }

    public HeaderComponent getHeaderComponent() {
        return headerElement;
    }
}
