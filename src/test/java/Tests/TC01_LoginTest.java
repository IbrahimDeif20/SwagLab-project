package Tests;

import Listener.IInvokeMethodListenerClass;
import Listener.ITestResultListenerClass;
import Pages.P01_LoginPage;
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

@Listeners({IInvokeMethodListenerClass.class, ITestResultListenerClass.class})
public class TC01_LoginTest {

    @BeforeMethod
    public void browserSetUp() throws IOException {
        setUpBrowser(DataUtil.getPropertyValue("environments", "Browser"));
        LogUtils.info("Edge is now opened");
        getDriver().get(DataUtil.getPropertyValue("environments", "BASE_URL"));
        LogUtils.info("Page is redirected to the URL");

    }

    @Test
    @Description("Verifying valid login functionality")
    public void ValidLogin() throws IOException {
        new P01_LoginPage(getDriver()).enterUsername(DataUtil.getJsonData("ValidLoginData", "username"))
                .enterPassword(DataUtil.getJsonData("ValidLoginData", "password"))
                .ClickOnLoginButton();
        Utility.takeScreenshot(getDriver(), "ValidLogin");
        Assert.assertEquals(getDriver().getCurrentUrl()
                , DataUtil.getPropertyValue("environments", "HOME_PAGE"));
    }

    @Test
    @Description("Verifying invalid login functionality with invalid username and valid password")
    public void InvalidLoginWithInvalidUsername() throws IOException {
        new P01_LoginPage(getDriver()).enterUsername(DataUtil.getJsonData("InvalidLoginData", "invalidUsername"))
                .enterPassword(DataUtil.getJsonData("InvalidLoginData", "validPassword"))
                .ClickOnLoginButton();
        Utility.takeScreenshot(getDriver(), "InvalidLoginWithInvalidUsername");
        Assert.assertNotEquals(getDriver().getCurrentUrl()
                , DataUtil.getPropertyValue("environments", "HOME_PAGE"));
    }

    @Test
    @Description("Verifying invalid login functionality with valid username and invalid password")
    public void InvalidLoginWithInvalidPassword() throws IOException {
        new P01_LoginPage(getDriver()).enterUsername(DataUtil.getJsonData("InvalidLoginData", "validUsername"))
                .enterPassword(DataUtil.getJsonData("InvalidLoginData", "invalidPassword"))
                .ClickOnLoginButton();
        Utility.takeScreenshot(getDriver(), "InvalidLoginWithInvalidPassword");
        Assert.assertNotEquals(getDriver().getCurrentUrl()
                , DataUtil.getPropertyValue("environments", "HOME_PAGE"));
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }

}
