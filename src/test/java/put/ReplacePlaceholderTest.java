package put;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.CreatePlaceHolderPayload;
import pojo.JsonPlaceHolder;

import utils.AssertionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to replace the resource with new values.
 */
public class ReplacePlaceholderTest extends PutRequest {

    @Test
    public void replacePlaceHolder() throws JsonProcessingException {

        JsonPlaceHolder payload = CreatePlaceHolderPayload.replacePayloadFromPojo();

        Response response = replaceJson(payload);

        Map<String,Object> expectedValueMap = new HashMap<>();
        expectedValueMap.put("body",payload.getBody());
        expectedValueMap.put("id",1);

        AssertionUtils.assertExpectedValuesWithJsonPath(response,expectedValueMap);


    }

    @Test
    public void wrongURI() {

        JsonPlaceHolder payload = CreatePlaceHolderPayload.replacePayloadFromPojo();

        Response response = replaceJsonWithWrongUri(payload);

        response.then()
                .assertThat()
                .statusCode(404);

    }
}
