package utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();
    public static Map<String,Object> getJsonDataAsMap(String jsonFileName) throws IOException {

        String completeFileName = System.getProperty("user.dir") + "/src/test/resources/" + jsonFileName + ".json";

        System.out.println(completeFileName);
        Map<String,Object> data = objectMapper.readValue(new File(completeFileName), new TypeReference<>() {});

        return data;

    }
}
