package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import reporting.ExtentReportManager;

import java.util.Map;

/**
 * This class is to interact with Rest Services using Rest Assured Libarary.
 * Logging the Request & Response Details in Extent Report
 * Creating a common Request Specification & Response Specification
 */
public class RestUtils {

    //Common Request Specification
    static RequestSpecification createRequestSpecification(String endPoint, Object requestPayload, Map<String, String> headers) {

        return RestAssured.given().baseUri(endPoint).contentType(ContentType.JSON).headers(headers).body(requestPayload);
    }

    static RequestSpecification createRequestSpecification(String endPoint, Map<String, String> headers) {

        return RestAssured.given().baseUri(endPoint).headers(headers);

    }

    static void printRequestDetailsInReport(RequestSpecification requestSpecification) {

        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);

        ExtentReportManager.logInfoDetails("End Point is: " + query.getBaseUri());
        ExtentReportManager.logInfoDetails("Request Method is: " + query.getMethod());
        ExtentReportManager.logInfoDetails("Rest Parameters are :" + query.getPathParams());
        ExtentReportManager.logInfoDetails("Headers are: ");

        ExtentReportManager.logDetailsInTabularFormat(query.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Request Body is: ");
        ExtentReportManager.logDetailsInJsonFormat(query.getBody());

    }

    static void printRequestDetailsInReportWithoutBody(RequestSpecification requestSpecification) {

        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);

        ExtentReportManager.logInfoDetails("End Point is: " + query.getBaseUri());
        ExtentReportManager.logInfoDetails("Request Method is: " + query.getMethod());
        ExtentReportManager.logInfoDetails("Rest Parameters are :" + query.getPathParams());
        ExtentReportManager.logInfoDetails("Headers are: ");

        ExtentReportManager.logDetailsInTabularFormat(query.getHeaders().asList());


    }

    static void printResponseDetailsInReport(Response response) {


        ExtentReportManager.logInfoDetails("Response Status Code is: " + response.getStatusCode());
        ExtentReportManager.logInfoDetails("Response Status Line is: " + response.getStatusLine());
        ExtentReportManager.logInfoDetails("Response Headers are: ");
        ExtentReportManager.logDetailsInTabularFormat(response.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Response Body is :");
        ExtentReportManager.logDetailsInJsonFormat(response.getBody().prettyPrint());
    }

    static void printResponseDetailsInReportWithOutBody(Response response) {


        ExtentReportManager.logInfoDetails("Response Status Code is: " + response.getStatusCode());
        ExtentReportManager.logInfoDetails("Response Status Line is: " + response.getStatusLine());
        ExtentReportManager.logInfoDetails("Response Headers are: ");
        ExtentReportManager.logDetailsInTabularFormat(response.getHeaders().asList());

    }

    //Creating method to perform a POST Operation.
    public static Response performPost(String endPoint, String requestPayload, Map<String, String> headers) {

        RequestSpecification requestSpecification = createRequestSpecification(endPoint, requestPayload, headers);
        Response response = requestSpecification.when().post();
        printRequestDetailsInReport(requestSpecification);
        printResponseDetailsInReport(response);
        return response;
    }

    public static Response performPost(String endPoint, Map<String, Object> requestPayload, Map<String, String> headers) {
        RequestSpecification requestSpecification = createRequestSpecification(endPoint, requestPayload, headers);
        Response response = requestSpecification.when().post();
        printRequestDetailsInReport(requestSpecification);
        printResponseDetailsInReport(response);
        return response;
    }

    public static Response performPost(String endPoint, Object requestPayloadFromPOJO, Map<String, String> headers) {
        RequestSpecification requestSpecification = createRequestSpecification(endPoint, requestPayloadFromPOJO, headers);
        Response response = requestSpecification.when().post("posts");
        printRequestDetailsInReport(requestSpecification);
        printResponseDetailsInReport(response);
        return response;
    }

    public static Response performPostWithWrongURI(String endPoint, Object requestPayloadFromPOJO, Map<String, String> headers) {
        RequestSpecification requestSpecification = createRequestSpecification(endPoint, requestPayloadFromPOJO, headers);
        Response response = requestSpecification.when().post("pofdsfdssts");
        printRequestDetailsInReport(requestSpecification);
        printResponseDetailsInReport(response);
        return response;
    }

    public static Response performPut(String endPoint, Object requestPayloadFromPOJO, Map<String, String> headers) {
        RequestSpecification requestSpecification = createRequestSpecification(endPoint, requestPayloadFromPOJO, headers);
        Response response = requestSpecification.when().put("posts/{id}", 1);
        printRequestDetailsInReport(requestSpecification);
        printResponseDetailsInReport(response);
        return response;
    }

    public static Response performPutWithWrongUri(String endPoint, Object requestPayloadFromPOJO, Map<String, String> headers) {
        RequestSpecification requestSpecification = createRequestSpecification(endPoint, requestPayloadFromPOJO, headers);
        Response response = requestSpecification.when().put("pofdsfsdsts/{id}", 1);
        printRequestDetailsInReport(requestSpecification);
        printResponseDetailsInReport(response);
        return response;
    }

    public static Response performPatch(String endPoint, Object requestPayloadFromPOJO, Map<String, String> headers) {
        RequestSpecification requestSpecification = createRequestSpecification(endPoint, requestPayloadFromPOJO, headers);
        Response response = requestSpecification.when().patch("posts/{id}", 1);
        printRequestDetailsInReport(requestSpecification);
        printResponseDetailsInReport(response);
        return response;
    }

    public static Response performDelete(String endPoint, Map<String, String> headers) {
        RequestSpecification requestSpecification = createRequestSpecification(endPoint, headers);
        Response response = requestSpecification.when().delete("posts/{id}", 1);
        printRequestDetailsInReportWithoutBody(requestSpecification);
        printResponseDetailsInReportWithOutBody(response);
        return response;

    }

    public static Response getData(String endPoint, Map<String, String> headers) {
        RequestSpecification requestSpecification = createRequestSpecification(endPoint, headers);
        Response response = requestSpecification.when().get("posts");
        printRequestDetailsInReportWithoutBody(requestSpecification);
        printResponseDetailsInReportWithOutBody(response);
        return response;

    }

    public static Response getDataforId(String endPoint, Map<String, String> headers, int pathParam) {
        RequestSpecification requestSpecification = createRequestSpecification(endPoint, headers);
        Response response = requestSpecification.when().get("posts/{id}", pathParam);
        printRequestDetailsInReportWithoutBody(requestSpecification);
        printResponseDetailsInReportWithOutBody(response);
        return response;

    }

    public static Response getDataforIdAndComments(String endPoint, Map<String, String> headers, int pathParam) {
        RequestSpecification requestSpecification = createRequestSpecification(endPoint, headers);
        Response response = requestSpecification.when().get("posts/{id}/comments", pathParam);
        printRequestDetailsInReportWithoutBody(requestSpecification);
        printResponseDetailsInReportWithOutBody(response);
        return response;

    }

    public static Response getDataforCommentWithQueryParam(String endPoint, Map<String, String> headers, int pathParam) {
        RequestSpecification requestSpecification = createRequestSpecification(endPoint, headers);
        Response response = requestSpecification.queryParam("postId", pathParam).when().get("comments");
        printRequestDetailsInReportWithoutBody(requestSpecification);
        printResponseDetailsInReportWithOutBody(response);
        return response;
    }

    public static Response wrongURI(String endPoint, Map<String, String> headers, int pathParam, String uri) {
        RequestSpecification requestSpecification = createRequestSpecification(endPoint, headers);
        Response response = requestSpecification.queryParam("postId", pathParam).when().get(uri);
        printRequestDetailsInReportWithoutBody(requestSpecification);
        printResponseDetailsInReportWithOutBody(response);
        return response;
    }
}
