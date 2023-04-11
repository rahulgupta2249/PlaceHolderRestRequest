package constants;

import lombok.Getter;

/**
 * Defined a constant class to read the path of test data
 */
public final class FrameworkConstants {

    private static @Getter
    final String requestJsonFolderPath = System.getProperty("user.dir") + "/src/test/resources/jsons/";



}
