package com.solvd.carina.task.gui.pages.desktop;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class LanguageSettingsPage extends AbstractPage {
    @FindBy(xpath = "//div[@id='icp-language-settings']/div[not(@id)]")
    private List<ExtendedWebElement> languageList;

    @FindBy(xpath = "//span[@id='icp-save-button']//input[@type='submit']")
    private ExtendedWebElement saveChangesButton;

    public LanguageSettingsPage(WebDriver driver) {
        super(driver);
    }

    public void selectAndClickARandomLanguageButton(){
        Random r = new Random();
        ExtendedWebElement webElement = languageList.get(r.nextInt(languageList.size()));
        webElement.click();
    }

    public void selectLanguage(String lang){
       ExtendedWebElement languageButton = languageList.stream()
                .filter(element -> Objects.equals(
                        element.getElement().findElement(By.xpath(".//input")).getDomAttribute("value"),
                        lang
                ))
                .findFirst()
                .orElseThrow();

       languageButton.click();
    }
    public void clickOnSaveChanges(){
        saveChangesButton.click();
    }
}
