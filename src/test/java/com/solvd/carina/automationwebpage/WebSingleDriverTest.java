package com.solvd.carina.automationwebpage;

import com.solvd.carina.automationwebpage.components.LoginFormComponent;
import com.solvd.carina.automationwebpage.components.ProductCardComponent;
import com.solvd.carina.automationwebpage.components.SignUpFormComponent;
import com.solvd.carina.automationwebpage.pages.*;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.solvd.carina.automationwebpage.constants.UserConstants.*;

public class WebSingleDriverTest extends AbstractTest {

    private HomePage homePage;
    ProductsPage productsPage;

    @BeforeClass
    public void startDriver() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void testOpenPage() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened!");
    }

    @Test(dependsOnMethods = "testOpenPage")
    @MethodOwner(owner = "mchutt")
    public void loginWithAnIncorrectEmailAndPasswordTest() {

        LoginFormComponent loginForm = homePage.getHeader().openLoginPage().getLoginForm();
        boolean loginToYourAccountMessageVisible = loginForm.isLoginToYourAccountMessageVisible();
        Assert.assertTrue(loginToYourAccountMessageVisible, "The 'Login to your account' message is not visible! ");
        loginForm.login("pepe@pepe.com", "Incorrect Pass");

        boolean isErrorMessageVisible = loginForm.isErrorMessageVisible();
        Assert.assertTrue(isErrorMessageVisible, "The error message for incorrect login credentials is not displayed. ");
    }

    @Test(dependsOnMethods = "loginWithAnIncorrectEmailAndPasswordTest")
    @MethodOwner(owner = "mchutt")
    public void registerUserWithAnExistingEmailTest() {

        SignUpFormComponent form = homePage.getHeader().openLoginPage().getSignUpForm();
        boolean newUserSignupMessageVisible = form.isNewUserSignupMessageVisible();
        Assert.assertTrue(newUserSignupMessageVisible, "The 'New user signup' message is not Visible! ");
        form.signUp("Pepe", "pepe@pepe.com");

        boolean isErrorMessageVisible = form.isErrorMessageVisible();
        Assert.assertTrue(isErrorMessageVisible, "The error 'Email Address already exist!' message is not visible. ");
    }

    @Test(dependsOnMethods = "registerUserWithAnExistingEmailTest")
    @MethodOwner(owner = "mchutt")
    public void createAnAccount() {

        SignUpFormComponent form = homePage.getHeader().openLoginPage().getSignUpForm();
        form.signUp(NEW_USER_NAME, NEW_USER_EMAIL);

        AccountCreatedPage accountCreatedPage = form.openFullSignUpPage()
                .typeFirstName(NEW_USER_NAME)
                .typeLastName(NEW_USER_LAST_NAME)
                .typePassword(NEW_USER_PASS)
                .typeAddress(NEW_USER_ADDRESS)
                .selectCountry(NEW_USER_COUNTRY)
                .typeState(NEW_USER_STATE)
                .typeCity(NEW_USER_CITY)
                .typeZipCode(NEW_USER_ZIP_CODE)
                .typeMobilePhone(NEW_USER_MOBILE_NUMBER)
                .clickOnSubmitButton();

        Assert.assertTrue(accountCreatedPage.isAccountCreatedMessageVisible(), "The message 'Account Created!' is not visible!");
        accountCreatedPage.clickOnContinueButton();

    }

    @Test(dependsOnMethods = "createAnAccount")
    @MethodOwner(owner = "mchutt")
    public void searchAProductTest() {


        productsPage = homePage.getHeader().openProductsPage();
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

    @Test(dependsOnMethods = "searchAProductTest")
    @MethodOwner(owner = "mchutt")
    public void checkoutTest() {

        ProductCardComponent productCardComponent = productsPage.getProducts().get(0);
        ProductDetailsPage productDetailsPage = productCardComponent.openProductDetails();
        CartPage cartPage = productDetailsPage.clickOnAddToCartButton().openCartPage();

        Assert.assertFalse(cartPage.getAllProducts().isEmpty(), "The cart is empty after adding a product. ");

        CheckoutPage checkoutPage = cartPage.clickOnCheckoutButton();

        PaymentDonePage paymentDonePage = checkoutPage.clickOnPlaceOrderButton()
                .typeInNameOnCardInput(USER_CARD_NAME)
                .typeInCardNumberInput(USER_CARD_NUMBER)
                .typeInCVCInput(USER_CARD_CVC)
                .typeInExpirationMonthInput(USER_CARD_MONTH)
                .typeInExpirationYearInput(USER_CARD_YEAR)
                .clickOnConfirmOrderButton();

        boolean confirmedOrder = paymentDonePage.isConfirmedOrderMessageDisplayed();
        Assert.assertTrue(confirmedOrder, "The order confirmation message is not displayed. ");

    }

    @Test(dependsOnMethods = "checkoutTest")
    @MethodOwner(owner = "mchutt")
    public void removeAccountTest() {

        AccountDeletedPage accountDeletedPage = homePage.getHeader().deleteAccount();
        Assert.assertTrue(accountDeletedPage.isAccountDeletedMessageVisible(), "The message 'Account Deleted!' is not visible");
        accountDeletedPage.clickOnContinueButton();

        boolean isLoggedMessagePresent = homePage.getHeader().isLoggedMessagePresent();
        Assert.assertFalse(isLoggedMessagePresent, "The logged message should not be present");

    }

}

