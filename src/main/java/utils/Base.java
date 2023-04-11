package utils;

import java.io.IOException;
import java.util.Map;

/**
 * This class is to read data from JSON and return in Map
 */
public class Base {
  public   static Map<String,Object> mapFromJsonData;
    static {
        String env = System.getProperty("env") == null ? "qa" : System.getProperty("env");
        try {
            mapFromJsonData = JsonUtils.getJsonDataAsMap(env+"/placeholderApiData");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
