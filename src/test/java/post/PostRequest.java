package post;

import io.restassured.response.Response;
import pojo.JsonPlaceHolder;
import utils.Base;
import utils.RestUtils;

import java.util.HashMap;

/**
 * Below class is to a mediator b/w Test Class & API interactive class which is RestUtils in our case.
 */
 class PostRequest {


     Response createJson(JsonPlaceHolder requestPayload) {


        String endPoint = (String) Base.mapFromJsonData.get("createPlaceHolderEndPoint");
        return RestUtils.performPost(endPoint, requestPayload, new HashMap<>());
    }

     Response createRequestWithWrongURI(JsonPlaceHolder requestPayload) {

        String endPoint = (String) Base.mapFromJsonData.get("createPlaceHolderEndPoint");
        return RestUtils.performPostWithWrongURI(endPoint, requestPayload, new HashMap<>());
    }


}
