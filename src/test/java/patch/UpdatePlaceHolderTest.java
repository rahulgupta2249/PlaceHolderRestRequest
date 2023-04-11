package patch;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.CreatePlaceHolderPayload;
import pojo.JsonPlaceHolder;

import utils.AssertionUtils;

import java.util.HashMap;
import java.util.Map;
/**
 * Below class is to a mediator b/w Test Class & API interactive class which is RestUtils in our case.
 */
public class UpdatePlaceHolderTest extends PatchRequest {

    @Test
    public void updatePlaceHolder() throws JsonProcessingException {

        JsonPlaceHolder payload = CreatePlaceHolderPayload.createPayloadFromPojo();
//        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload));
        Response response = updateJson(payload);

        Map<String,Object> expectedValueMap = new HashMap<>();

        expectedValueMap.put("id",1);
        expectedValueMap.put("userId",payload.getUserId());
        expectedValueMap.put("title",payload.getTitle());
        expectedValueMap.put("body",payload.getBody());

        AssertionUtils.assertExpectedValuesWithJsonPath(response,expectedValueMap);


    }
}
