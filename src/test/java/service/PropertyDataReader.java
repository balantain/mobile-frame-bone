package service;

import java.util.ResourceBundle;

public interface PropertyDataReader {
    String URL_BUNDLE = "youTubeURLs";
    String MAIN = "main";
    String TEST_USER_BUNDLE = "testUser";

    default String getUrl(String urlSettings) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(URL_BUNDLE);
        return resourceBundle.getString(urlSettings);
    }

    default String getUserCredentials(String credentials){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(TEST_USER_BUNDLE);
        return resourceBundle.getString(credentials);
    }
}
