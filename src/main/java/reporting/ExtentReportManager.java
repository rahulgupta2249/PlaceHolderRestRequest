package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.internal.NameAndValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * This class is to set up config in Extent Reports and create Reports and logging Details using Rest Assured Library
 */
public class ExtentReportManager {

    public static ExtentReports extentReports;
    public static ExtentReports setUpReport(String fileName,String reportName,String documentTitle){

        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileName);
        extentSparkReporter.config().setReportName(reportName);
        extentSparkReporter.config().setDocumentTitle(documentTitle);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setEncoding("utf-8");

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);

        return extentReports;

    }

    static String createReportNameWithTimeStamp(){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String formattedDateTime = dateTimeFormatter.format(localDateTime);
        formattedDateTime = "Test Report " + formattedDateTime + ".html";
        return formattedDateTime;

    }

    public static void logPassDetails(String log){
        Setup.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }

    public static void logFailDetails(String log){
        Setup.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }

    public static void logExceptionDetails(String log){
        Setup.extentTest.get().fail(log);
    }

    public static void logInfoDetails(String log){
        Setup.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
    }

    public static void logWarnDetails(String log){
        Setup.extentTest.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
    }

    public static void logDetailsInJsonFormat(String json){
        Setup.extentTest.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
    }

    public static <T extends NameAndValue> void logDetailsInTabularFormat(List<T> headers){

        String[][] strings = headers.stream().map(str -> new String[]{str.getName(), str.getValue()}).toArray(String[][]::new);
        Setup.extentTest.get().info(MarkupHelper.createTable(strings));
    }
}
