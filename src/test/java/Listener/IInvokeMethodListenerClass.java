package Listener;

import Utilities.LogUtils;
import Utilities.Utility;
import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokeMethodListenerClass implements IInvokedMethodListener {
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // Utility.takeCustomizedScreenshot(getDriver(), new P02_LandingPage(getDriver()).getNumbersOfProductsOnCartIcon());
        File logFile = Utility.getLastFile(LogUtils.Log_Path);
        try {

            Allure.addAttachment("logs.log", Files.readString(Path.of(logFile.getPath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
