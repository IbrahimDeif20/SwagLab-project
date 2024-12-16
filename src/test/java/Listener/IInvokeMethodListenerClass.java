package Listener;

import Utilities.LogUtils;
import Utilities.Utility;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.IOException;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokeMethodListenerClass implements IInvokedMethodListener {
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // Utility.takeCustomizedScreenshot(getDriver(), new P02_LandingPage(getDriver()).getNumbersOfProductsOnCartIcon());
        if (testResult.getStatus() == ITestResult.FAILURE) {
            LogUtils.info("TC" + testResult.getName() + " has been failed");
            try {
                Utility.takeScreenshot(getDriver(), testResult.getName());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
