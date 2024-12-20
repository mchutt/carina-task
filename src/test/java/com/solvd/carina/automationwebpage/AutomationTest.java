package com.solvd.carina.automationwebpage;

import com.solvd.carina.automationwebpage.components.LoginFormComponent;
import com.solvd.carina.automationwebpage.components.ProductCardComponent;
import com.solvd.carina.automationwebpage.components.ProductInCartComponent;
import com.solvd.carina.automationwebpage.components.SignUpFormComponent;
import com.solvd.carina.automationwebpage.pages.*;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.solvd.carina.automationwebpage.constants.UserConstants.*;

public class AutomationTest extends com.zebrunner.carina.core.AbstractTest {





    @Test
    public void searchAProductTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");


        ProductsPage productsPage = homePage.getHeader().openProductsPage();
        boolean isTitleDisplayed = productsPage.isAllProductsTitleDisplayed();
        Assert.assertTrue(isTitleDisplayed, "The 'All Products' title is not displayed on the Products page.");


        String searchQ = "top";
        productsPage.typeTextInSearchInput(searchQ);
        productsPage.clickOnSubmitSearchButton();
        boolean searchedProductsTitleDisplayed = productsPage.isSearchedProductsTitleDisplayed();
        Assert.assertTrue(searchedProductsTitleDisplayed, "The 'Searched Products' title is not displayed on the Products page.");


        List<ProductCardComponent> products = productsPage.getProducts();
        int productNameContainsSearchedTextCount = 0;
        for (ProductCardComponent product : products) {
            if (StringUtils.containsIgnoreCase(product.getProductName(), searchQ)) {
                productNameContainsSearchedTextCount++;
            } else productNameContainsSearchedTextCount--;
        }
        Assert.assertTrue(productNameContainsSearchedTextCount > 0, "The product list do not contain any products which name matches with the searched text: " + searchQ);
    }

    @Test
    public void registerUserWithAnExistingEmailTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");


        SignUpFormComponent form = homePage.getHeader().openLoginPage().getSignUpForm();
        form.signUp("Pepe", "pepe@pepe.com");

        boolean isErrorMessageVisible = form.isErrorMessageVisible();
        Assert.assertTrue(isErrorMessageVisible, "The error message for an existing email is not displayed. ");
    }

    @Test
    public void loginWithAnIncorrectEmailAndPasswordTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");


        LoginFormComponent loginForm = homePage.getHeader().openLoginPage().getLoginForm();
        loginForm.login("pepe@pepe.com", "Incorrect Pass");

        boolean isErrorMessageVisible = loginForm.isErrorMessageVisible();
        Assert.assertTrue(isErrorMessageVisible, "The error message for incorrect login credentials is not displayed. ");
    }

    @Test
    public void addProductToTheCartTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");


        ProductsPage productsPage = homePage.getHeader().openProductsPage();
        ProductCardComponent productCardComponent = productsPage.getProducts().get(0);
        ProductDetailsPage productDetailsPage = productCardComponent.openProductDetails();

        CartPage cartPage = productDetailsPage.clickOnAddToCartButton().openCartPage();


        Assert.assertFalse(cartPage.getAllProducts().isEmpty(), "The cart is empty. ");

        cartPage.getAllProducts()
                .forEach(ProductInCartComponent::clickOnDeleteProductButton);
        Assert.assertTrue(cartPage.isEmptyCartMessageVisible(), "The cart is NOT empty. ");
    }

    @Test
    public void checkoutTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");


        //Login
        LoginFormComponent loginForm = homePage.getHeader().openLoginPage().getLoginForm();
        loginForm.login(USER_EMAIL, USER_PASSWORD);

        boolean isloggedMessageDisplayed = homePage.getHeader().isLoggedMessagePresent();
        Assert.assertTrue(isloggedMessageDisplayed, "The 'logged in user' message is not displayed after login. ");

        //Add product to cart
        ProductsPage productsPage = homePage.getHeader().openProductsPage();
        ProductCardComponent productCardComponent = productsPage.getProducts().get(0);
        ProductDetailsPage productDetailsPage = productCardComponent.openProductDetails();
        CartPage cartPage = productDetailsPage.clickOnAddToCartButton().openCartPage();

        Assert.assertFalse(cartPage.getAllProducts().isEmpty(), "The cart is empty after adding a product. ");

        CheckoutPage checkoutPage = cartPage.clickOnCheckoutButton();
        PaymentPage paymentPage = checkoutPage.clickOnPlaceOrderButton();

        paymentPage.typeInNameOnCardInput("Mateo");
        paymentPage.typeInCardNumberInput("123424234");
        paymentPage.typeInCVCInput("234");
        paymentPage.typeInExpirationMonthInput("03");
        paymentPage.typeInExpirationYearInput("2030");
        PaymentDonePage paymentDonePage = paymentPage.clickOnConfirmOrderButton();

        boolean confirmedOrder = paymentDonePage.isConfirmedOrderMessageDisplayed();
        Assert.assertTrue(confirmedOrder, "The order confirmation message is not displayed. ");

    }

    @Test
    @MethodOwner(owner = "mchutt")
    public void createAccountAndRemoveAccountTest(){
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");

        SignUpFormComponent form = homePage.getHeader().openLoginPage().getSignUpForm();
        form.signUp(NEW_USER_NAME, NEW_USER_EMAIL);

        FullSignUpPage fullSignUpPage = form.openFullSignUpPage();
        fullSignUpPage.typeFirstName(NEW_USER_NAME);
        fullSignUpPage.typeLastName(NEW_USER_LAST_NAME);
        fullSignUpPage.typePassword(NEW_USER_PASS);
        fullSignUpPage.typeAddress(NEW_USER_ADDRESS);
        fullSignUpPage.selectCountry(NEW_USER_COUNTRY);
        fullSignUpPage.typeState(NEW_USER_STATE);
        fullSignUpPage.typeCity(NEW_USER_CITY);
        fullSignUpPage.typeZipCode(NEW_USER_ZIP_CODE);
        fullSignUpPage.typeMobilePhone(NEW_USER_MOBILE_NUMBER);

        AccountCreatedPage accountCreatedPage = fullSignUpPage.clickOnSubmitButton();
        Assert.assertTrue(accountCreatedPage.isAccountCreatedMessageVisible(), "The message 'Account Created!' is not visible!");
        accountCreatedPage.clickOnContinueButton();

        AccountDeletedPage accountDeletedPage = homePage.getHeader().deleteAccount();
        Assert.assertTrue(accountDeletedPage.isAccountDeletedMessageVisible(), "The message 'Account Deleted!' is not visible");
        accountDeletedPage.clickOnContinueButton();

        boolean isLoggedMessagePresent = homePage.getHeader().isLoggedMessagePresent();
        Assert.assertFalse(isLoggedMessagePresent, "The logged message should not be present");

    }
}
