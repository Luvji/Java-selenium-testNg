package testComponents;

import java.time.Duration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import utils.ReadConfigUtils;

public class BaseTest {

  public ReadConfigUtils data;
  public LoginPage loginPage;
  public ProductPage productPage;
  public CartPage cartPage;
  protected WebDriver driver;

  public WebDriver getDriver() {
    return driver;
  }

  @BeforeMethod
  public void setup() {

    // ISSUE: When workoing with chrome driver, the google password
    // manager alert keep showing.tried various ways to avoid that
    // but got no working solution. Now handling it in fire fox.

    // chrome driver - not working due to error.

    // ChromeOptions options = new ChromeOptions();
    // Map<String, Object> prefs = new HashMap<>();
    // prefs.put("credentials_enable_service", false);
    // prefs.put("profile.password_manager_enabled", false);
    // options.setExperimentalOption("prefs", prefs);
    // Optional: disable automation info bars
    // options.setExperimentalOption("excludeSwitches", new
    // String[]{"enable-automation"});
    // options.setExperimentalOption("useAutomationExtension", false);

    // chrome driver - option 2 - not working either
    // ChromeOptions options = new ChromeOptions();
    // options.addArguments("--disable-features=CredentialsEnableService");
    // options.addArguments("--profile.password_manager_leak_detection=false");
    // // using a temporary profile for google chrome to bypass password manager.
    // options.addArguments("--user-data-dir=/tmp/chrome-profile");

    // driver = new ChromeDriver(options);
    // fire fox driver
    System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");
    FirefoxOptions options = new FirefoxOptions();
    options.setBinary("/snap/firefox/current/usr/lib/firefox/firefox"); // Actual Firefox binary path
    // options.addArguments("--headless");

    driver = new FirefoxDriver(options);
    driver.manage().window().setPosition(new Point(0, 0));
    data = new ReadConfigUtils();
    // watcher.driver = driver; // Set driver after setup

    driver.get(data.getUrl());
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    loginPage = new LoginPage(driver); // Create object
    productPage = new ProductPage(driver);
    cartPage = new CartPage(driver);

  }

  // ISSUE : when implemented the screen shot module, afterAll always one or
  // two (out of 4) browser instinct stay open when success.
  // if its a failure, then it would throw error with noSuchSession exception.
  // tried with After each and after _all combinations
  // Found a resolution by closing each after each step.
  // but had to create driver insatnce accordingly.

  @AfterMethod
  public void packup() {
    if (driver != null) {
      driver.close();
    } else {
      System.out.println("no driver found");
    }
  }

  @AfterSuite(alwaysRun = true)
  public void teardown() {
    if (driver != null) {
      try {
        System.out.println(">>> Closing Driver instance " + driver);
        driver.quit();
        System.out.println(">>> Browser terminated");
      } catch (NoSuchSessionException e) {
        System.out.println("Driver session already closed.");
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        driver = null; // avoid further use
      }
    } else {
      System.out.println("Driver already closed");
    }
  }

}
