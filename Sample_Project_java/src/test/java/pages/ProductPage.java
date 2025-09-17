package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ReadLocatorPropertyUtils;
import utils.ReadConfigUtils;

public class ProductPage {

  WebDriver driver;
  ReadLocatorPropertyUtils locatorData = new ReadLocatorPropertyUtils();
  ReadConfigUtils data = new ReadConfigUtils();

  String productPageTitle = "Products";
  String cartUrl = data.getCartUrl();
  By loc_productLabel;
  By loc_productsInventoryCard;
  By loc_productItemName;
  By loc_productAddButton;

  public ProductPage(WebDriver driver) {
    this.driver = driver;
    loc_productLabel = By.cssSelector(locatorData.getLocatorValue("loc_productLabel"));
    loc_productsInventoryCard = By.cssSelector(locatorData.getLocatorValue("loc_productsInventoryCard"));
    loc_productItemName = By.cssSelector(locatorData.getLocatorValue("loc_productItemName"));
    loc_productAddButton = By.cssSelector(locatorData.getLocatorValue("loc_productAddButton"));
  }

  public void ensurePageReach() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

    WebElement productLabel = wait.until(
        ExpectedConditions.visibilityOfElementLocated(loc_productLabel));
    assertTrue(productLabel.isDisplayed());
    assertEquals(productLabel.getText(), productPageTitle);

  }

  public void addProductToCart(String txt) {
    List<WebElement> products = driver.findElements(loc_productsInventoryCard);
    for (WebElement product : products) {

      String name = product.findElement(loc_productItemName).getText();
      if (name.equals(txt)) {
        product.findElement(loc_productAddButton).click();
      }
    }
  }

}
