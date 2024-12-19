package com.solvd.carina.automationwebpage.pages;

import com.solvd.carina.automationwebpage.components.HeaderComponent;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(css = "#header")
    private HeaderComponent header;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HeaderComponent getHeader() {
        return header;
    }
}
