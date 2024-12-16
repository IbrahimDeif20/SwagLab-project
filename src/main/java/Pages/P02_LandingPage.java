package Pages;

import Utilities.LogUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

import static Utilities.Utility.generalWait;

public class P02_LandingPage {
    static float totalPrice = 0;
    private static List<WebElement> allProducts;
    private static List<WebElement> selectedProducts;
    private final By addToCartButtonForAllProducts = By.xpath("//button[@class]");
    private final By numbersOfProductsOnCartIcon = By.className("shopping_cart_badge");
    private final By numbersOfSelectedProducts = By.xpath("//button[.='Remove']");
    private final By cartICon = By.className("shopping_cart_link");
    private final By pricesOfSelectedProductsLocator = By.xpath("//button[.='Remove'] //preceding-sibling ::div[@class='inventory_item_price']");

    WebDriver driver;

    public P02_LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getNumbersOfProductsOnCartIcon() {
        return numbersOfProductsOnCartIcon;
    }

    public P02_LandingPage addAllProductsToCart() {
        allProducts = driver.findElements(addToCartButtonForAllProducts); //6
        LogUtils.info("Number of all products " + allProducts.size());
        for (int i = 1; i <= allProducts.size(); i++) {
            By addToCartButtonForAllProducts = By.xpath("(//button[@class])[" + i + "]");
            Utility.ClickOnElement(driver, addToCartButtonForAllProducts);
        }
        return this;
    }

    public String getNumberOfProductsOnCart() {
        try {
            LogUtils.info("number of products on cart " + Utility.getText(driver, numbersOfProductsOnCartIcon));
            return Utility.getText(driver, numbersOfProductsOnCartIcon);
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return "0";
        }
    }

    public String getNumberOfSelectedProducts() {
        try {
            selectedProducts = driver.findElements(numbersOfSelectedProducts); //6
            LogUtils.info("number of selected products " + selectedProducts.size());
            return String.valueOf(selectedProducts.size());
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            return "0";
        }

    }

    public P02_LandingPage addRandomProducts(int numbersOfProductsNeeded, int numbersOfTotalProducts) {
        Set<Integer> randomProducts = Utility.uniqueGeneratedNumbers(numbersOfProductsNeeded, numbersOfTotalProducts); //2,4,1
        for (int random : randomProducts) {
            LogUtils.info("Random number is " + random);
            By addToCartButtonForAllProducts = By.xpath("(//button[@class])[" + random + "]");
            Utility.ClickOnElement(driver, addToCartButtonForAllProducts);
        }
        return this;
    }

    public boolean comparingSelectedProductsToProductsOnCart() {
        return getNumberOfProductsOnCart().equals(getNumberOfSelectedProducts());
    }

    public P03_CartPage openCartPage() {
        Utility.ClickOnElement(driver, cartICon);
        return new P03_CartPage(driver);
    }

    public boolean verifyCartPageURL(String expectedURL) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTotalPriceOfSelectedProducts() {
        try {
            List<WebElement> priceOfSelectedProducts = driver.findElements(pricesOfSelectedProductsLocator); //up to 6 products
            for (int i = 1; i <= priceOfSelectedProducts.size(); i++) {
                By elements = By.xpath("(//button[.='Remove'] //preceding-sibling ::div[@class='inventory_item_price'])[" + i + "]");
                String fullText = Utility.getText(driver, elements);
                totalPrice += Float.parseFloat(fullText.replace("$", ""));
            }
            LogUtils.info("Total price on main page is " + totalPrice);
            return String.valueOf(totalPrice);
        } catch (Exception e) {
            return "0";
        }
    }

}
