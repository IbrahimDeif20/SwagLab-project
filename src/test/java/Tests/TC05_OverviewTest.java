package Tests;

import Listener.IInvokeMethodListenerClass;
import Listener.ITestResultListenerClass;
import Pages.P01_LoginPage;
import Pages.P05_OverviewPage;
import Utilities.DataUtil;
import Utilities.LogUtils;
import Utilities.Utility;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static DriverFactory.DriverFactory.*;

@Listeners({IInvokeMethodListenerClass.class, ITestResultListenerClass.class})
public class TC05_OverviewTest {
    private final String firstName = DataUtil.getJsonData("information", "fName") + Utility.getTimeStamps();
    private final String lastName = DataUtil.getJsonData("information", "lName") + Utility.getTimeStamps();
    private final String zipcode = new Faker().number().digits(5);

    public TC05_OverviewTest() throws FileNotFoundException {
    }


    @BeforeMethod
    public void browserSetUp() throws IOException {
        setUpBrowser(DataUtil.getPropertyValue("environments", "Browser"));
        LogUtils.info("Edge is now opened");
        getDriver().get(DataUtil.getPropertyValue("environments", "BASE_URL"));
        LogUtils.info("Page is redirected to the URL");

    }

    @Test
    public void checkoutStepTwoTC() throws IOException {
        new P01_LoginPage(getDriver()).enterUsername(DataUtil.getJsonData("ValidLoginData", "username"))
                .enterPassword(DataUtil.getJsonData("ValidLoginData", "password"))
                .ClickOnLoginButton().addRandomProducts(2, 6)
                .openCartPage().clickOnCheckout().fillForm(firstName, lastName, zipcode)
                .clickOnContinueButton();
        Assert.assertTrue(new P05_OverviewPage(getDriver()).comparingPrices());

    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}

