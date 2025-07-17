package Tests;

import Listener.IInvokeMethodListenerClass;
import Listener.ITestResultListenerClass;
import Pages.P01_LoginPage;
import Pages.P06_FinishingOrderPage;
import Utilities.DataUtil;
import Utilities.LogUtils;
import Utilities.Utility;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static DriverFactory.DriverFactory.*;

@Listeners({IInvokeMethodListenerClass.class, ITestResultListenerClass.class})
public class TC06_FinishingOrderTest {
    private final String firstName = DataUtil.getJsonData("information", "fName") + Utility.getTimeStamps();
    private final String lastName = DataUtil.getJsonData("information", "lName") + Utility.getTimeStamps();
    private final String zipcode = new Faker().number().digits(5);

    public TC06_FinishingOrderTest() throws FileNotFoundException {
    }

    @BeforeMethod
    public void browserSetUp() throws IOException {
        setUpBrowser(DataUtil.getPropertyValue("environments", "Browser"));
        LogUtils.info("Edge is now opened");
        getDriver().get(DataUtil.getPropertyValue("environments", "BASE_URL"));
        LogUtils.info("Page is redirected to the URL");

    }

    @Test
    @Description("Verify that the user can finish the order successfully")
    public void finishingOrderTC() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUsername(DataUtil.getJsonData("ValidLoginData", "username"))
                .enterPassword(DataUtil.getJsonData("ValidLoginData", "password"))
                .ClickOnLoginButton()
                .addRandomProducts(2, 6)
                .openCartPage()
                .clickOnCheckout()
                .fillForm(firstName, lastName, zipcode)
                .clickOnContinueButton()
                .clickOnFinishButton();
        Utility.takeScreenshot(getDriver(), "OrderFinished");
        LogUtils.info("Order is finished successfully");
        Assert.assertTrue(new P06_FinishingOrderPage(getDriver()).checkThanksMsg());
        LogUtils.info("Thanks message is displayed");
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
