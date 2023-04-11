package get;

import io.restassured.response.Response;
import utils.Base;
import utils.RestUtils;

import java.util.HashMap;
/**
 * Below class is to fetch the response from an API
 */
 class GetRequest {

     Response getJson(){

        String endPoint = (String) Base.mapFromJsonData.get("createPlaceHolderEndPoint");
        return RestUtils.getData(endPoint,new HashMap<>());

    }

     Response getJsonforId(int pathParam){

        String endPoint = (String) Base.mapFromJsonData.get("createPlaceHolderEndPoint");
        return RestUtils.getDataforId(endPoint,new HashMap<>(),pathParam);

    }

     Response getJsonforIdAndComments(int pathParam){

        String endPoint = (String) Base.mapFromJsonData.get("createPlaceHolderEndPoint");
        return RestUtils.getDataforIdAndComments(endPoint,new HashMap<>(),pathParam);

    }

     Response getJsonforQueryComments(int pathParam){

        String endPoint = (String) Base.mapFromJsonData.get("createPlaceHolderEndPoint");
        return RestUtils.getDataforCommentWithQueryParam(endPoint,new HashMap<>(),pathParam);

    }

     Response getResponseFromWrongURI(int pathParam,String uri){

        String endPoint = (String) Base.mapFromJsonData.get("createPlaceHolderEndPoint");
        return RestUtils.wrongURI(endPoint,new HashMap<>(),pathParam,uri);

    }
}
