package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {
    WebDriver driver;

    By username = By.id("user-name");
    By userPassword = By.id("password");
    By loginButton = By.xpath("//input[@id='login-button']");

    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public P01_LoginPage enterUsername(String usernameText) {
        Utility.enterData(driver, username, usernameText);
        return this;
    }

    public P01_LoginPage enterPassword(String userPasswordText) {
        Utility.enterData(driver, userPassword, userPasswordText);
        return this;
    }

    public P02_LandingPage ClickOnLoginButton() {
        Utility.ClickOnElement(driver, loginButton);
        return new P02_LandingPage(driver);
    }
}
