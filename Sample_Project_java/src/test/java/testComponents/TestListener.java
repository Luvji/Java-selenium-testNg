package testComponents;

import org.openqa.selenium.WebDriver;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import utils.ScreenshotUtil;

public class TestListener implements ITestListener, ISuiteListener {

  final String ANSI_BOLD = "\u001B[1m";
  final String ANSI_RESET = "\u001B[0m";

  public void printTestResults(IResultMap resultMap, String expected_status) {

    System.out.printf("%-25s %-25s %-40s %-10s%n", "Test Name", "Method Name", "Class Name", "Status");
    System.out
        .println("-----------------------------------------------------------------------------------------------");
    if (resultMap.size() > 0) {

      Set<ITestResult> results = resultMap.getAllResults();
      for (ITestResult result : results) {
        String testName = result.getName();
        String methodName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        String status = getStatusName(result.getStatus());
        System.out.printf("%-25s %-25s %-40s %-10s%n", testName, methodName, className, status);
      }
    } else {
      System.out.println("There is no " + expected_status + " tests");
    }
  }

  private String getStatusName(int status) {
    switch (status) {
      case ITestResult.SUCCESS:
        return "SUCCESS";
      case ITestResult.FAILURE:
        return "FAILURE";
      case ITestResult.SKIP:
        return "SKIP";
      default:
        return "UNKNOWN";
    }
  }

  private static String folderPath;

  @Override
  public void onStart(ISuite suite) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");

    Date now = new Date();
    String runDate = dateFormat.format(now);
    String runStartTime = timeFormat.format(now);

    folderPath = "screenshots" + File.separator + runDate + File.separator + runStartTime;
    File screenshotDir = new File(folderPath);
    if (!screenshotDir.exists()) {
      screenshotDir.mkdirs();
    }
    System.out.println("Screenshot folder initialized at: " + folderPath);
  }

  @Override
  public void onTestStart(ITestResult ITestResult) {
    System.out.println("\tTest Start" + " :- "
        + ITestResult.getTestClass().getName() + " -> " + ITestResult.getMethod().getMethodName());
  }

  @Override
  public void onFinish(ITestContext context) {
    System.out.println("\tAll Test(s) Completed | Suite name : " + context.getName() + "\n**********************************");

    System.out.println(ANSI_BOLD+"    -: Summary :-"+ANSI_RESET+"\n**********************************");
    IResultMap passed = context.getPassedTests();
    System.out.println(ANSI_BOLD+"**Passed Tests:-"+ANSI_RESET);
    printTestResults(passed, "Passed");

    IResultMap failed = context.getFailedTests();

    System.out.println(ANSI_BOLD+"\n**Failed Tests:-"+ANSI_RESET);
    printTestResults(failed, "Failed");
    System.out
        .println("_______________________________________________________________________________________________\n");
  }

  @Override
  public void onTestSuccess(ITestResult ITestResult) {
    System.out.println("\t\t Test Successful");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    Object currentClass = result.getInstance();
    WebDriver driver = ((BaseTest) currentClass).getDriver();
    String methodName = result.getMethod().getMethodName();
    String filePath = folderPath + File.separator + methodName + ".png";
    ScreenshotUtil.captureScreenshot(driver, filePath);
    System.out.println("\t\tTest failed - Screenshot captured for failed test: " + filePath);
  }

  @Override
  public void onTestSkipped(ITestResult ITestResult) {
    System.out.println("\t\t Test Skipped");
  }
}
