package Pages;

import Utilities.LogUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_CartPage {
    private final By pricesOfSelectedProductsLocator = By.xpath("(//button[.='Remove'] //preceding-sibling ::div[@class='inventory_item_price'])");
    private final WebDriver driver;
    float totalPrice = 0;

    public P03_CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTotalPrice() {
        try {
            List<WebElement> priceOfSelectedProducts = driver.findElements(pricesOfSelectedProductsLocator); //up to 6 products

            for (int i = 1; i <= priceOfSelectedProducts.size(); i++) {
                By elements = By.xpath("(//button[.='Remove'] //preceding-sibling ::div[@class='inventory_item_price'])[" + i + "]");
                String fullText = Utility.getText(driver, elements);
                totalPrice += Float.parseFloat(fullText.replace("$", ""));
            }
            LogUtils.info("Total price on cart page is " + totalPrice);
            return String.valueOf(totalPrice);
        } catch (Exception e) {
            return "0";
        }
    }

    public boolean comparingPrices(String price) {
        return getTotalPrice().equals(price);
    }
}
