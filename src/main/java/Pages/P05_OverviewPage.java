package Pages;

import Utilities.LogUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_OverviewPage {
    private final WebDriver driver;

    private final By subTotalLocator = By.className("summary_subtotal_label");
    private final By taxLocator = By.className("summary_tax_label");
    private final By totalPricesLocator = By.className("summary_total_label");
    private final By finishButtonLocator = By.id("finish");

    public P05_OverviewPage(WebDriver driver) {
        this.driver = driver;
    }


    public Float subTotal() {
        return Float.parseFloat(Utility.getText(driver, subTotalLocator).replace("Item total: $", ""));
    }

    public Float tax() {
        return Float.parseFloat(Utility.getText(driver, taxLocator).replace("Tax: $", ""));
    }

    public Float total() {
        LogUtils.info("actual total price: " + Utility.getText(driver, totalPricesLocator));
        return Float.parseFloat(Utility.getText(driver, totalPricesLocator).replace("Total: $", ""));
    }

    public String calculateTotalPrice() {
        LogUtils.info("Calculated total price: " + (subTotal() + tax()));
        return String.valueOf(subTotal() + tax());
    }

    public boolean comparingPrices() {
        return calculateTotalPrice().equals(String.valueOf(total()));
    }

    public P06_FinishingOrderPage clickOnFinishButton() {
        Utility.ClickOnElement(driver, finishButtonLocator);
        return new P06_FinishingOrderPage(driver);
    }
}
