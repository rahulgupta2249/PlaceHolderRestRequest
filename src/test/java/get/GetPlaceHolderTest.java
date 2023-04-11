package get;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.FrameworkConstants;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.JsonPlaceHolder;
import utils.APIUtils;
import utils.Base;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class GetPlaceHolderTest extends GetRequest {

    @Test
    public void getAllPlaceHolder() throws IOException {

        String expectedJson = APIUtils.readJSONAndGetAsString(FrameworkConstants.getRequestJsonFolderPath() + "allposts.json");



        Response response = getJson();
        response.then().assertThat()
                .statusCode(200)
                .time(Matchers.lessThan(2L), TimeUnit.SECONDS)
                .body(Matchers.equalTo(expectedJson));

    }

    @Test
    public void getAllPlaceHolderWithId() throws IOException {


        int id = Integer.parseInt(Base.mapFromJsonData.get("id").toString());

        Response response = getJsonforId(id);

        JsonPlaceHolder jsonPlaceHolder = response.as(JsonPlaceHolder.class);

        ObjectMapper objectMapper = new ObjectMapper();
        List<LinkedHashMap<String, Object>> linkedHashMaps = objectMapper.readValue(new File(FrameworkConstants.getRequestJsonFolderPath() + "allposts.json"),
                new TypeReference<List<LinkedHashMap<String, Object>>>() {
                });

            LinkedHashMap<String, Object> id1 = linkedHashMaps.stream().filter(e -> Integer.parseInt(e.get("id").toString()) == id).findFirst().get();

            Assert.assertEquals(Integer.parseInt(id1.get("userId").toString()),jsonPlaceHolder.getUserId());
            Assert.assertEquals(id1.get("title").toString(),jsonPlaceHolder.getTitle());
            Assert.assertEquals(id1.get("body").toString(),jsonPlaceHolder.getBody());

    }

    @Test
    public void getAllPlaceHolderWithIdAndComments() throws IOException {
        String expectedJson = APIUtils.readJSONAndGetAsString(FrameworkConstants.getRequestJsonFolderPath() + "postsWithComments.json");
        int id = Integer.parseInt(Base.mapFromJsonData.get("id").toString());

        Response response = getJsonforIdAndComments(id);

        response.then().assertThat()
                .statusCode(200)
                .time(Matchers.lessThan(2L), TimeUnit.SECONDS)
                .body(Matchers.equalTo(expectedJson));

    }

    @Test
    public void getAllPlaceHolderCommentsWithQueryParam() throws IOException {
        String expectedJson = APIUtils.readJSONAndGetAsString(FrameworkConstants.getRequestJsonFolderPath() + "postsWithComments.json");
        int id = Integer.parseInt(Base.mapFromJsonData.get("id").toString());

        Response response = getJsonforQueryComments(id);

        response.then().assertThat()
                .statusCode(200)
                .time(Matchers.lessThan(2L), TimeUnit.SECONDS)
                .body(Matchers.equalTo(expectedJson));

    }

    @Test
    public void wrongQueryParamValue() throws IOException {
        int id = (int)4234648995433L;
        String wrongURI = Base.mapFromJsonData.get("wrongURI").toString();
        Response response = getResponseFromWrongURI(id,wrongURI);

        response.then().assertThat()
                .statusCode(404)
                .time(Matchers.lessThan(2L), TimeUnit.SECONDS);

    }
}
