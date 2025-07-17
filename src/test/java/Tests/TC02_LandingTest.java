package Tests;

import Listener.IInvokeMethodListenerClass;
import Listener.ITestResultListenerClass;
import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Utilities.DataUtil;
import Utilities.LogUtils;
import Utilities.Utility;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static DriverFactory.DriverFactory.*;
import static Utilities.Utility.verifyURL;

@Listeners({IInvokeMethodListenerClass.class, ITestResultListenerClass.class})
public class TC02_LandingTest {

    @BeforeMethod
    public void browserSetUp() throws IOException {
        setUpBrowser(DataUtil.getPropertyValue("environments", "Browser"));
        LogUtils.info("Edge is now opened");
        getDriver().get(DataUtil.getPropertyValue("environments", "BASE_URL"));
        LogUtils.info("Page is redirected to the URL");

    }

    @Test
    @Description("Login with valid credentials and verify the landing page")
    public void checkingNumberOfProducts() throws IOException {
        new P01_LoginPage(getDriver()).enterUsername(
                        DataUtil.getJsonData("ValidLoginData", "username"))
                .enterPassword(DataUtil.getJsonData("ValidLoginData", "password"))
                .ClickOnLoginButton()
                .addAllProductsToCart();
        Utility.takeScreenshot(getDriver(), "AllProductsAddedToCart");
        LogUtils.info("All products are added to the cart");
        Assert.assertTrue(new P02_LandingPage(getDriver()).comparingSelectedProductsToProductsOnCart());
        LogUtils.info("Selected products are compared to products on cart successfully");
    }


    @Test
    @Description("Adding random products to the cart and verifying the count")
    public void addingRandomProductsTC() throws IOException {
        new P01_LoginPage(getDriver()).enterUsername(
                        DataUtil.getJsonData("ValidLoginData", "username"))
                .enterPassword(DataUtil.getJsonData("ValidLoginData", "password"))
                .ClickOnLoginButton()
                .addRandomProducts(3, 6);
        Utility.takeScreenshot(getDriver(), "RandomProductsAddedToCart");
        LogUtils.info("Random products are added to the cart");
        Assert.assertTrue(new P02_LandingPage(getDriver()).comparingSelectedProductsToProductsOnCart());
        LogUtils.info("Selected products are compared to products on cart successfully");
    }

    @Test
    @Description("Click on cart icon and verify the URL")
    public void clickOnCartIconTC() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUsername(DataUtil.getJsonData("ValidLoginData", "username"))
                .enterPassword(DataUtil.getJsonData("ValidLoginData", "password"))
                .ClickOnLoginButton()
                .openCartPage();
        Utility.takeScreenshot(getDriver(), "CartPageOpened");
        LogUtils.info("Cart icon is clicked and redirected to the cart page");
        Assert.assertTrue(verifyURL(getDriver(),
                DataUtil.getPropertyValue("environments", "CartIcon_URL")));
        LogUtils.info("Cart page URL is verified successfully");
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
