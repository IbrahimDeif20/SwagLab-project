package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Utility {
    private static final String screenshotPath = "Test-outputs/ScreenShots/";

    public static void ClickOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    public static void enterData(WebDriver driver, By locator, String Text) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(Text);
    }

    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public static void Scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].ScrollIntoView", byToWebElement(driver, locator));
    }

    public static WebElement byToWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static String getTimeStamps() {
        return new SimpleDateFormat("yyyy-m-hh-mm-ssa").format(new Date());
    }

    public static void takeScreenshot(WebDriver driver, String ScreenshotName) throws IOException {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File ScreenshotDest = new File(screenshotPath + ScreenshotName + "-" + getTimeStamps() + ".png");
            FileUtils.copyFile(src, ScreenshotDest);
            Allure.addAttachment(ScreenshotName, Files.newInputStream(ScreenshotDest.toPath()));
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
        }
    }

    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new Select(byToWebElement(driver, locator)).selectByVisibleText(option);
    }

    public static void takeCustomizedScreenshot(WebDriver driver, By locator) {
        try {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL).highlight(byToWebElement(driver, locator))
                    .save(screenshotPath);
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
        }

    }

    public static int generateRandomNumber(int upperBound) {
        return new Random().nextInt(upperBound) + 1;
    }

    public static Set<Integer> uniqueGeneratedNumbers(int numbersOfProductsNeeded, int numbersOfTotalProducts) {
        Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() < numbersOfProductsNeeded) {
            int randomNumber = generateRandomNumber(numbersOfTotalProducts);
            generatedNumbers.add(randomNumber);
        }
        return generatedNumbers;
    }

    public static boolean verifyURL(WebDriver driver, String expectedURL) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static File getLastFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }
}
