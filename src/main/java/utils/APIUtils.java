package utils;

import io.restassured.response.Response;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This util class is to read data from file and return as String OR to store data in JSON File
 */
public final class APIUtils {

    private APIUtils(){

    }

    @SneakyThrows
    public static String readJSONAndGetAsString(String filePath){
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    @SneakyThrows
    public static void storeStringAsJSONFile(String filepath, Response response){
        Files.write(Paths.get(filepath),response.asByteArray());
    }
}
