package utils;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.response.Response;
import pojo.AssertionKeys;
import reporting.ExtentReportManager;
import reporting.Setup;

import java.util.*;

/**
 * This class is to assert Expected Values with actual response and display data in Extent Report in Tabular Format.
 * For easy understanding for users to debug the data.
 */
public class AssertionUtils {


    public static void assertExpectedValuesWithJsonPath(Response response, Map<String,Object> expectedValuesMap){

        List<AssertionKeys> actualValuesMap = new ArrayList<>();
        actualValuesMap.add(new AssertionKeys("JSON Path","Expected VALUE","Actual VALUE","RESULT"));
        boolean allMatched = true;

        Set<String> jsonPaths = expectedValuesMap.keySet();

        for(String jsonPath:jsonPaths){

            Optional<Object> actualValue = Optional.ofNullable(response.jsonPath().get(jsonPath));

            if(actualValue.isPresent()){
                if(actualValue.get().equals(expectedValuesMap.get(jsonPath))){
                    actualValuesMap.add(new AssertionKeys(jsonPath,expectedValuesMap.get(jsonPath),actualValue.get(),"MATCHED"));
                }else{
                    allMatched = false;
                    actualValuesMap.add(new AssertionKeys(jsonPath,expectedValuesMap.get(jsonPath),actualValue.get(),"NOT MATCHED"));
                }

            }else {
                allMatched = false;
                actualValuesMap.add(new AssertionKeys(jsonPath,expectedValuesMap.get(jsonPath),"VALUE NOT FOUND","NOT MATCHED"));
            }

        }

        if(allMatched)
            ExtentReportManager.logPassDetails("All Assertions are passed. 😀😀");
        else
            ExtentReportManager.logFailDetails("All Assertions are not passed. 🥲🥲");

        String[][] strings = actualValuesMap.stream().map(str -> new String[]{str.getJsonPath(), String.valueOf(str.getExpectedValue()),String.valueOf(str.getActualValue())
                ,str.getResult()}).toArray(String[][]::new);
        Setup.extentTest.get().info(MarkupHelper.createTable(strings));


    }
}
