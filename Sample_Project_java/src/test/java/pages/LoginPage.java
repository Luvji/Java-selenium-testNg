package pages;

import static org.testng.Assert.assertTrue;
import static utils.Delay.delay;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReadLocatorPropertyUtils;

public class LoginPage {

  WebDriver driver;
  WebDriverWait wait;
  ReadLocatorPropertyUtils locatorData = new ReadLocatorPropertyUtils();

  By loc_username;
  By loc_password;
  By loc_loginButton;
  By loc_loginErrorText;

  public LoginPage(WebDriver driver) {

    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Initialize once

    loc_username = By.id(locatorData.getLocatorValue("locId_username"));
    loc_password = By.id(locatorData.getLocatorValue("locId_password"));
    loc_loginButton = By.id(locatorData.getLocatorValue("locId_loginButton"));
    loc_loginErrorText = By.cssSelector("h3[data-test='" +
        locatorData.getLocatorValue("locCS_loginErrorText") + "']");
  }

  public void enterUsername(String username) {
    WebElement cartButton = wait.until(
        ExpectedConditions.visibilityOfElementLocated(loc_username));
    cartButton.click();
    driver.findElement(loc_username).sendKeys(username);

  }

  public void enterPassword(String password) {
    driver.findElement(loc_password).sendKeys(password);
  }

  public void enterInvalidPassword(String invalidPassword) {
    driver.findElement(loc_password).sendKeys(invalidPassword);
  }

  public void clickLogin() {
    driver.findElement(loc_loginButton).click();
  }

  public void checkLoginFail() {
    delay(2);
    Boolean isError = driver.findElement(loc_loginErrorText).isDisplayed();
    assertTrue(isError, "Expected login error message, but none was shown.");
  }
}