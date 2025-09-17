package test;

import org.testng.annotations.Test;
import testComponents.BaseTest;

public class OrderTest extends BaseTest {

  @Test
  public void validCheckoutflow() {

    loginPage.enterUsername(data.getUsername());
    loginPage.enterPassword(data.getPassword());
    loginPage.clickLogin();
    productPage.ensurePageReach();
    productPage.addProductToCart("Sauce Labs Backpack");
    cartPage.clickCart();
    cartPage.clickCheckout();
    cartPage.enterFirstName();
    cartPage.enterLastName();
    cartPage.enterZip();
    cartPage.clickInfoContinue();
    cartPage.clickCheckoutFinish();
    cartPage.ensureCheckoutComplete();

  }

  @Test
  public void orderMultipleProductflow() {

    loginPage.enterUsername(data.getUsername());
    loginPage.enterPassword(data.getPassword());
    loginPage.clickLogin();
    productPage.ensurePageReach();
    productPage.addProductToCart("Sauce Labs Backpack");
    productPage.addProductToCart("Sauce Labs Fleece Jacket");
    cartPage.clickCart();
    cartPage.clickCheckout();
    cartPage.enterFirstName();
    cartPage.enterLastName();
    cartPage.enterZip();
    cartPage.clickInfoContinue();
    cartPage.clickCheckoutFinish();
    cartPage.ensureCheckoutComplete();

  }

}
