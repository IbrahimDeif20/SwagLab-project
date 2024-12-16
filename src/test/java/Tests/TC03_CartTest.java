package Tests;

import Listener.IInvokeMethodListenerClass;
import Listener.ITestResultListenerClass;
import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Pages.P03_CartPage;
import Utilities.DataUtil;
import Utilities.LogUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static DriverFactory.DriverFactory.*;

@Listeners({IInvokeMethodListenerClass.class, ITestResultListenerClass.class})
public class TC03_CartTest {
    WebDriver driver;

    @BeforeMethod
    public void browserSetUp() throws IOException {
        setUpBrowser(DataUtil.getPropertyValue("environments", "Browser"));
        LogUtils.info("Edge is now opened");
        getDriver().get(DataUtil.getPropertyValue("environments", "BASE_URL"));
        LogUtils.info("Page is redirected to the URL");

    }

    @Test
    public void comparingPricesTC() throws IOException {
        String PriceOfSelectedProducts = new P01_LoginPage(getDriver()).enterUsername(DataUtil.getJsonData("ValidLoginData", "username"))
                .enterPassword(DataUtil.getJsonData("ValidLoginData", "password"))
                .ClickOnLoginButton().addRandomProducts(2, 6)
                .getTotalPriceOfSelectedProducts();
        //System.out.println(PriceOfSelectedProducts);
        new P02_LandingPage(getDriver()).openCartPage();
        Assert.assertTrue(new P03_CartPage(getDriver()).comparingPrices(PriceOfSelectedProducts));

    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
