package com.solvd.carina.task;

import com.solvd.carina.task.gui.components.ProductCardComponent;
import com.solvd.carina.task.gui.pages.desktop.*;
import com.zebrunner.carina.core.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonWebsiteTest extends AbstractTest {
    @Test
    public void validateSearchingForAProduct(){
        AmazonHomePage page = new AmazonHomePage(getDriver());
        page.openURL("https://www.amazon.com/");

//        pause(10000L);// to avoid captcha
        page.typeOnSearchInput("iPhone");
        SearchPage searchPage = page.pressEnter();

        Assert.assertFalse(searchPage.isProductListEmpty(), "Product list is empty");
    }
    @Test
    public void validateChangingWebsiteLanguage(){
        AmazonHomePage page = new AmazonHomePage(getDriver());
        page.open();

        //pause(10000L);// to avoid captcha
        ChangeLanguagePage changeLanguagePage = page.getHeaderComponent().clickOnChangeLanguageButton();
        changeLanguagePage.selectLanguage("es_US");
        changeLanguagePage.clickOnSaveChanges();

        String expectedSentence = "Elige un idioma para hacer compras. La selección actual es Español. ";
        boolean isPresent = page.getHeaderComponent().isSentencePresent(expectedSentence);

        Assert.assertTrue(isPresent, "The language of the website is not as expected. Expected sentence is not present. ");
    }
    @Test
    public void validateAddingAProductToTheCart(){
        AmazonHomePage page = new AmazonHomePage(getDriver());
        page.open();

        page.typeOnSearchInput("iphone 14 pro max");
        SearchPage searchPage = page.pressEnter();
        ProductCardComponent product = searchPage.getAProduct(0);
        product.clickOnAddToCartButton();

        CartPage cartPage = page.getHeaderComponent().clickOnCartButton();

        Assert.assertFalse(cartPage.isCartEmpty(), "Cart is Empty");
    }

    @Test
    public void validateThatANonRegisteredUserIsInvalid(){
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
        SearchPage searchPage = page.pressEnter();
        searchPage.clickOnSortingButton();
        searchPage.clickOnSortByPriceAsc();

        Assert.assertTrue(searchPage.isProductListSortedByPrice(), "Products are no sorted by price");
    }

}
