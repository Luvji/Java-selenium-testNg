package pages;

import static org.testng.Assert.fail;
import static utils.Delay.delay;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReadConfigUtils;
import utils.ReadLocatorPropertyUtils;

public class CartPage {

  WebDriver driver;
  WebDriverWait wait; // Declare as a field
  ReadLocatorPropertyUtils locatorData = new ReadLocatorPropertyUtils();
  ReadConfigUtils data = new ReadConfigUtils();

  By loc_cartLink;
  By loc_cartButton;
  By loc_firstName;
  By loc_lastName;
  By loc_zipcode;
  By loc_cartContinueButton;
  By loc_cartFinishButton;
  By loc_checkoutHeaderText;

  String cartUrl = "https://www.saucedemo.com/v1/cart.html";

  public CartPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Initialize once

    // loc_username = By.id(locatorData.getLocatorValue("locId_username"));

    loc_cartLink = By.cssSelector(locatorData.getLocatorValue("loc_cartLink"));
    loc_cartButton = By.cssSelector(locatorData.getLocatorValue("loc_cartButton"));
    loc_firstName = By.cssSelector(locatorData.getLocatorValue("loc_firstName"));
    loc_lastName = By.cssSelector(locatorData.getLocatorValue("loc_lastName"));
    loc_zipcode = By.cssSelector(locatorData.getLocatorValue("loc_zipcode"));
    loc_cartContinueButton = By.cssSelector(locatorData.getLocatorValue("loc_cartContinueButton"));
    loc_cartFinishButton = By.cssSelector(locatorData.getLocatorValue("loc_cartFinishButton"));
    loc_checkoutHeaderText = By.cssSelector(locatorData.getLocatorValue("loc_checkoutHeaderText"));
  }

  public void clickCart() {
    // driver.findBy.cssSelector("a.shopping_cart_link")Element(By.cssSelector("#shopping_cart_container")).click();
    WebElement cartLink = driver.findElement(loc_cartLink);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartLink);
  }

  public void clickCheckout() {
    delay(2);
    WebElement cartButton = wait.until(
        ExpectedConditions.visibilityOfElementLocated(loc_cartButton));
    cartButton.click();
  }

  public void enterLastName() {
    driver.findElement(loc_firstName).sendKeys(data.getFirstname());
  }

  public void enterFirstName() {
    driver.findElement(loc_lastName).sendKeys(data.getLastName());
  }

  public void enterZip() {
    driver.findElement(loc_zipcode).sendKeys(data.getZipCode());
    delay(3);
  }

  public void clickInfoContinue() {
    driver.findElement(loc_cartContinueButton).click();

  }

  public void clickCheckoutFinish() {
    driver.findElement(loc_cartFinishButton).click();
  }

  public void ensureCheckoutComplete() {
    String currUrl = driver.getCurrentUrl();
    delay(2);

    boolean isUrl = currUrl.equals(data.getCheckoutCompleteUrl());
    boolean isTextDisplayed = driver.findElement(loc_checkoutHeaderText).isDisplayed();

    if (!isUrl && !isTextDisplayed) {
      fail("page didnt reach checkout");
    }

  }

  public void ensureCartPageReach() throws Exception {

    String currUrl = driver.getCurrentUrl();
    if (currUrl.equals(cartUrl)) {
      System.out.println("Cart page Reached");
    } else {
      throw new Exception("cart page url not reached: current URL- " + currUrl);
    }

  }

}
