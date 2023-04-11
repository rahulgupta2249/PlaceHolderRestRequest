package post;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import payloads.CreatePlaceHolderPayload;
import pojo.JsonPlaceHolder;
import utils.AssertionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Rahul Gupta
 * @version 1.0
 * @since 1.0
 *
 * This Class is to create a new resource with POST Call includes negative scenarios as well
 */
public class CreatePlaceholderTest extends PostRequest {
    /**
     * Below Method is to create a new resource on API
     */
    @Test
    public void createPlaceHolder() {

        JsonPlaceHolder payload = CreatePlaceHolderPayload.createPayloadFromPojo();

        Response response = createJson(payload);
        response.then()
                .assertThat()
                .statusCode(201)
                .time(Matchers.lessThan(2L), TimeUnit.SECONDS);

        Map<String, Object> expectedValueMap = new HashMap<>();
        expectedValueMap.put("userId", payload.getUserId());
        expectedValueMap.put("title", payload.getTitle());
        expectedValueMap.put("body", payload.getBody());

        AssertionUtils.assertExpectedValuesWithJsonPath(response, expectedValueMap);


    }

    /**\
     * Send Request without Request Paylaod
     */
    @Test
    public void createPlaceHolderRequestWithoutBody() {


        Response response = createJson(new JsonPlaceHolder());
        response.then()
                .assertThat()
                .statusCode(404)
                .time(Matchers.lessThan(2L), TimeUnit.SECONDS);


    }

    /**
     * Send Rquest with Wrong URI and expects 404
     */
    @Test
    public void wrongURI() {

        JsonPlaceHolder payload = CreatePlaceHolderPayload.createPayloadFromPojo();

        Response response = createRequestWithWrongURI(payload);

        response.then()
                .assertThat()
                .statusCode(404);


    }
}
