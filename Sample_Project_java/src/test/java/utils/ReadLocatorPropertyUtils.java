package utils;

import java.io.InputStream;
import java.util.Properties;

public class ReadLocatorPropertyUtils {
    private static Properties locatorData = new Properties();

    static {
        try (InputStream input = ReadLocatorPropertyUtils.class.getClassLoader().getResourceAsStream("locators.properties")) {
            locatorData.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLocatorValue(String key) {
        return locatorData.getProperty(key);
    }


}
  
