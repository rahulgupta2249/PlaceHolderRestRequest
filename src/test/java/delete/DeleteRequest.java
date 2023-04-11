package delete;

import io.restassured.response.Response;
import utils.Base;
import utils.RestUtils;

import java.util.HashMap;
/**
 * Below class is to a mediator b/w Test Class & API interactive class which is RestUtils in our case.
 */
 class DeleteRequest {
     Response deleteJson(){

        String endPoint = (String) Base.mapFromJsonData.get("createPlaceHolderEndPoint");
        return RestUtils.performDelete(endPoint,new HashMap<>());

    }
}
