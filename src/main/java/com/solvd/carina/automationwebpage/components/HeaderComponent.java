package com.solvd.carina.automationwebpage.components;


import com.solvd.carina.automationwebpage.constants.LinkNames;
import com.solvd.carina.automationwebpage.pages.HomePage;
import com.solvd.carina.automationwebpage.pages.LoginPage;
import com.solvd.carina.automationwebpage.pages.ProductsPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;

public class HeaderComponent extends AbstractUIObject {

    @FindBy(xpath = ".//li/a")
    private List<ExtendedWebElement> linkList;

    @FindBy(xpath = ".//li/a[text()=' Logged in as ']")
    private ExtendedWebElement loggedMessage;

    public HeaderComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    private void clickOnLink(LinkNames linkName) {
        linkList.stream()
                .filter(link -> Objects.equals(
                        link.getAttribute("href"),
                        linkName.getHref()))
                .findFirst().orElseThrow().click();
    }

    public ProductsPage openProductsPage() {
        clickOnLink(LinkNames.PRODUCTS);
        return new ProductsPage(driver);
    }

    public HomePage openHomePage() {
        clickOnLink(LinkNames.HOME);
        return new HomePage(driver);
    }

    public LoginPage openLoginPage() {
        clickOnLink(LinkNames.LOGIN);
        return new LoginPage(driver);
    }

    public boolean isLoggedMessageDisplayed() {
        return loggedMessage.isElementPresent();
    }

}
