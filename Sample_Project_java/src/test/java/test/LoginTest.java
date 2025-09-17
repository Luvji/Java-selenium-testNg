package test;

import org.testng.annotations.Test;
import testComponents.BaseTest;

public class LoginTest extends BaseTest {

  @Test
  public void invalidLogin() {

    loginPage.enterUsername(data.getUsername());
    loginPage.enterInvalidPassword(data.getInvalidPassword());
    loginPage.clickLogin();
    loginPage.checkLoginFail();

  }

  @Test
  public void validLogin() {

    loginPage.enterUsername(data.getUsername());
    loginPage.enterPassword(data.getPassword());
    loginPage.clickLogin();
    productPage.ensurePageReach();

  }
}
