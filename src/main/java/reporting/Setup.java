package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class usues ITestListener to setup & tearn down Reports
 * This class is used to create Test test details and Test failure details
 */
public class Setup implements ITestListener {

    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ExtentReports extentReports;

    //onStart will work with Test NG - <test verbose="2" /. If there are multiple <test> tag, then it will run before all of them>
    public void onStart(ITestContext context) {
        String reportNameWithTimeStamp = ExtentReportManager.createReportNameWithTimeStamp();
        String reportPath = System.getProperty("user.dir") + "/reports/" + reportNameWithTimeStamp ;

         extentReports = ExtentReportManager.setUpReport(reportPath, "Test API Automation Report", "Test Execution Report");
    }

    public void onFinish(ITestContext context) {
        if(Objects.nonNull(extentReports))extentReports.flush();
    }

    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest("Test Name : " + result.getTestClass().getName() + " -- " + result.getMethod().getMethodName());

        extentTest.set(test);

    }

    public void onTestFailure(ITestResult result) {
        ExtentReportManager.logFailDetails(result.getThrowable().getMessage());


        String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
        stackTrace =  stackTrace.replaceAll(",","<br>");

        String detailsMsg = "<details>\n" +
                "  <summary> Click to see here:- </summary>\n" +
                stackTrace + " \n" +
                "</details>";
        ExtentReportManager.logExceptionDetails(detailsMsg);

    }
}
