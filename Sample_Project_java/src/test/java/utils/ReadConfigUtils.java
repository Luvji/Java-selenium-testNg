package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigUtils {
  String configFilePath = "/src/test/resources/config.properties";
  String BaseDirPath = System.getProperty("user.dir");
  Properties prop;

  public ReadConfigUtils() {
    File src = new File(BaseDirPath + configFilePath);
    try {

      FileInputStream f = new FileInputStream(src);

      prop = new Properties();
      prop.load(f);

    } catch (Exception e) {
      System.out.println("\n\tError:"+e.getLocalizedMessage());
    }

  }

  public String getUrl(){
    return prop.getProperty("baseUrl");
  }
  public String getUsername(){
    return prop.getProperty("username");
  }
  public String getPassword(){
    return prop.getProperty("password");
  }
  public String getTitle(){
    return prop.getProperty("productPageTitle");
  }
  public String getInvalidPassword(){
    return prop.getProperty("invalidPassword");
  }
  public String getCheckoutCompleteUrl(){
    return prop.getProperty("checkoutCompleteUrl");
  }
    public String getCartUrl(){
    return prop.getProperty("cartUrl");
  }
    public String getFirstname(){
    return prop.getProperty("firstName");
  }
    public String getLastName(){
    return prop.getProperty("lastName");
  }
    public String getZipCode(){
    return prop.getProperty("zip");
  }

}
