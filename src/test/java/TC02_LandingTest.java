import Listener.IInvokeMethodListenerClass;
import Listener.ITestResultListenerClass;
import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Utilities.DataUtil;
import Utilities.LogUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static DriverFactory.DriverFactory.*;

@Listeners({IInvokeMethodListenerClass.class, ITestResultListenerClass.class})
public class TC02_LandingTest {
    WebDriver driver;

    @BeforeMethod
    public void browserSetUp() throws IOException {
        setUpBrowser(DataUtil.getPropertyValue("environments", "Browser"));
        LogUtils.info("Edge is now opened");
        getDriver().get(DataUtil.getPropertyValue("environments", "BASE_URL"));
        LogUtils.info("Page is redirected to the URL");

    }

    @Test
    public void checkingNumberOfProducts() throws FileNotFoundException {
        new P01_LoginPage(getDriver()).enterUsername(DataUtil.getJsonData("ValidLoginData", "username"))
                .enterPassword(DataUtil.getJsonData("ValidLoginData", "password"))
                .ClickOnLoginButton()
                .addAllProductsToCart();
        Assert.assertTrue(new P02_LandingPage(getDriver()).comparingSelectedProductsToProductsOnCart());

    }

    @Test
    public void addingRandomProductsTC() throws FileNotFoundException {
        new P01_LoginPage(getDriver()).enterUsername(DataUtil.getJsonData("ValidLoginData", "username"))
                .enterPassword(DataUtil.getJsonData("ValidLoginData", "password"))
                .ClickOnLoginButton()
                .addRandomProducts(3, 6);
        Assert.assertTrue(new P02_LandingPage(getDriver()).comparingSelectedProductsToProductsOnCart());
    }

    @Test
    public void clickOnCartIconTC() throws IOException {
        new P01_LoginPage(getDriver()).enterUsername(DataUtil.getJsonData("ValidLoginData", "username"))
                .enterPassword(DataUtil.getJsonData("ValidLoginData", "password"))
                .ClickOnLoginButton()
                .openCartPage();
        Assert.assertTrue(new P02_LandingPage(getDriver()).verifyCartPageURL(DataUtil.getPropertyValue("environments", "CartIcon_URL")));
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
