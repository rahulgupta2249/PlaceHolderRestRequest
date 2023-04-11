package delete;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Below class is to delete the resource.
 */
public class DeletePlaceholderTest extends DeleteRequest {

    @Test
    public void deletePlaceHolder(){



        Response response = deleteJson();

        Assert.assertEquals(response.getStatusCode(),200);


    }
}
