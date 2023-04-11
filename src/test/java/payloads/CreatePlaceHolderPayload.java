package payloads;

import pojo.JsonPlaceHolder;
import utils.RandomDataGenerator;

/**
 * Create Payload for API by using POJO Class
 */
public class CreatePlaceHolderPayload {

    public static JsonPlaceHolder createPayloadFromPojo() {

        return JsonPlaceHolder.builder()
                .setUserId(Integer.valueOf(RandomDataGenerator.createRandomNumber(6)))
                .setTitle(RandomDataGenerator.generateTitle())
                .setBody(RandomDataGenerator.createRandomString(20))
                .build();

    }

    public static JsonPlaceHolder replacePayloadFromPojo() {


        return JsonPlaceHolder.builder()
                .setBody(RandomDataGenerator.createRandomString(20))
                .build();

    }
}
