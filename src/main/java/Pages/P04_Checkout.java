package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_Checkout {

    private final WebDriver driver;

    private final By firstnameLocator = By.id("first-name");
    private final By lastnameLocator = By.id("last-name");
    private final By zipCodeLocator = By.id("postal-code");
    private final By continueButtonLocator = By.id("continue");

    public P04_Checkout(WebDriver driver) {
        this.driver = driver;
    }

    public P04_Checkout fillForm(String fName, String lName, String zipcode) {
        Utility.enterData(driver, firstnameLocator, fName);
        Utility.enterData(driver, lastnameLocator, lName);
        Utility.enterData(driver, zipCodeLocator, zipcode);
        return this;
    }

    public P05_OverviewPage clickOnContinueButton() {
        Utility.ClickOnElement(driver, continueButtonLocator);
        return new P05_OverviewPage(driver);
    }
    
}
