package Listener;

import Utilities.LogUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestResultListenerClass implements ITestListener {
    public void onTestStart(ITestResult result) {
        LogUtils.info("TC" + result.getName() + "has been started");

    }

    public void onTestSuccess(ITestResult result) {
        LogUtils.info("TC" + result.getName() + " has been passed");
    }

    public void onTestSkipped(ITestResult result) {
        LogUtils.info("TC" + result.getName() + " has been skipped");
    }

}
