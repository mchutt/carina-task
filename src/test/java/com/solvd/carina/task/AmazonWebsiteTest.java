package com.solvd.carina.task;

import com.solvd.carina.task.gui.components.ProductCardComponent;
import com.solvd.carina.task.gui.pages.desktop.*;
import com.zebrunner.carina.core.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonWebsiteTest extends AbstractTest {
    @Test
    public void validateSearchingForAProduct() {
        AmazonHomePage page = new AmazonHomePage(getDriver());
        page.openURL("https://www.amazon.com/");

//        pause(10000L);// to avoid captcha
        page.typeOnSearchInput("iPhone");
        ProductListPage productListPage = page.pressEnter();

        Assert.assertFalse(productListPage.isProductListEmpty(), "Product list is empty");
    }

    @Test
    public void validateChangingWebsiteLanguage() {
        AmazonHomePage page = new AmazonHomePage(getDriver());
        page.open();

        //pause(10000L);// to avoid captcha
        LanguageSettingsPage languageSettingsPage = page.getHeaderComponent().clickOnChangeLanguageButton();
        languageSettingsPage.selectLanguage("es_US");
        languageSettingsPage.clickOnSaveChanges();

        String expectedSentence = "Elige un idioma para hacer compras. La selección actual es Español. ";
        boolean isPresent = page.getHeaderComponent().isSentencePresent(expectedSentence);

        Assert.assertTrue(isPresent, "The language of the website is not as expected. Expected sentence is not present. ");
    }

    @Test
    public void validateAddingAProductToTheCart() {
        AmazonHomePage page = new AmazonHomePage(getDriver());
        page.open();

        page.typeOnSearchInput("iphone 14 pro max");
        ProductListPage productListPage = page.pressEnter();
        ProductCardComponent product = productListPage.getAProduct(0);
        product.clickOnAddToCartButton();

        CartPage cartPage = page.getHeaderComponent().clickOnCartButton();

        Assert.assertFalse(cartPage.isCartEmpty(), "Cart is Empty");
    }

    @Test
    public void validateThatANonRegisteredUserIsInvalid() {
        AmazonHomePage page = new AmazonHomePage(getDriver());
        page.open();

        //pause(10000L);// to avoid captcha
        LoginPage loginPage = page.getHeaderComponent().clickOnLoginButton();
        loginPage.typeEmail("sadfsadf324234sdf@sdfsdf.sddf");
        loginPage.clickOnContinueButton();

        Assert.assertTrue(loginPage.isErrorMessagePresent(), "Error message is not present");
    }

    @Test
    public void validateSortingProductsByPrice() {
        AmazonHomePage page = new AmazonHomePage(getDriver());
        page.open();

        //pause(10000L); // to avoid captcha
        page.typeOnSearchInput("iPhone");
        ProductListPage productListPage = page.pressEnter();
        productListPage.clickOnSortingButton();
        productListPage.clickOnSortByPriceAsc();

        Assert.assertTrue(productListPage.isProductListSortedByPrice(), "Products are no sorted by price");
    }

}
