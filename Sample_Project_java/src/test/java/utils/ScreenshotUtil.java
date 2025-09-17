package utils;

import org.openqa.selenium.*;
import java.io.File;

public class ScreenshotUtil {
    public static void captureScreenshot(WebDriver driver, String filePath) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            org.openqa.selenium.io.FileHandler.copy(srcFile, destFile);
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}

