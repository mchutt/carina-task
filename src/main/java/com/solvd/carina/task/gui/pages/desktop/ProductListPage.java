package com.solvd.carina.task.gui.pages.desktop;

import com.solvd.carina.task.gui.components.ProductCardComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ProductListPage extends AbstractPage {

    @FindBy(xpath = "//div[@data-component-type='s-search-result']")
    private List<ProductCardComponent> productList;

    @FindBy(xpath = "//span[@id='a-autoid-0-announce']//span[@class='a-dropdown-prompt']")
    private ExtendedWebElement sortingButton;

    @FindBy(css = "#s-result-sort-select_1")
    private ExtendedWebElement sortByPriceAsc;


    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductListEmpty(){
        return getProductList().isEmpty();
    }

    public ProductCardComponent getAProduct(int index){
        return getProductList().get(0);
    }

    public List<ProductCardComponent> getProductList(){
//        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        return productList;
    }

    public void clickOnSortingButton(){
//        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        wait.until(ExpectedConditions.elementToBeClickable(sortingButton));
        sortingButton.click();
    }
    public void clickOnSortByPriceAsc(){
//        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        wait.until(ExpectedConditions.elementToBeClickable(sortByPriceAsc));
        sortByPriceAsc.click();
    }
    public boolean isProductListSortedByPrice(){
        List<Double> clearPrices = getClearPrices();
        //System.out.println(clearPrices);

        return clearPrices
                .equals(clearPrices
                        .stream()
                        .sorted()
                        .collect(Collectors.toList()));
    }

    public List<Double> getClearPrices(){
       return getProductList().stream()
                .map(ProductCardComponent::getClearPrice)
                .filter(price -> !price.equals(-1.0))
                .collect(Collectors.toList());
    }
}
